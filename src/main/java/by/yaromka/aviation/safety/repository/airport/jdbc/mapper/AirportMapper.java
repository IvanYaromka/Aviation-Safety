package by.yaromka.aviation.safety.repository.airport.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.entity.country.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AirportMapper implements RowMapper<Airport> {
    @Override
    public Airport mapRow(ResultSet rs, int i) throws SQLException {
        Airport result = new Airport();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setIcao(rs.getString("icao"));
        result.setCountry(this.getCountry(rs));
        return result;
    }

    private Country getCountry(ResultSet rs) throws SQLException {
        Country result = new Country();
        result.setId(rs.getLong("country_id"));
        return result;
    }
}
