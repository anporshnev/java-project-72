package hexlet.code.dto;

import hexlet.code.util.AlertsType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePage {
    private String alertMessage;
    private AlertsType typeAlert;
}
