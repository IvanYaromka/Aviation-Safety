package by.yaromka.aviation.safety.service.entity.user.impl;

import by.yaromka.aviation.safety.domain.entity.user.User;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.user.UserRepository;
import by.yaromka.aviation.safety.service.entity.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<User> findForPage(Long index, Long size) {
        return repository.findForPage(index, size);
    }

    @Override
    public Long create(User entity) {
        String encodedPassword = encoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
        return repository.create(entity);
    }

    @Override
    public Long update(Long id, User entity) {
        return repository.update(id, entity);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
