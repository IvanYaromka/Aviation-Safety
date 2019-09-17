package by.yaromka.aviation.safety.repository.airline.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import by.yaromka.aviation.safety.domain.entity.country.Country;
import by.yaromka.aviation.safety.repository.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class AirlineMapper implements RowMapper<Airline> {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Airline mapRow(ResultSet rs, int i) throws SQLException {
        Airline result = new Airline();
        result.setId(rs.getLong("id"));
        result.setCountry(this.mapCountry(rs));
        result.setName(rs.getString("name"));
        return result;
    }

    private Country mapCountry(ResultSet rs) throws SQLException {
        Country actual = null;
        Optional<Country> result = countryRepository.findById(rs.getLong("country_id"));
        if (result.isPresent()) {
            actual = result.get();
        }
        return actual;
    }
}
