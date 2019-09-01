package by.yaromka.aviation.safety.service.entity.pilot;

import by.yaromka.aviation.safety.repository.pilot.PilotRepository;
import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.airline.AirlineService;
import by.yaromka.aviation.safety.service.entity.pilot.impl.PilotServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PilotServiceTest {
    @Mock
    private PilotRepository repository;
    @Mock
    private AirlineService airlineService;
    @InjectMocks
    private PilotService service;

    private Pilot firstPilot;
    private Pilot secondPilot;
    private Pilot thirdPilot;

    @Before
    public void setUp() {
        service = new PilotServiceImpl();
        MockitoAnnotations.initMocks(this);

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
        when(repository.findAll()).thenReturn(expected);
        List<Pilot> actual = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Pilot> expected = Optional.of(firstPilot);
        when(repository.findById(1L)).thenReturn(expected);
        when(airlineService.findById(1L)).thenReturn(Optional.empty());
        Optional<Pilot> actual = service.findById(1L);
        verify(repository, atLeastOnce()).findById(1L);
        verify(airlineService, atLeastOnce()).findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Pilot> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondPilot));
        when(repository.findForPage(1L, 1L)).thenReturn(expected);
        Page<Pilot> actual = service.findForPage(1L, 1L);
        verify(repository, atLeastOnce()).findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        when(repository.create(thirdPilot)).thenReturn(expected);
        Long actual = service.create(thirdPilot);
        verify(repository, atLeastOnce()).create(thirdPilot);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        when(repository.update(2L, thirdPilot)).thenReturn(expected);
        Long actual = service.update(2L, thirdPilot);
        verify(repository, atLeastOnce()).update(2L, thirdPilot);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, atLeastOnce()).delete(1L);
    }
}