package learn.notbooking.data.Mappers;

import learn.notbooking.models.PackageEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageEventMapper implements RowMapper<PackageEvent> {

    @Override
    public PackageEvent mapRow(ResultSet resultSet, int i) throws SQLException {

        PackageEvent packageEvent = new PackageEvent();
        packageEvent.setPackageEventId(resultSet.getInt("package_event_id"));
        packageEvent.setEventId(resultSet.getInt("event_id"));
        packageEvent.setPackageId(resultSet.getInt("package_id"));

        TierMapper tierMapper = new TierMapper();
        packageEvent.setTier(tierMapper.mapRow(resultSet, i));

        EventMapper eventMapper = new EventMapper();
        packageEvent.setEvent(eventMapper.mapRow(resultSet, i));

        return packageEvent;
    }
}
