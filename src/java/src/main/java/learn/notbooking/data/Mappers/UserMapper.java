package learn.notbooking.data.Mappers;

import learn.notbooking.models.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setPasswordHash(resultSet.getString("password_hash"));
        user.setUserRoleId(resultSet.getInt("user_role_id"));

               return user;
    }
}
