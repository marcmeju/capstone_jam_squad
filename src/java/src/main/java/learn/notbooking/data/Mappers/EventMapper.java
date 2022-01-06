package learn.notbooking.data.Mappers;

import learn.notbooking.models.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {


    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setEventId(resultSet.getInt("event_id"));
        event.setEventType(resultSet.getString("event_type"));
        event.setEventName(resultSet.getString("event_name"));
        event.setEventDate(resultSet.getString("event_date"));
        event.setEventPrice(resultSet.getInt("event_price"));
        event.setLocationId(resultSet.getInt("location_id"));
        event.setContactId(resultSet.getInt("contact_id"));

        return event;

                    }

       }
