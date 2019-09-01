package by.yaromka.aviation.safety.domain.dto.airplane;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public final class AirplaneRequest {
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String number;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String year;
    @NotNull
    @Min(1)
    private Long typeId;
}
