package by.yaromka.aviation.safety.domain.entity.airline;

import by.yaromka.aviation.safety.domain.entity.country.Country;
import lombok.Data;

@Data
public class Airline {
    private Long id;
    private String name;
    private Country country;
}
