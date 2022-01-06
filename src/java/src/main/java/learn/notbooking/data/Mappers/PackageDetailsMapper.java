package learn.notbooking.data.Mappers;

import learn.notbooking.models.Package;
import learn.notbooking.models.PackageDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageDetailsMapper implements RowMapper<PackageDetails> {
    @Override
    public PackageDetails mapRow(ResultSet resultSet, int i) throws SQLException {
        PackageDetails packageDetails = new PackageDetails();

        packageDetails.setPackId(resultSet.getInt("package_id"));
        packageDetails.setPackageName(resultSet.getString("package_name"));
        packageDetails.setEventName(resultSet.getString("event_name"));
        packageDetails.setEventDate(resultSet.getString("event_date"));
        packageDetails.setLocationCity(resultSet.getString("location_city"));
        packageDetails.setLocationState(resultSet.getString("location_state"));
        packageDetails.setContactEmail(resultSet.getString("email"));
        packageDetails.setContactPhone(resultSet.getString("phone"));

        return packageDetails;

    }
}
