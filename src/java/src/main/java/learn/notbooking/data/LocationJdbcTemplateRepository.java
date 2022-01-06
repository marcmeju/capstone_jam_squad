package learn.notbooking.data;

import learn.notbooking.data.Mappers.LocationMapper;
import learn.notbooking.data.Mappers.PackageMapper;
import learn.notbooking.data.Mappers.TierMapper;
import learn.notbooking.models.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LocationJdbcTemplateRepository implements LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    public LocationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Location> findAll() {
        final String sql = "select * "
                + "from Location ;";

        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    public Location findById(int locationId) {
        final String sql = "select * "
                + "from Location "
                + "where location_id = ?;";

        return jdbcTemplate.query(sql, new LocationMapper(), locationId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Location add(Location location) {
        final String sql = "insert into Location (location_city, location_state, location_address, location_zipcode) values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, location.getLocationCity());
            ps.setString(2, location.getLocationState());
            ps.setString(3, location.getLocationAddress());
            ps.setInt(4, location.getLocationZipCode());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

    @Override
    public boolean update(Location location) {
        final String sql = "update Location set location_city = ?, location_state = ?, location_address = ?, location_zipcode = ?  where location_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                location.getLocationCity(), location.getLocationState(), location.getLocationAddress(), location.getLocationZipCode(), location.getLocationId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int locationId) {
        final String sql = "delete from Location where location_id = ?;";
        return jdbcTemplate.update(sql, locationId) > 0;
    }
}
