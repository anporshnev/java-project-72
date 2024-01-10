package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repository.BaseRepository;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    private static final String DEFAULT_PORT = "7070";
    private static final String DEFAULT_DB = "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;";

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", DEFAULT_PORT);
        return Integer.parseInt(port);
    }
    private static String getDatabase() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL", DEFAULT_DB);

    }

    public static Javalin getApp() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDatabase());

        BaseRepository.dataSource = new HikariDataSource(hikariConfig);

        var app = Javalin.create(conf -> conf.plugins.enableDevLogging());

        app.get("/", ctx -> ctx.result("Hello, World!"));

        return app;
    }
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
}
