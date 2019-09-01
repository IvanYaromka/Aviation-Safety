package by.yaromka.aviation.safety.repository.flight;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.entity.flight.Flight;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.domain.util.page.Page;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@JdbcTest
public class FlightRepositoryTest {
    @Autowired
    private FlightRepository repository;
    private Flight firstFlight;
    private Flight secondFlight;
    private Flight thirdFlight;

    @Before
    public void setUp() {
        Pilot firstPilot = new Pilot();
        firstPilot.setId(1L);
        Pilot secondPilot = new Pilot();
        secondPilot.setId(2L);

        Airport takeOffAirport = new Airport();
        takeOffAirport.setId(1L);
        Airport landAirport = new Airport();
        landAirport.setId(2L);

        firstFlight = new Flight();
        firstFlight.setId(1L);
        firstFlight.setTakeOffDate(LocalDate.of(1, 1, 1));
        firstFlight.setLandDate(LocalDate.of(1, 1, 1));
        firstFlight.setFirstPilot(firstPilot);
        firstFlight.setSecondPilot(secondPilot);
        firstFlight.setTakeOffAirport(takeOffAirport);
        firstFlight.setLandAirport(landAirport);
        firstFlight.setRool(1D);
        firstFlight.setTakeOffPitch(1D);
        firstFlight.setLandPitch(1D);
        firstFlight.setGforce(1D);

        secondFlight = new Flight();
        secondFlight.setId(2L);
        secondFlight.setTakeOffDate(LocalDate.of(2, 2, 2));
        secondFlight.setLandDate(LocalDate.of(2, 2, 2));
        secondFlight.setFirstPilot(firstPilot);
        secondFlight.setSecondPilot(secondPilot);
        secondFlight.setTakeOffAirport(takeOffAirport);
        secondFlight.setLandAirport(landAirport);
        secondFlight.setRool(2D);
        secondFlight.setTakeOffPitch(2D);
        secondFlight.setLandPitch(2D);
        secondFlight.setGforce(2D);

        thirdFlight = new Flight();
        thirdFlight.setId(3L);
        thirdFlight.setTakeOffDate(LocalDate.of(3, 3, 3));
        thirdFlight.setLandDate(LocalDate.of(3, 3, 3));
        thirdFlight.setFirstPilot(firstPilot);
        thirdFlight.setSecondPilot(secondPilot);
        thirdFlight.setTakeOffAirport(takeOffAirport);
        thirdFlight.setLandAirport(landAirport);
        thirdFlight.setRool(3D);
        thirdFlight.setTakeOffPitch(3D);
        thirdFlight.setLandPitch(3D);
        thirdFlight.setGforce(3D);
    }

    @Test
    public void findLastForPilot() {
        List<Flight> expected = Collections.singletonList(secondFlight);
        List<Flight> actual = repository.findLastForPilot(1, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findBetweenDateForPilot() {
        List<Flight> expected = Collections.singletonList(secondFlight);
        List<Flight> actual = repository.findBetweenDateForPilot(LocalDate.of(1, 1, 1), LocalDate.of(3, 3, 3), 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findLastForAirport() {
        List<Flight> expected = Collections.singletonList(secondFlight);
        List<Flight> actual = repository.findLastForAirport(1, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findBetweenDateForAirport() {
        List<Flight> expected = Collections.singletonList(secondFlight);
        List<Flight> actual = repository.findBetweenDateForAirport(LocalDate.of(1, 1, 1), LocalDate.of(3, 3, 3), 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findLastForAirline() {
        List<Flight> expected = Collections.singletonList(secondFlight);
        List<Flight> actual = repository.findLastForAirline(1, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findBetweenDateForAirline() {
        List<Flight> expected = Collections.singletonList(secondFlight);
        List<Flight> actual = repository.findBetweenDateForAirline(LocalDate.of(1, 1, 1), LocalDate.of(3, 3, 3), 1L);
        assertEquals(expected, actual);
    }


    @Test
    public void findAll() {
        List<Flight> expected = Arrays.asList(firstFlight, secondFlight);
        List<Flight> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Flight> expected = Optional.of(firstFlight);
        Optional<Flight> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Flight> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondFlight));
        Page<Flight> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdFlight);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdFlight);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}