package by.yaromka.aviation.safety.service.entity.airline.iml;

import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.airline.AirlineRepository;
import by.yaromka.aviation.safety.service.entity.airline.AirlineService;
import by.yaromka.aviation.safety.service.entity.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {
    @Autowired
    private AirlineRepository repository;
    @Autowired
    private CountryService countryService;

    @Override
    public List<Airline> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Airline> findById(Long id) {
        Optional<Airline> result = repository.findById(id);
        if (result.isPresent()) {
            Airline airline = result.get();
            countryService.findById(airline.getCountry().getId()).ifPresent(airline::setCountry);
        }
        return result;
    }

    @Override
    public Page<Airline> findForPage(Long index, Long size) {
        return repository.findForPage(index, size);
    }

    @Override
    public Long create(Airline entity) {
        return repository.create(entity);
    }

    @Override
    public Long update(Long id, Airline entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
