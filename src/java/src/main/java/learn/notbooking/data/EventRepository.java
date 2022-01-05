package learn.notbooking.data;

import learn.notbooking.models.Event;

import java.util.List;

public interface EventRepository {
    List<Event> findAll();
    Event findById(int eventId);

    Event add(Event event);

    boolean update(Event event);

    boolean deleteById(int eventId);
}
