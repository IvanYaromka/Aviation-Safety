package by.yaromka.aviation.safety.service.entity.pilot.impl;

import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.pilot.PilotRepository;
import by.yaromka.aviation.safety.service.entity.pilot.PilotService;
import by.yaromka.aviation.safety.service.entity.airline.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotRepository repository;
    @Autowired
    private AirlineService airlineService;

    @Override
    public List<Pilot> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Pilot> findById(Long id) {
        Optional<Pilot> result = repository.findById(id);
        if (result.isPresent()) {
            Pilot pilot = result.get();
            airlineService.findById(pilot.getId()).ifPresent(pilot::setAirline);
        }
        return result;
    }

    @Override
    public Page<Pilot> findForPage(Long index, Long size) {
        return repository.findForPage(index, size);
    }

    @Override
    public Long create(Pilot entity) {
        return repository.create(entity);
    }

    @Override
    public Long update(Long id, Pilot entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
