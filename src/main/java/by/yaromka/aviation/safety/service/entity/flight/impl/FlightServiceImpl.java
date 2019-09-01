package by.yaromka.aviation.safety.service.entity.flight.impl;

import by.yaromka.aviation.safety.domain.entity.flight.Flight;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.flight.FlightRepository;
import by.yaromka.aviation.safety.service.entity.airport.AirportService;
import by.yaromka.aviation.safety.service.entity.flight.FlightService;
import by.yaromka.aviation.safety.service.entity.pilot.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository repository;
    @Autowired
    private PilotService pilotService;
    @Autowired
    private AirportService airportService;

    @Override
    public List<Flight> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        Optional<Flight> result = repository.findById(id);
        if (result.isPresent()) {
            Flight flight = result.get();
            pilotService.findById(flight.getFirstPilot().getId()).ifPresent(flight::setFirstPilot);
            pilotService.findById(flight.getSecondPilot().getId()).ifPresent(flight::setSecondPilot);
            airportService.findById(flight.getTakeOffAirport().getId()).ifPresent(flight::setTakeOffAirport);
            airportService.findById(flight.getLandAirport().getId()).ifPresent(flight::setLandAirport);
        }
        return result;
    }

    @Override
    public Page<Flight> findForPage(Long index, Long size) {
        return repository.findForPage(index, size);
    }

    @Override
    public Long create(Flight entity) {
        return repository.create(entity);
    }

    @Override
    public Long update(Long id, Flight entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
