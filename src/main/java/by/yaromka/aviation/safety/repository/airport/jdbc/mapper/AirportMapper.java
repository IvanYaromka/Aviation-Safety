package by.yaromka.aviation.safety.repository.airport.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.entity.country.Country;
import by.yaromka.aviation.safety.repository.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class AirportMapper implements RowMapper<Airport> {
    @Autowired
    private CountryRepository countryRepository;

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
        Country actual = null;
        Optional<Country> result = countryRepository.findById(rs.getLong("country_id"));
        if (result.isPresent()) {
            actual = result.get();
        }
        return actual;
    }
}
