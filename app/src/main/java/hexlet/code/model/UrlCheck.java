package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class UrlCheck {
    private Long id;

    @ToString.Include
    private Integer statusCode;
    private String title;
    private String h1;
    private String description;
    private Long urlId;
    private Instant createdAt;

    public UrlCheck(Integer statusCode, String title, String h1, String description) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.createdAt = Instant.now();
    }
}
