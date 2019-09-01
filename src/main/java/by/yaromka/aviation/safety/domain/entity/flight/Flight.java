package by.yaromka.aviation.safety.domain.entity.flight;

import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Flight {
    private Long id;
    private LocalDate takeOffDate;
    private LocalDate landDate;
    private Pilot firstPilot;
    private Pilot secondPilot;
    private Airport takeOffAirport;
    private Airport landAirport;
    private Double rool;
    private Double takeOffPitch;
    private Double landPitch;
    private Double gforce;
}
