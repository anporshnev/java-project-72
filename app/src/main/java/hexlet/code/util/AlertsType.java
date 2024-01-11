package hexlet.code.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlertsType {
    DANGER("danger"),
    SUCCESS("success");

    private final String type;
}
