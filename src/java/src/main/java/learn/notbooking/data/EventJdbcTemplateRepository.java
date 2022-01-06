package learn.notbooking.data;

import learn.notbooking.data.Mappers.EventMapper;
import learn.notbooking.models.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EventJdbcTemplateRepository implements EventRepository{

    private final JdbcTemplate jdbcTemplate;

    public EventJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Event> findAll() {
        final String sql = "select * "
                + "from Event ;";

        return jdbcTemplate.query(sql, new EventMapper());
    }

    @Override
    public Event findById(int eventId) {
        final String sql = "select * "
                + "from Event "
                + "where event_id = ?;";

        return jdbcTemplate.query(sql, new EventMapper(), eventId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Event add(Event event) {
        final String sql = "insert into Event (event_type, event_name, event_date, event_price, location_id, contact_id) values (?, ?, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, event.getEventType());
            ps.setString(2, event.getEventName());
            ps.setString(3, event.getEventDate());
            ps.setInt(4, event.getEventPrice());
            ps.setInt(5, event.getLocationId());
            ps.setInt(6, event.getContactId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        event.setEventId(keyHolder.getKey().intValue());
        return event;
    }

    @Override
    public boolean update(Event event) {
        final String sql = "update Event set event_type = ?, event_name = ?, event_date = ?, event_price = ? , location_id = ?, contact_id = ?  where event_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                event.getEventType(), event.getEventName(), event.getEventDate(), event.getEventPrice(), event.getLocationId(), event.getContactId(), event.getEventId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int eventId) {
        final String sql = "delete from Event where event_id = ?;";
        return jdbcTemplate.update(sql, eventId) > 0;
    }

}
