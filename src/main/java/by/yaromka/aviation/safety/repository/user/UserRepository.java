package by.yaromka.aviation.safety.repository.user;

import by.yaromka.aviation.safety.repository.Repository;
import by.yaromka.aviation.safety.domain.entity.user.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {
    Optional<User> findByUsername(String username);
}
