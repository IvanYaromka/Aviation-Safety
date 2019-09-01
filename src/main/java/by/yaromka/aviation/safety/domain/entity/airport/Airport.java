package by.yaromka.aviation.safety.domain.entity.airport;

import by.yaromka.aviation.safety.domain.entity.country.Country;
import lombok.Data;

@Data
public class Airport {
    private Long id;
    private String name;
    private String icao;
    private Country country;
}
