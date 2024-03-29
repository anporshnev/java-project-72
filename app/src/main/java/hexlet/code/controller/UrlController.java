package hexlet.code.controller;

import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.AlertsType;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URL;
import java.util.Collections;

@Slf4j
public class UrlController {
    public static Handler create = ctx -> {
        var name = ctx.formParamAsClass("url", String.class).getOrDefault("").trim().toLowerCase();
        URL url;

        try {
            url = new URI(name).toURL();
        } catch (Exception e) {
            ctx.sessionAttribute("alertMessage", "Некорректный URL");
            ctx.sessionAttribute("typeAlert", AlertsType.DANGER.getType());
            ctx.redirect("/");
            return;
        }

        var parsedUrl = "%s://%s".formatted(url.getProtocol(), url.getAuthority());

        var urlFromDB = UrlRepository.findByUrl(parsedUrl);
        if (urlFromDB.isPresent()) {
            ctx.sessionAttribute("alertMessage", "Страница уже существует");
            ctx.sessionAttribute("typeAlert", AlertsType.DANGER.getType());
            ctx.redirect("/");
            return;
        }

        var newUrl = new Url(parsedUrl);
        UrlRepository.save(newUrl);

        ctx.sessionAttribute("alertMessage", "Страница успешно добавлена");
        ctx.sessionAttribute("typeAlert", AlertsType.SUCCESS.getType());
        ctx.redirect("/urls");
    };

    public static Handler show = ctx -> {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Сайт не найден"));
        var page = new UrlPage(url);
        ctx.render("urls/url.jte", Collections.singletonMap("page", page));
    };

    public static Handler showAll = ctx -> {
        var urls = UrlRepository.getAll();
        var page = new UrlsPage(urls);
        page.setAlertMessage(ctx.consumeSessionAttribute("alertMessage"));
        page.setTypeAlert(ctx.consumeSessionAttribute("typeAlert"));
        ctx.render("urls/urls.jte", Collections.singletonMap("page", page));
    };
}
