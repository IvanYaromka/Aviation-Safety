package by.yaromka.aviation.safety.repository.pilot;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
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
public class PilotRepositoryTest {
    @Autowired
    private PilotRepository repository;

    private Pilot firstPilot;
    private Pilot secondPilot;
    private Pilot thirdPilot;

    @Before
    public void setUp() {
        Airline firstAirline = new Airline();
        firstAirline.setId(1L);
        firstPilot = new Pilot();
        firstPilot.setId(1L);
        firstPilot.setName("First");
        firstPilot.setSurname("First");
        firstPilot.setAirline(firstAirline);

        Airline secondAirline = new Airline();
        secondAirline.setId(2L);
        secondPilot = new Pilot();
        secondPilot.setId(2L);
        secondPilot.setName("Second");
        secondPilot.setSurname("Second");
        secondPilot.setAirline(secondAirline);

        Airline thirdAirline = new Airline();
        thirdAirline.setId(3L);
        thirdPilot = new Pilot();
        thirdPilot.setId(3L);
        thirdPilot.setName("Third");
        thirdPilot.setSurname("Third");
        thirdPilot.setAirline(thirdAirline);
    }

    @Test
    public void findAll() {
        List<Pilot> expected = Arrays.asList(firstPilot, secondPilot);
        List<Pilot> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Pilot> expected = Optional.of(firstPilot);
        Optional<Pilot> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Pilot> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondPilot));
        Page<Pilot> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdPilot);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdPilot);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}