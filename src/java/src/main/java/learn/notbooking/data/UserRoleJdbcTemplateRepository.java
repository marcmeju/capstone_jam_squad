package learn.notbooking.data;

import learn.notbooking.data.Mappers.UserMapper;
import learn.notbooking.data.Mappers.UserRoleMapper;
import learn.notbooking.models.User;
import learn.notbooking.models.UserRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRoleJdbcTemplateRepository implements UserRoleRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserRoleJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserRole> findAll() {
        final String sql = "select * "
                + "from user_role ;";

        return jdbcTemplate.query(sql, new UserRoleMapper());
    }

    @Override
    public UserRole findById(int userRoleId) {
        final String sql = "select * "
                + "from user_role "
                + "where user_role_id = ?;";

        return jdbcTemplate.query(sql, new UserRoleMapper(), userRoleId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public UserRole add(UserRole userRole) {
        final String sql = "insert into user_role (role_name) values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userRole.getRoleName());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        userRole.setUserRoleId( keyHolder.getKey().intValue());
        return userRole;
    }

    @Override
    public boolean update(UserRole userRole) {
        final String sql = "update user_role set role_name = ? where user_role_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                userRole.getRoleName(), userRole.getUserRoleId() );

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int userRoleId) {
        final String sql = "delete from user_role where user_role_id = ?;";
        return jdbcTemplate.update(sql, userRoleId) > 0;
    }
}
