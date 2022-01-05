package learn.notbooking.data.Mappers;

import learn.notbooking.models.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleMapper implements RowMapper<UserRole> {
    @Override
    public UserRole mapRow(ResultSet resultSet, int i) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setUserRoleId(resultSet.getInt("user_role_id"));
        userRole.setRoleName(resultSet.getString("role_name"));

        return userRole;
    }
}
