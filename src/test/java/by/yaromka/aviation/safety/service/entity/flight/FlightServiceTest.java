package by.yaromka.aviation.safety.service.entity.flight;

import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.entity.flight.Flight;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.flight.FlightRepository;
import by.yaromka.aviation.safety.service.entity.airport.AirportService;
import by.yaromka.aviation.safety.service.entity.flight.impl.FlightServiceImpl;
import by.yaromka.aviation.safety.service.entity.pilot.PilotService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class FlightServiceTest {
    @Mock
    private FlightRepository repository;
    @Mock
    private PilotService pilotService;
    @Mock
    private AirportService airportService;
    @InjectMocks
    private FlightService service;

    private Flight firstFlight;
    private Flight secondFlight;
    private Flight thirdFlight;

    @Before
    public void setUp() {
        service = new FlightServiceImpl();
        MockitoAnnotations.initMocks(this);

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
    public void findAll() {
        List<Flight> expected = Arrays.asList(firstFlight, secondFlight);
        when(repository.findAll()).thenReturn(expected);
        List<Flight> actual = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Flight> expected = Optional.of(firstFlight);
        when(repository.findById(1L)).thenReturn(expected);
        when(pilotService.findById(anyLong())).thenReturn(Optional.empty());
        when(airportService.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Flight> actual = service.findById(1L);
        verify(repository, atLeastOnce()).findById(1L);
        verify(pilotService, atLeastOnce()).findById(anyLong());
        verify(airportService, atLeastOnce()).findById(anyLong());
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Flight> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondFlight));
        when(repository.findForPage(1L, 1L)).thenReturn(expected);
        Page<Flight> actual = service.findForPage(1L, 1L);
        verify(repository, atLeastOnce()).findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        when(repository.create(thirdFlight)).thenReturn(expected);
        Long actual = service.create(thirdFlight);
        verify(repository, atLeastOnce()).create(thirdFlight);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        when(repository.update(2L, thirdFlight)).thenReturn(expected);
        Long actual = service.update(2L, thirdFlight);
        verify(repository, atLeastOnce()).update(2L, thirdFlight);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, atLeastOnce()).delete(1L);
    }
}