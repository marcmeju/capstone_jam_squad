package learn.notbooking.data;

import learn.notbooking.data.Mappers.PackageMapper;
import learn.notbooking.models.Package;
import learn.notbooking.models.Package;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PackageJdbcTemplateRepository implements PackageRepository {
    private final JdbcTemplate jdbcTemplate;

    public PackageJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Package> findAll() {
        final String sql = "select * "
                + "from Package ;";

        return jdbcTemplate.query(sql, new PackageMapper());

    }

    @Override
    public Package findById(int packId) {

        final String sql = "select package_id, package_name, tier_id, package_price "
                + "from Package "
                + "where package_id = ?;";

        return jdbcTemplate.query(sql, new PackageMapper(), packId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Package add(Package pack) {
        final String sql = "insert into Package (package_name, tier_id, package_price) values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pack.getPackageName());
            ps.setInt(2, pack.getTierId());
            ps.setInt(3, pack.getPackagePrice());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        pack.setPackageId(keyHolder.getKey().intValue());
        return pack;
    }

    @Override
    public boolean update(Package pack) {
        final String sql = "update Package set package_name = ?, tier_id = ?, package_price = ?  where package_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                pack.getPackageName(), pack.getTierId(), pack.getPackagePrice(), pack.getPackageId());

        return rowsUpdated > 0;

    }

    @Override
    public boolean deleteById(int packId) {
        final String sql = "delete from Package where package_id = ?;";
        return jdbcTemplate.update(sql, packId) > 0;
    }
}
