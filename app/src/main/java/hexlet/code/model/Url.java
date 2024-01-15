package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class Url {

    private Long id;

    @ToString.Include
    private String name;
    private Instant createdAt;

    public Url(String name) {
        this.name = name;
        this.createdAt = Instant.now();
    }

    public Url(String name, Instant createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }
}
