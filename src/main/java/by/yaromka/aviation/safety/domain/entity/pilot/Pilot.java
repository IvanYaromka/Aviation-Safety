package by.yaromka.aviation.safety.domain.entity.pilot;

import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import lombok.Data;

@Data
public class Pilot {
    private Long id;
    private String name;
    private String surname;
    private Airline airline;
}
