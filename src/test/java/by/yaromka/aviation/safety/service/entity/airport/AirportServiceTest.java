package by.yaromka.aviation.safety.service.entity.airport;

import by.yaromka.aviation.safety.repository.airport.AirportRepository;
import by.yaromka.aviation.safety.service.entity.airport.impl.AirportServiceImpl;
import by.yaromka.aviation.safety.service.entity.country.CountryService;
import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.entity.country.Country;
import by.yaromka.aviation.safety.domain.util.page.Page;
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

public class AirportServiceTest {
    @Mock
    private AirportRepository repository;
    @Mock
    private CountryService countryService;
    @InjectMocks
    private AirportService service;

    private Airport firstAirport;
    private Airport secondAirport;
    private Airport thirdAirport;

    @Before
    public void setUp() {
        service = new AirportServiceImpl();
        MockitoAnnotations.initMocks(this);

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
        when(repository.findAll()).thenReturn(expected);
        List<Airport> actual = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Airport> expected = Optional.of(firstAirport);
        when(repository.findById(1L)).thenReturn(expected);
        when(countryService.findById(1L)).thenReturn(Optional.empty());
        Optional<Airport> actual = service.findById(1L);
        verify(repository, atLeastOnce()).findById(1L);
        verify(countryService, atLeastOnce()).findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Airport> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondAirport));
        when(repository.findForPage(1L, 1L)).thenReturn(expected);
        Page<Airport> actual = service.findForPage(1L, 1L);
        verify(repository, atLeastOnce()).findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        when(repository.create(thirdAirport)).thenReturn(expected);
        Long actual = service.create(thirdAirport);
        verify(repository, atLeastOnce()).create(thirdAirport);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        when(repository.update(2L, thirdAirport)).thenReturn(expected);
        Long actual = service.update(2L, thirdAirport);
        verify(repository, atLeastOnce()).update(2L, thirdAirport);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, atLeastOnce()).delete(1L);
    }
}