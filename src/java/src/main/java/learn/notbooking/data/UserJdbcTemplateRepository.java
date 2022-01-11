package learn.notbooking.data;

import learn.notbooking.data.Mappers.UserMapper;
import learn.notbooking.models.AppUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    List<String> roles;
    @Override
    public List<AppUser> findAll() {
        final String sql = "select * "
                + "from user ;";

        return jdbcTemplate.query(sql, new UserMapper(roles));
    }

    @Override
    public AppUser findById(int userId) {
        final String sql = "select * "
                + "from user "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new UserMapper(roles), userId)
                .stream()
                .findFirst().orElse(null);
    }

    @Transactional
    public AppUser findByUsername(String username) {
        List<String> roles = getRolesByUsername(username);

        final String sql = "select user_id, user_name, password_hash, disabled "
                + "from user "
                + "where user_name = ?;";

        return jdbcTemplate.query(sql, new UserMapper(roles), username)
                .stream()
                .findFirst().orElse(null);
    }

    @Transactional
    public AppUser create(AppUser user) {

        final String sql = "insert into user (user_name, password_hash) values (?, ?);";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());

        //updateRoles(user);

        return user;
    }

    @Transactional
    public boolean update(AppUser user) {

        final String sql = "update user set "
                + "user_name = ?, "
                + "disabled = ? "
                + "where user_id = ?";

        jdbcTemplate.update(sql,
                user.getUsername(), !user.isEnabled(), user.getUserId());

        //updateRoles(user);
        return false;
    }

    private void updateRoles(AppUser user) {
        // delete all roles, then re-add
        jdbcTemplate.update("delete from user where user_id = ?;", user.getUserId());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if (authorities == null) {
            return;
        }

        for (String role : AppUser.convertAuthoritiesToRoles(authorities)) {
            String sql = "insert into user_role (user_id, user_role_id) "
                    + "select ?, user_role_id from user_role where role_name = ?;";
            jdbcTemplate.update(sql, user.getUserId(), role);
        }
    }

    private List<String> getRolesByUsername(String username) {
        final String sql = "select r.role_name "
                + "from user_role ur "
                + "inner join user_role r on ur.user_role_id = r.user_role_id "
                + "inner join user au on ur.user_id = au.user_id "
                + "where au.user_name = ?";
        return jdbcTemplate.query(sql, (rs, rowId) -> rs.getString("name"), username);
    }

    @Override
    public AppUser add(AppUser user) {
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

//    @Override
//    public boolean update(AppUser user) {
//        final String sql = "update user set user_name = ?, password_hash = ?, user_role_id = ? where user_id = ?;";
//
//        int rowsUpdated = jdbcTemplate.update(sql,
//                user.getUserName(), user.getPasswordHash(), user.getUserRoleId() , user.getUserId());
//
//        return rowsUpdated > 0;
//    }

    @Override
    public boolean deleteById(int userId) {
        final String sql = "delete from user where user_id = ?;";
        return jdbcTemplate.update(sql, userId) > 0;
    }


    private int convertRolesToId(List<String> roles){
        for(String r : roles){
            if(r.equalsIgnoreCase("guest")){
                return 1;
            }
            if(r.equalsIgnoreCase("member")){
                return 2;
            }
            if(r.equalsIgnoreCase("vip")){
                return 3;
            }
            if(r.equalsIgnoreCase("admin")){
                return 4;
            }
        }
        return 0;
    }
}
