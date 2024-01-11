package hexlet.code.dto;

import hexlet.code.util.AlertsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasePage {
    private String alertMessage;
    private AlertsType typeAlert;
}
