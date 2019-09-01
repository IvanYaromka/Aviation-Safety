package by.yaromka.aviation.safety.service.safety.airport;

import by.yaromka.aviation.safety.service.safety.SafetyService;
import by.yaromka.aviation.safety.domain.safety.response.SafetyResponse;
import by.yaromka.aviation.safety.repository.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AirportSafetyService implements SafetyService {
    @Autowired
    private FlightRepository repository;

    @Override
    public SafetyResponse queryByDateRange(Long id, LocalDate begin, LocalDate end) {
        return null;
    }
}
