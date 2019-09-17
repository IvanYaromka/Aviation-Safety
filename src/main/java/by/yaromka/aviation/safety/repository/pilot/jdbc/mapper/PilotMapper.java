package by.yaromka.aviation.safety.repository.pilot.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.repository.airline.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class PilotMapper implements RowMapper<Pilot> {
    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    public Pilot mapRow(ResultSet rs, int i) throws SQLException {
        Pilot result = new Pilot();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setSurname(rs.getString("surname"));
        result.setAirline(this.getAirline(rs));
        return result;
    }

    private Airline getAirline(ResultSet rs) throws SQLException {
        Airline result = null;
        Optional<Airline> airlineFromDb = airlineRepository.findById(rs.getLong("airline_id"));
        if (airlineFromDb.isPresent()) {
            result = airlineFromDb.get();
        }
        return result;
    }
}
