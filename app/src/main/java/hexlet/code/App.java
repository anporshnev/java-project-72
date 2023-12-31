package hexlet.code;

import io.javalin.Javalin;

public class App {
    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }


    public static Javalin getApp() {
        var app = Javalin.create(conf -> conf.plugins.enableDevLogging());

        app.get("/", ctx -> ctx.result("Hello, World!"));


        return app;
    }
    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(getPort());
    }
}
