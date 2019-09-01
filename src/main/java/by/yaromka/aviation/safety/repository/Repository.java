package by.yaromka.aviation.safety.repository;

import by.yaromka.aviation.safety.domain.util.page.Page;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    Page<T> findForPage(Long index, Long size);
    Long create(T entity);
    Long update(Long id, T entity);
    void delete(Long id);
}
