package by.yaromka.aviation.safety.service.entity.airport.impl;

import by.yaromka.aviation.safety.service.entity.airport.AirportService;
import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.airport.AirportRepository;
import by.yaromka.aviation.safety.service.entity.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    private AirportRepository repository;
    @Autowired
    private CountryService countryService;

    @Override
    public List<Airport> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Airport> findById(Long id) {
        Optional<Airport> result = repository.findById(id);
        if (result.isPresent()) {
            Airport airport = result.get();
            countryService.findById(airport.getId()).ifPresent(airport::setCountry);
        }
        return result;
    }

    @Override
    public Page<Airport> findForPage(Long index, Long size) {
        return repository.findForPage(index, size);
    }

    @Override
    public Long create(Airport entity) {
        return repository.create(entity);
    }

    @Override
    public Long update(Long id, Airport entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
