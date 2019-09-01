package by.yaromka.aviation.safety.repository.airline;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.airline.Airline;
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
public class AirlineRepositoryTest {
    @Autowired
    private AirlineRepository repository;
    private Airline firstAirline;
    private Airline secondAirline;
    private Airline thirdAirline;

    @Before
    public void setUp() {
        Country firstCountry = new Country();
        firstCountry.setId(1L);
        firstAirline = new Airline();
        firstAirline.setId(1L);
        firstAirline.setName("First");
        firstAirline.setCountry(firstCountry);

        Country secondCountry = new Country();
        secondCountry.setId(2L);
        secondAirline = new Airline();
        secondAirline.setId(2L);
        secondAirline.setName("Second");
        secondAirline.setCountry(secondCountry);

        Country thirdCountry = new Country();
        thirdCountry.setId(3L);
        thirdAirline = new Airline();
        thirdAirline.setId(1L);
        thirdAirline.setName("Third");
        thirdAirline.setCountry(thirdCountry);
    }

    @Test
    public void findAll() {
        List<Airline> expected = Arrays.asList(firstAirline, secondAirline);
        List<Airline> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Airline> expected = Optional.of(firstAirline);
        Optional<Airline> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Airline> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondAirline));
        Page<Airline> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdAirline);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdAirline);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}