package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Url {

    private Long id;

    @ToString.Include
    private String name;
    private Date createdAt= new Date();

    public Url(String name) {
        this.name = name;
    }
}
