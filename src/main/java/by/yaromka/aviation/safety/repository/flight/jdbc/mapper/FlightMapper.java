package by.yaromka.aviation.safety.repository.flight.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airport.Airport;
import by.yaromka.aviation.safety.domain.entity.flight.Flight;
import by.yaromka.aviation.safety.domain.entity.pilot.Pilot;
import by.yaromka.aviation.safety.repository.airport.AirportRepository;
import by.yaromka.aviation.safety.repository.pilot.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class FlightMapper implements RowMapper<Flight> {
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private PilotRepository pilotRepository;

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
        Pilot result = null;
        Optional<Pilot> pilotFromDb = pilotRepository.findById(rs.getLong("first_pilot_id"));
        if (pilotFromDb.isPresent()) {
            result = pilotFromDb.get();
        }
        return result;
    }

    private Pilot getSecondPilot(ResultSet rs) throws SQLException {
        Pilot result = null;
        Optional<Pilot> pilotFromDb = pilotRepository.findById(rs.getLong("second_pilot_id"));
        if (pilotFromDb.isPresent()) {
            result = pilotFromDb.get();
        }
        return result;
    }

    private Airport getTakeOffAirport(ResultSet rs) throws SQLException {
        Airport result = null;
        Optional<Airport> airportFromDb = airportRepository.findById(rs.getLong("take_off_airport_id"));
        if (airportFromDb.isPresent()) {
            result = airportFromDb.get();
        }
        return result;
    }

    private Airport getLandAirport(ResultSet rs) throws SQLException {
        Airport result = null;
        Optional<Airport> airportFromDb = airportRepository.findById(rs.getLong("land_airport_id"));
        if (airportFromDb.isPresent()) {
            result = airportFromDb.get();
        }
        return result;
    }
}
