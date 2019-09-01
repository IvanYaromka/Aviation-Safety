package by.yaromka.aviation.safety.domain.dto.pilot;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public final class PilotRequest {
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String surname;
    @NotNull
    @Min(1)
    private Long airlineId;
}
