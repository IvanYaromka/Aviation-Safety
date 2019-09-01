package by.yaromka.aviation.safety.domain.dto.airline;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public final class AirlineRequest {
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
    @NotNull
    @Min(1)
    private Long countryId;
}
