package learn.notbooking.data;

import learn.notbooking.data.Mappers.UserMapper;
import learn.notbooking.data.Mappers.UserMapper;
import learn.notbooking.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select * "
                + "from user ;";

        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findById(int userId) {
        final String sql = "select * "
                + "from user "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public User add(User user) {
        final String sql = "insert into user (user_name, password_hash, user_role_id) values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPasswordHash());
            ps.setInt(3,user.getUserRoleId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        user.setUserId( keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {
        final String sql = "update user set user_name = ?, password_hash = ?, user_role_id = ? where user_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                user.getUserName(), user.getPasswordHash(), user.getUserRoleId() , user.getUserId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int userId) {
        final String sql = "delete from user where user_id = ?;";
        return jdbcTemplate.update(sql, userId) > 0;
    }
}
