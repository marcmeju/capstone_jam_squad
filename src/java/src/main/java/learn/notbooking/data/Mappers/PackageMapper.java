package learn.notbooking.data.Mappers;

import learn.notbooking.models.Package;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageMapper implements RowMapper<Package> {
    @Override
    public Package mapRow(ResultSet resultSet, int i) throws SQLException {
        Package pack = new Package();
        pack.setPackageId(resultSet.getInt("package_id"));
        pack.setPackageName(resultSet.getString("package_name"));
        pack.setTierId(resultSet.getInt("tier_id"));
        pack.setPackagePrice(resultSet.getInt("package_price"));
        return pack;
    }
}
