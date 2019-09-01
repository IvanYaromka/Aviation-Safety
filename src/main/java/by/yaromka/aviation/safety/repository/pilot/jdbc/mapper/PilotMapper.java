package by.yaromka.aviation.safety.repository.pilot.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airline.Airline;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PilotMapper implements RowMapper<Pilot> {
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
        Airline result = new Airline();
        result.setId(rs.getLong("airline_id"));
        return result;
    }
}
