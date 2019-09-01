package by.yaromka.aviation.safety.repository.flight.jdbc;

import by.yaromka.aviation.safety.repository.flight.FlightRepository;
import by.yaromka.aviation.safety.domain.entity.flight.Flight;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.flight.jdbc.mapper.FlightMapper;
import by.yaromka.aviation.safety.repository.flight.jdbc.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcFlightRepository implements FlightRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private FlightMapper mapper;

    @Override
    public List<Flight> findLastForPilot(Integer count, Long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("pilot_id", id)
                                                               .addValue("limit", count);
        return jdbcTemplate.query(Query.SELECT_LAST_FOR_PILOT, params, mapper);
    }

    @Override
    public List<Flight> findBetweenDateForPilot(LocalDate begin, LocalDate end, Long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("pilot_id", id)
                                                               .addValue("take_off_date", begin)
                                                               .addValue("land_date", end);
        return jdbcTemplate.query(Query.SELECT_BETWEEN_DATE_FOR_PILOT, params, mapper);
    }

    @Override
    public List<Flight> findLastForAirport(Integer count, Long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("airport_id", id)
                                                               .addValue("limit", count);
        return jdbcTemplate.query(Query.SELECT_LAST_FOR_AIRPORT, params, mapper);
    }

    @Override
    public List<Flight> findBetweenDateForAirport(LocalDate begin, LocalDate end, Long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("airport_id", id)
                                                               .addValue("take_off_date", begin)
                                                               .addValue("land_date", end);
        return jdbcTemplate.query(Query.SELECT_BETWEEN_DATE_FOR_AIRPORT, params, mapper);
    }

    @Override
    public List<Flight> findLastForAirline(Integer count, Long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("airline_id", id)
                                                               .addValue("limit", count);
        return jdbcTemplate.query(Query.SELECT_LAST_FOR_AIRLINE, params, mapper);
    }

    @Override
    public List<Flight> findBetweenDateForAirline(LocalDate begin, LocalDate end, Long id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("airline_id", id)
                                                               .addValue("take_off_date", begin)
                                                               .addValue("land_date", end);
        return jdbcTemplate.query(Query.SELECT_BETWEEN_DATE_FOR_AIRLINE, params, mapper);
    }

    @Override
    public List<Flight> findAll() {
        return jdbcTemplate.query(Query.SELECT_ALL, mapper);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        Optional<Flight> result;
        try {
            result = Optional.of(jdbcTemplate.queryForObject(Query.SELECT_BY_ID, params, mapper));
        } catch (DataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Page<Flight> findForPage(Long index, Long size) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("limit", size)
                                                               .addValue("offset", index * size);
        List<Flight> content = jdbcTemplate.query(Query.SELECT_FOR_PAGE, params, mapper);
        long last = this.getLastPage(size);
        return new Page<>(index, last, size, content);
    }

    private long getLastPage(long size) {
        long count = jdbcTemplate.queryForObject(Query.SELECT_COUNT, EmptySqlParameterSource.INSTANCE, Long.class);
        return (long) Math.ceil(count / (double) size) - 1;
    }

    @Override
    public Long create(Flight entity) {
        SqlParameterSource params = this.extractQueryParams(entity);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(Query.INSERT, params, holder);
        return (Long) holder.getKeys().get("id");
    }

    private MapSqlParameterSource extractQueryParams(Flight entity) {
        return new MapSqlParameterSource().addValue("take_off_date", Date.valueOf(entity.getTakeOffDate()))
                                          .addValue("land_date", Date.valueOf(entity.getLandDate()))
                                          .addValue("first_pilot_id", entity.getFirstPilot().getId())
                                          .addValue("second_pilot_id", entity.getSecondPilot().getId())
                                          .addValue("take_off_airport_id", entity.getTakeOffAirport().getId())
                                          .addValue("land_airport_id", entity.getLandAirport().getId())
                                          .addValue("rool", entity.getRool())
                                          .addValue("take_off_pitch", entity.getTakeOffPitch())
                                          .addValue("land_pitch", entity.getLandPitch())
                                          .addValue("g_force", entity.getGforce());
    }

    @Override
    public Long update(Long id, Flight entity) {
        SqlParameterSource params = this.extractQueryParams(entity).addValue("id", id);
        jdbcTemplate.update(Query.UPDATE, params);
        return id;
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(Query.DELETE, params);
    }
}
