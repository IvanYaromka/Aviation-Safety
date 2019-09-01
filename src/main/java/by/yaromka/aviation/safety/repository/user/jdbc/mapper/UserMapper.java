package by.yaromka.aviation.safety.repository.user.jdbc.mapper;

import by.yaromka.aviation.safety.domain.entity.user.User;
import by.yaromka.aviation.safety.domain.entity.user.role.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User result = new User();
        result.setId(rs.getLong("id"));
        result.setUsername(rs.getString("username"));
        result.setPassword(rs.getString("password"));
        result.setName(rs.getString("name"));
        result.setSurname(rs.getString("surname"));
        result.setAirlineId(rs.getLong("airline_id"));
        result.setRole(Role.valueOf(rs.getInt("role")));
        result.setEnabled(rs.getBoolean("enabled"));
        return result;
    }
}
