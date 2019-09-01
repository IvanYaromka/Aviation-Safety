package by.yaromka.aviation.safety.repository.type.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.type.AirType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AirTypeMapper implements RowMapper<AirType> {
    @Override
    public AirType mapRow(ResultSet rs, int i) throws SQLException {
        AirType result = new AirType();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setRool(rs.getDouble("rool"));
        result.setTakeOffPitch(rs.getDouble("take_off_pitch"));
        result.setLandPitch(rs.getDouble("land_pitch"));
        result.setGforce(rs.getDouble("g_force"));
        return result;
    }
}
