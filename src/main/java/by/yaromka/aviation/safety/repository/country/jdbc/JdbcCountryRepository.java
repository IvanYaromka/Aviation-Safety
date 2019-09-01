package by.yaromka.aviation.safety.repository.country.jdbc;

import by.yaromka.aviation.safety.repository.country.CountryRepository;
import by.yaromka.aviation.safety.domain.entity.country.Country;
import by.yaromka.aviation.safety.domain.util.page.Page;
import by.yaromka.aviation.safety.repository.country.jdbc.mapper.CountryMapper;
import by.yaromka.aviation.safety.repository.country.jdbc.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCountryRepository implements CountryRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private CountryMapper mapper;

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(Query.SELECT_ALL, mapper);
    }

    @Override
    public Optional<Country> findById(Long id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        Optional<Country> result;
        try {
            result = Optional.of(jdbcTemplate.queryForObject(Query.SELECT_BY_ID, params, mapper));
        } catch (DataAccessException e) {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Page<Country> findForPage(Long index, Long size) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("limit", size)
                                                               .addValue("offset", index * size);
        List<Country> content = jdbcTemplate.query(Query.SELECT_FOR_PAGE, params, mapper);
        long last = this.getLastPage(size);
        return new Page<>(index, last, size, content);
    }

    private long getLastPage(long size) {
        long count = jdbcTemplate.queryForObject(Query.SELECT_COUNT, EmptySqlParameterSource.INSTANCE, Long.class);
        return (long) Math.ceil(count / (double) size) - 1;
    }

    @Override
    public Long create(Country entity) {
        SqlParameterSource params = new MapSqlParameterSource("name", entity.getName());
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(Query.INSERT, params, holder);
        return (Long) holder.getKeys().get("id");
    }

    private MapSqlParameterSource extractQueryParams(Country entity) {
        return new MapSqlParameterSource("name", entity.getName());
    }

    @Override
    public Long update(Long id, Country entity) {
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
