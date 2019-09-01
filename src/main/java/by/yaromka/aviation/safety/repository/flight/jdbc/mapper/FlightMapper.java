package by.yaromka.aviation.safety.repository.flight.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.entity.flight.Flight;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FlightMapper implements RowMapper<Flight> {
    @Override
    public Flight mapRow(ResultSet rs, int i) throws SQLException {
        Flight result = new Flight();
        result.setId(rs.getLong("id"));
        result.setTakeOffDate(rs.getDate("take_off_date").toLocalDate());
        result.setLandDate(rs.getDate("land_date").toLocalDate());
        result.setFirstPilot(this.getFirstPilot(rs));
        result.setSecondPilot(this.getSecondPilot(rs));
        result.setTakeOffAirport(this.getTakeOffAirport(rs));
        result.setLandAirport(this.getLandAirport(rs));
        result.setRool(rs.getDouble("rool"));
        result.setTakeOffPitch(rs.getDouble("take_off_pitch"));
        result.setLandPitch(rs.getDouble("land_pitch"));
        result.setGforce(rs.getDouble("g_force"));
        return result;
    }

    private Pilot getFirstPilot(ResultSet rs) throws SQLException {
        Pilot result = new Pilot();
        result.setId(rs.getLong("first_pilot_id"));
        return result;
    }

    private Pilot getSecondPilot(ResultSet rs) throws SQLException {
        Pilot result = new Pilot();
        result.setId(rs.getLong("second_pilot_id"));
        return result;
    }

    private Airport getTakeOffAirport(ResultSet rs) throws SQLException {
        Airport result = new Airport();
        result.setId(rs.getLong("take_off_airport_id"));
        return result;
    }

    private Airport getLandAirport(ResultSet rs) throws SQLException {
        Airport result = new Airport();
        result.setId(rs.getLong("land_airport_id"));
        return result;
    }
}
