package by.yaromka.aviation.safety.repository.airplane.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.airplane.Airplane;
import by.yaromka.aviation.safety.domain.entity.type.AirType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AirplaneMapper implements RowMapper<Airplane> {
    @Override
    public Airplane mapRow(ResultSet rs, int i) throws SQLException {
        Airplane result = new Airplane();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setNumber(rs.getString("number"));
        result.setYear(rs.getDate("year").toLocalDate());
        result.setType(this.getType(rs));
        return result;
    }

    private AirType getType(ResultSet rs) throws SQLException {
        AirType result = new AirType();
        result.setId(rs.getLong("type_id"));
        return result;
    }
}
