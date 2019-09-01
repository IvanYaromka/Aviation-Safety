package by.yaromka.aviation.safety.repository.airplane;

import by.yaromka.aviation.safety.config.Config;
import by.yaromka.aviation.safety.domain.entity.airplane.Airplane;
import by.yaromka.aviation.safety.domain.entity.type.AirType;
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
public class AirplaneRepositoryTest {
    @Autowired
    private AirplaneRepository repository;
    private Airplane firstAirplane;
    private Airplane secondAirplane;
    private Airplane thirdAirplane;

    @Before
    public void setUp() {
        AirType firstType = new AirType();
        firstType.setId(1L);
        firstAirplane = new Airplane();
        firstAirplane.setId(1L);
        firstAirplane.setName("First");
        firstAirplane.setNumber("1");
        firstAirplane.setYear(LocalDate.of(1, 1, 1));
        firstAirplane.setType(firstType);

        AirType secondType = new AirType();
        secondType.setId(2L);
        secondAirplane = new Airplane();
        secondAirplane.setId(2L);
        secondAirplane.setName("Second");
        secondAirplane.setNumber("2");
        secondAirplane.setYear(LocalDate.of(2, 2, 2));
        secondAirplane.setType(secondType);

        AirType thirdType = new AirType();
        thirdType.setId(3L);
        thirdAirplane = new Airplane();
        thirdAirplane.setId(3L);
        thirdAirplane.setName("Third");
        thirdAirplane.setNumber("3");
        thirdAirplane.setYear(LocalDate.of(3, 3, 3));
        thirdAirplane.setType(thirdType);
    }

    @Test
    public void findAll() {
        List<Airplane> expected = Arrays.asList(firstAirplane, secondAirplane);
        List<Airplane> actual = repository.findAll();
        assertEquals(expected, actual);
    }

    @Test
    public void findById() {
        Optional<Airplane> expected = Optional.of(firstAirplane);
        Optional<Airplane> actual = repository.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void findForPage() {
        Page<Airplane> expected = new Page<>(1L, 1L, 1L, Collections.singletonList(secondAirplane));
        Page<Airplane> actual = repository.findForPage(1L, 1L);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Long expected = 3L;
        Long actual = repository.create(thirdAirplane);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Long expected = 2L;
        Long actual = repository.update(2L, thirdAirplane);
        assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        repository.delete(1L);
    }
}