package hexlet.code.controller;

import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.AlertsType;
import io.javalin.http.Handler;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URL;

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

        var newUrl = new Url(name);
        UrlRepository.save(newUrl);

        ctx.sessionAttribute("alertMessage", "Страница успешно добавлена");
        ctx.sessionAttribute("typeAlert", AlertsType.SUCCESS.getType());
        ctx.redirect("/urls");
    };
}
