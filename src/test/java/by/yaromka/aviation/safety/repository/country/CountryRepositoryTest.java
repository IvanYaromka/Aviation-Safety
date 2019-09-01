package by.yaromka.aviation.safety.repository.country;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.country.Country;
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
public class CountryRepositoryTest {
    @Autowired
    private CountryRepository repository;

    private Country firstCountry;
    private Country secondCountry;
    private Country thirdCountry;

    @Before
    public void setUp() {
        firstCountry = new Country();
        firstCountry.setId(1L);
        firstCountry.setName("First");

        secondCountry = new Country();
        secondCountry.setId(2L);
        secondCountry.setName("Second");

        thirdCountry = new Country();
        thirdCountry.setId(3L);
        thirdCountry.setName("Third");
    }

    @Test
    public void findAll() {
        List<Country> expected = Arrays.asList(firstCountry, secondCountry);
        List<Country> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Country> expected = Optional.of(firstCountry);
        Optional<Country> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Country> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondCountry));
        Page<Country> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdCountry);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdCountry);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}