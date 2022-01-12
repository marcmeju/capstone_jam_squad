package learn.notbooking.data.Mappers;

import learn.notbooking.models.AppUser;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserMapper implements RowMapper<AppUser> {

    private List<String> roles;

    public UserMapper(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        return new AppUser(resultSet.getInt("user_id")
                , resultSet.getString("user_name")
                , resultSet.getString("password_hash")
                ,resultSet.getInt("user_role_id")
                ,resultSet.getBoolean("disabled"));

    }


    private List<String> convertIdToRoles(int userRoleId){
        if( userRoleId == 1){
            return  roles = List.of("Admin");
        }
        if( userRoleId == 2){
            return  roles = List.of("User");
        }
        return List.of("Invalid user type");

    }
}
