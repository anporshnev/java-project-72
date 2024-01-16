package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;


import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;



public class AppTest {
    private static Javalin app;
    private static final String NOT_FOUND_ID = "1000";
    private static final String EXAMPLE_URL = "https://some-domain.org";

    @BeforeEach
    public final void setApp() throws SQLException, IOException {
        app = App.getApp();
    }

    @AfterAll
    public static final void closeApp() {
        app.close();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            var body = response.body().string();
            assertThat(response.code()).isEqualTo(HttpServletResponse.SC_OK);
            assertThat(body).contains("Анализатор страниц");
            assertThat(body).contains("Главная");
            assertThat(body).contains("Сайты");
            assertThat(body).contains("Бесплатно проверяйте сайты на SEO пригодность");
        });
    }

    @Nested
    class UrlTest {

        @Test
        public void testUrlPage() throws SQLException {
            var url = new Url(EXAMPLE_URL);
            UrlRepository.save(url);

            JavalinTest.test(app, (server, client) -> {
                var response = client.get("/urls/" + url.getId());
                var body = response.body().string();
                assertThat(response.code()).isEqualTo(HttpServletResponse.SC_OK);
                assertThat(body).contains(EXAMPLE_URL);
            });
        }

        @Test
        public void testUrlNotFound() throws SQLException {
            var url = new Url(EXAMPLE_URL);
            UrlRepository.save(url);

            JavalinTest.test(app, (server, client) -> {
                var response = client.get("/urls/" + NOT_FOUND_ID);
                assertThat(response.code()).isEqualTo(HttpServletResponse.SC_NOT_FOUND);
            });
        }

        @Test
        public void testUrlsPage() {
            JavalinTest.test(app, (server, client) -> {
                var response = client.get("/urls");
                assertThat(response.code()).isEqualTo(HttpServletResponse.SC_OK);
            });
        }
    }
}

