package by.yaromka.aviation.safety.repository.type;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.type.AirType;
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
public class AirTypeRepositoryTest {
    @Autowired
    private AirTypeRepository repository;

    private AirType firstAirType;
    private AirType secondAirType;
    private AirType thirdAirType;

    @Before
    public void setUp() {
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
        List<AirType> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<AirType> expected = Optional.of(firstAirType);
        Optional<AirType> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<AirType> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondAirType));
        Page<AirType> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdAirType);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdAirType);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}