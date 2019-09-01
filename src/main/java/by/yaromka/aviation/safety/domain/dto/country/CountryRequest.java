package by.yaromka.aviation.safety.domain.dto.country;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public final class CountryRequest {
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
}
