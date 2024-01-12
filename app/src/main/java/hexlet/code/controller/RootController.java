package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import io.javalin.http.Context;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Slf4j
public class RootController {
    public static void index(Context ctx) {
        var page = new BasePage();
        page.setAlertMessage(ctx.consumeSessionAttribute("alertMessage"));
        page.setTypeAlert(ctx.consumeSessionAttribute("typeAlert"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
}
