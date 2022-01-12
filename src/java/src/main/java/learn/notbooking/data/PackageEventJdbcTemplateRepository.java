package learn.notbooking.data;


import learn.notbooking.data.PackageEventRepository;
import learn.notbooking.models.PackageEvent;
import learn.notbooking.data.Mappers.PackageEventMapper;
import learn.notbooking.models.PackageEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PackageEventJdbcTemplateRepository implements PackageEventRepository {

    private final JdbcTemplate jdbcTemplate;

    public PackageEventJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public boolean add(PackageEvent packageEvent) {
        final String sql = "insert into Package_event (event_id, package_id) values (?, ?);";


        return jdbcTemplate.update(sql,
                packageEvent.getEventId(),
                packageEvent.getPackageId()) > 0;


    }

    @Override
    public boolean update(PackageEvent packageEvent) {
        final String sql = "update Package_event set event_id = ?, package_id = ? where package_event_id = ?;";

        return jdbcTemplate.update(sql,
                packageEvent.getPackageEventId(),
                packageEvent.getEventId(),
                packageEvent.getPackageId()) > 0;

    }

    @Override
    public boolean deleteByKey(int packageEventId) {
        final String sql = "delete from Package_event where package_event_id = ?;";
        return jdbcTemplate.update(sql, packageEventId) > 0;
    }

}
