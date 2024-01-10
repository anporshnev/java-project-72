package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

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
    }
}
