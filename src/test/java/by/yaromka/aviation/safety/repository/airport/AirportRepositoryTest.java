package by.yaromka.aviation.safety.repository.airport;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.airport.Airport;
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
public class AirportRepositoryTest {
    @Autowired
    private AirportRepository repository;

    private Airport firstAirport;
    private Airport secondAirport;
    private Airport thirdAirport;

    @Before
    public void setUp() {
        Country firstCountry = new Country();
        firstCountry.setId(1L);
        firstAirport = new Airport();
        firstAirport.setId(1L);
        firstAirport.setName("First");
        firstAirport.setIcao("1");
        firstAirport.setCountry(firstCountry);
        firstAirport.setCountry(firstCountry);

        Country secondCountry = new Country();
        secondCountry.setId(2L);
        secondAirport = new Airport();
        secondAirport.setId(2L);
        secondAirport.setName("Second");
        secondAirport.setIcao("2");
        secondAirport.setCountry(secondCountry);

        Country thirdCountry = new Country();
        thirdCountry.setId(3L);
        thirdAirport = new Airport();
        thirdAirport.setId(3L);
        thirdAirport.setName("Third");
        thirdAirport.setIcao("3");
        thirdAirport.setCountry(thirdCountry);
    }

    @Test
    public void findAll() {
        List<Airport> expected = Arrays.asList(firstAirport, secondAirport);
        List<Airport> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Airport> expected = Optional.of(firstAirport);
        Optional<Airport> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Airport> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondAirport));
        Page<Airport> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdAirport);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdAirport);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}