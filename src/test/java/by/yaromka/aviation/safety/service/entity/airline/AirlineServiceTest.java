package by.yaromka.aviation.safety.service.entity.airline;

import by.yaromka.aviation.safety.repository.airline.AirlineRepository;
import by.yaromka.aviation.safety.service.entity.country.CountryService;
import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import by.yaromka.aviation.safety.domain.entity.country.Country;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.airline.iml.AirlineServiceImpl;
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

public class AirlineServiceTest {
    @Mock
    private AirlineRepository repository;
    @Mock
    private CountryService countryService;
    @InjectMocks
    private AirlineService service;
    private Airline firstAirline;
    private Airline secondAirline;
    private Airline thirdAirline;

    @Before
    public void setUp() {
        service = new AirlineServiceImpl();
        MockitoAnnotations.initMocks(this);

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
        when(repository.findAll()).thenReturn(expected);
        List<Airline> actual = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Airline> expected = Optional.of(firstAirline);
        when(repository.findById(1L)).thenReturn(expected);
        when(countryService.findById(1L)).thenReturn(Optional.empty());
        Optional<Airline> actual = service.findById(1L);
        verify(repository, atLeastOnce()).findById(1L);
        verify(countryService, atLeastOnce()).findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Airline> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondAirline));
        when(repository.findForPage(1L, 1L)).thenReturn(expected);
        Page<Airline> actual = service.findForPage(1L, 1L);
        verify(repository, atLeastOnce()).findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        when(repository.create(thirdAirline)).thenReturn(expected);
        Long actual = service.create(thirdAirline);
        verify(repository, atLeastOnce()).create(thirdAirline);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        when(repository.update(2L, thirdAirline)).thenReturn(expected);
        Long actual = service.update(2L, thirdAirline);
        verify(repository, atLeastOnce()).update(2L, thirdAirline);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, atLeastOnce()).delete(1L);
    }
}