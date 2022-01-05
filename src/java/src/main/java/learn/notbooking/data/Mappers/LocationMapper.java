package learn.notbooking.data.Mappers;

import learn.notbooking.models.Location;
import learn.notbooking.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        Location location = new Location();
        location.setLocationId(resultSet.getInt("location_id"));
        location.setLocationCity(resultSet.getString("location_city"));
        location.setLocationState(resultSet.getString("location_state"));
        location.setLocationAddress(resultSet.getString("location_address"));
        location.setLocationZipCode(resultSet.getInt("location_zipcode"));

        return location;
    }
}
