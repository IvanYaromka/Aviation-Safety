package by.yaromka.aviation.safety.security.auth;

import by.yaromka.aviation.safety.domain.entity.user.User;
import by.yaromka.aviation.safety.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(NoSuchElementException::new);
    }
}
