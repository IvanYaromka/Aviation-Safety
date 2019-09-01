package by.yaromka.aviation.safety.service.entity.type.impl;

import by.yaromka.aviation.safety.domain.entity.type.AirType;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.type.AirTypeRepository;
import by.yaromka.aviation.safety.service.entity.type.AirTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirTypeServiceImpl implements AirTypeService {
    @Autowired
    private AirTypeRepository repository;

    @Override
    public List<AirType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<AirType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<AirType> findForPage(Long index, Long size) {
        return repository.findForPage(index, size);
    }

    @Override
    public Long create(AirType entity) {
        return repository.create(entity);
    }

    @Override
    public Long update(Long id, AirType entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
