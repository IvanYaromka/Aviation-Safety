package by.yaromka.aviation.safety.service.entity.user;

import by.yaromka.aviation.safety.repository.user.UserRepository;
import by.yaromka.aviation.safety.domain.entity.user.User;
import by.yaromka.aviation.safety.domain.entity.user.role.Role;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.user.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;

    private User firstUser;
    private User secondUser;
    private User thirdUser;

    @Before
    public void setUp() {
        service = new UserServiceImpl();
        MockitoAnnotations.initMocks(this);

        firstUser = new User();
        firstUser.setId(1L);
        firstUser.setUsername("First");
        firstUser.setPassword("First");
        firstUser.setName("First");
        firstUser.setSurname("First");
        firstUser.setAirlineId(1L);
        firstUser.setRole(Role.ADMIN);
        firstUser.setEnabled(true);

        secondUser = new User();
        secondUser.setId(2L);
        firstUser.setUsername("Second");
        firstUser.setPassword("Second");
        secondUser.setName("Second");
        secondUser.setSurname("Second");
        secondUser.setAirlineId(2L);
        secondUser.setRole(Role.SUPPLIER);
        secondUser.setEnabled(true);

        thirdUser = new User();
        thirdUser.setId(3L);
        thirdUser.setUsername("Third");
        thirdUser.setPassword("Third");
        thirdUser.setName("Third");
        thirdUser.setSurname("Third");
        thirdUser.setAirlineId(3L);
        thirdUser.setRole(Role.ANALYST);
        thirdUser.setEnabled(true);
    }


    @Test
    public void findAll() {
        List<User> expected = Arrays.asList(firstUser, secondUser);
        when(repository.findAll()).thenReturn(expected);
        List<User> actual = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<User> expected = Optional.of(firstUser);
        when(repository.findById(1L)).thenReturn(expected);
        Optional<User> actual = service.findById(1L);
        verify(repository, atLeastOnce()).findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<User> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondUser));
        when(repository.findForPage(1L, 1L)).thenReturn(expected);
        Page<User> actual = service.findForPage(1L, 1L);
        verify(repository, atLeastOnce()).findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        when(repository.create(thirdUser)).thenReturn(expected);
        Long actual = service.create(thirdUser);
        verify(repository, atLeastOnce()).create(thirdUser);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        when(repository.update(2L, thirdUser)).thenReturn(expected);
        Long actual = service.update(2L, thirdUser);
        verify(repository, atLeastOnce()).update(2L, thirdUser);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, atLeastOnce()).delete(1L);
    }
}