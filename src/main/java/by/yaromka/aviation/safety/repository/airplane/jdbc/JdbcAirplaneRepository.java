package by.yaromka.aviation.safety.repository.airplane.jdbc;

import by.yaromka.aviation.safety.repository.airplane.AirplaneRepository;
import by.yaromka.aviation.safety.domain.entity.airplane.Airplane;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.airplane.jdbc.mapper.AirplaneMapper;
import by.yaromka.aviation.safety.repository.airplane.jdbc.query.Query;
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
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAirplaneRepository implements AirplaneRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private AirplaneMapper mapper;

    @Override
    public List<Airplane> findAll() {
        return jdbcTemplate.query(Query.SELECT_ALL, mapper);
    }

    @Override
    public Optional<Airplane> findById(Long id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        Optional<Airplane> result;
        try {
            result = Optional.of(jdbcTemplate.queryForObject(Query.SELECT_BY_ID, params, mapper));
        } catch (DataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Page<Airplane> findForPage(Long index, Long size) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("limit", size)
                                                               .addValue("offset", index * size);
        List<Airplane> content = jdbcTemplate.query(Query.SELECT_FOR_PAGE, params, mapper);
        long last = this.getLastPage(size);
        return new Page<>(index, last, size, content);
    }

    private long getLastPage(long size) {
        long count = jdbcTemplate.queryForObject(Query.SELECT_COUNT, EmptySqlParameterSource.INSTANCE, Long.class);
        return (long) Math.ceil(count / (double) size) - 1;
    }

    @Override
    public Long create(Airplane entity) {
        SqlParameterSource params = this.extractQueryParams(entity);
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(Query.INSERT, params, holder);
        return (Long) holder.getKeys().get("id");
    }

    private MapSqlParameterSource extractQueryParams(Airplane entity) {
        return new MapSqlParameterSource().addValue("name", entity.getName())
                                          .addValue("number", entity.getNumber())
                                          .addValue("year", Date.valueOf(entity.getYear()))
                                          .addValue("type_id", entity.getType().getId());
    }

    @Override
    public Long update(Long id, Airplane entity) {
        SqlParameterSource params = this.extractQueryParams(entity).addValue("id", entity.getId());
        jdbcTemplate.update(Query.UPDATE, params);
        return id;
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(Query.DELETE, params);
    }
}
