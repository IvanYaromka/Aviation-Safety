package by.yaromka.aviation.safety.repository.country.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.country.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int i) throws SQLException {
        Country result = new Country();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        return result;
    }
}
