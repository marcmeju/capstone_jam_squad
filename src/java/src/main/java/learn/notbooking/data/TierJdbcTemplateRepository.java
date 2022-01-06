package learn.notbooking.data;

import learn.notbooking.data.Mappers.TierMapper;
import learn.notbooking.models.Tier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TierJdbcTemplateRepository implements TierRepository {
    private final JdbcTemplate jdbcTemplate;

    public TierJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Tier> findAll() {
        final String sql = "select * "
                + "from Tier ;";

        return jdbcTemplate.query(sql, new TierMapper());

    }

    @Override
    public Tier findById(int tierId) {

        final String sql = "select tier_id, tier_name, tier_desc"
                + "from Tier "
                + "where tier_id = ?;";

        return jdbcTemplate.query(sql, new TierMapper(), tierId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Tier add(Tier tier) {
        final String sql = "insert into Tier (tier_name, tier_desc) values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tier.getTierName());
            ps.setString(2, tier.getTierDescription());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        tier.setTierId(keyHolder.getKey().intValue());
        return tier;
    }

    @Override
    public boolean update(Tier tier) {
        final String sql = "update Tier set `tier_name` = ?, tier_desc = ?  where tier_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                tier.getTierName(), tier.getTierDescription(), tier.getTierId());

        return rowsUpdated > 0;

    }

    @Override
    public boolean deleteById(int tierId) {
        final String sql = "delete from Tier where tier_id = ?;";
        return jdbcTemplate.update(sql, tierId) > 0;
    }

}
