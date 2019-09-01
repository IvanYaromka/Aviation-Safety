package by.yaromka.aviation.safety.domain.dto.type;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public final class AirTypeRequest {
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
    @NotNull
    private Double rool;
    @NotNull
    private Double takeOffPitch;
    @NotNull
    private Double landPitch;
    @NotNull
    private Double gForce;
}
