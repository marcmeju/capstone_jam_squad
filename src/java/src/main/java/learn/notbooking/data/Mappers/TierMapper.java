package learn.notbooking.data.Mappers;

import learn.notbooking.models.Package;
import learn.notbooking.models.Tier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TierMapper implements RowMapper<Tier> {

    @Override
    public Tier mapRow(ResultSet resultSet, int i) throws SQLException {
        Tier tier = new Tier();
        tier.setTierId(resultSet.getInt("tier_id"));
        tier.setTierName(resultSet.getString("tier_name"));
        tier.setTierDescription(resultSet.getString("tier_desc"));

        //PackageMapper packageMapper = new PackageMapper();
      //  tier.setPack(packageMapper.mapRow(resultSet, i));

        return tier;
    }
}
