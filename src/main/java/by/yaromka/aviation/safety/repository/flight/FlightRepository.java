package by.yaromka.aviation.safety.repository.flight;

import by.yaromka.aviation.safety.repository.Repository;
import by.yaromka.aviation.safety.domain.entity.flight.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends Repository<Flight> {
    List<Flight> findLastForPilot(Integer count, Long id);
    List<Flight> findBetweenDateForPilot(LocalDate begin, LocalDate end, Long id);
    List<Flight> findLastForAirport(Integer count, Long id);
    List<Flight> findBetweenDateForAirport(LocalDate begin, LocalDate end, Long id);
    List<Flight> findLastForAirline(Integer count, Long id);
    List<Flight> findBetweenDateForAirline(LocalDate begin, LocalDate end, Long id);
}
