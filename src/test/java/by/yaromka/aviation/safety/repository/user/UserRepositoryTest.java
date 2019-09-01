package by.yaromka.aviation.safety.repository.user;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.user.User;
import by.yaromka.aviation.safety.domain.entity.user.role.Role;
import by.yaromka.aviation.safety.domain.util.page.Page;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@JdbcTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    private User firstUser;
    private User secondUser;
    private User thirdUser;

    @Before
    public void setUp() {
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
        List<User> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<User> expected = Optional.of(firstUser);
        Optional<User> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<User> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondUser));
        Page<User> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdUser);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdUser);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}