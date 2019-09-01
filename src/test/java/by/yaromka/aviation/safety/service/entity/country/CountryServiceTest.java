package by.yaromka.aviation.safety.service.entity.country;

import by.yaromka.aviation.safety.repository.country.CountryRepository;
import by.yaromka.aviation.safety.service.entity.country.impl.CountryServiceImpl;
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

public class CountryServiceTest {
    @Mock
    private CountryRepository repository;
    @InjectMocks
    private CountryService service;

    private Country firstCountry;
    private Country secondCountry;
    private Country thirdCountry;

    @Before
    public void setUp() {
        service = new CountryServiceImpl();
        MockitoAnnotations.initMocks(this);

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
        when(repository.findAll()).thenReturn(expected);
        List<Country> actual = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Country> expected = Optional.of(firstCountry);
        when(repository.findById(1L)).thenReturn(expected);
        Optional<Country> actual = service.findById(1L);
        verify(repository, atLeastOnce()).findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Country> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondCountry));
        when(repository.findForPage(1L, 1L)).thenReturn(expected);
        Page<Country> actual = service.findForPage(1L, 1L);
        verify(repository, atLeastOnce()).findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        when(repository.create(thirdCountry)).thenReturn(expected);
        Long actual = service.create(thirdCountry);
        verify(repository, atLeastOnce()).create(thirdCountry);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        when(repository.update(2L, thirdCountry)).thenReturn(expected);
        Long actual = service.update(2L, thirdCountry);
        verify(repository, atLeastOnce()).update(2L, thirdCountry);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, atLeastOnce()).delete(1L);
    }
}