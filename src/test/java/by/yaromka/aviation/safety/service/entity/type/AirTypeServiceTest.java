package by.yaromka.aviation.safety.service.entity.type;

import by.yaromka.aviation.safety.repository.type.AirTypeRepository;
import by.yaromka.aviation.safety.domain.entity.type.AirType;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.service.entity.type.impl.AirTypeServiceImpl;
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

public class AirTypeServiceTest {
    @Mock
    private AirTypeRepository repository;
    @InjectMocks
    private AirTypeService service;


    private AirType firstAirType;
    private AirType secondAirType;
    private AirType thirdAirType;

    @Before
    public void setUp() {
        service = new AirTypeServiceImpl();
        MockitoAnnotations.initMocks(this);

        firstAirType = new AirType();
        firstAirType.setId(1L);
        firstAirType.setName("First");
        firstAirType.setRool(1D);
        firstAirType.setTakeOffPitch(1D);
        firstAirType.setLandPitch(1D);
        firstAirType.setGforce(1D);

        secondAirType = new AirType();
        secondAirType.setId(2L);
        secondAirType.setName("Second");
        secondAirType.setRool(2D);
        secondAirType.setTakeOffPitch(2D);
        secondAirType.setLandPitch(2D);
        secondAirType.setGforce(2D);

        thirdAirType = new AirType();
        thirdAirType.setId(3L);
        thirdAirType.setName("Third");
        thirdAirType.setRool(3D);
        thirdAirType.setTakeOffPitch(3D);
        thirdAirType.setLandPitch(3D);
        thirdAirType.setGforce(3D);
    }

    @Test
    public void findAll() {
        List<AirType> expected = Arrays.asList(firstAirType, secondAirType);
        when(repository.findAll()).thenReturn(expected);
        List<AirType> actual = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<AirType> expected = Optional.of(firstAirType);
        when(repository.findById(1L)).thenReturn(expected);
        Optional<AirType> actual = service.findById(1L);
        verify(repository, atLeastOnce()).findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<AirType> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondAirType));
        when(repository.findForPage(1L, 1L)).thenReturn(expected);
        Page<AirType> actual = service.findForPage(1L, 1L);
        verify(repository, atLeastOnce()).findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        when(repository.create(thirdAirType)).thenReturn(expected);
        Long actual = service.create(thirdAirType);
        verify(repository, atLeastOnce()).create(thirdAirType);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        when(repository.update(2L, thirdAirType)).thenReturn(expected);
        Long actual = service.update(2L, thirdAirType);
        verify(repository, atLeastOnce()).update(2L, thirdAirType);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
        verify(repository, atLeastOnce()).delete(1L);
    }
}