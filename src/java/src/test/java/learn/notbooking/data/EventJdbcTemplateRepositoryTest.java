package learn.notbooking.data;

import learn.notbooking.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EventJdbcTemplateRepositoryTest {


    @Autowired
    EventJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<Event> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {

        Event eventOne = new Event(1, "event1Address", "event1City","event1Date",12345,1,1);
        Event eventTwo = new Event(2, "event2Address", "event2City","event2Date",21534,1,1);

        Event actual = repository.findById(1);
        assertEquals(eventOne, actual);

        actual = repository.findById(2);
        assertEquals(eventTwo, actual);

        actual = repository.findById(5000);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {

        Event event = new Event();
        event.setEventType("eventType");
        event.setEventName("EventName");
        event.setEventDate("EventDate");
        event.setEventPrice(150);
        event.setLocationId(1);
        event.setContactId(1);

        Event actual = repository.add(event);
        event.setEventId(32);
        assertNotNull(actual);
        assertEquals(event, actual);
    }

    @Test
    void shouldUpdate() {
        Event event = new Event();
        event.setEventId(2);
        event.setEventType("updatedEventType");
        event.setEventName("updatedEventName");
        event.setEventDate("Event Date");
        event.setEventPrice(12345);
        event.setLocationId(1);
        event.setContactId(1);


        assertTrue(repository.update(event));
        assertEquals(event, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Event event = new Event();
        event.setEventId(89347985);
        event.setEventType("NotUpdatedEventType");
        event.setEventName("NotUpdatedEventName");
        event.setEventDate("NotUpdatedEventDate");
        event.setEventPrice(12345);
        event.setLocationId(1);
        event.setContactId(1);

        assertFalse(repository.update(event));

    }


    @Test
    void shouldDeleteExisting() {
        assertTrue(repository.deleteById(2));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(40000));
    }

}