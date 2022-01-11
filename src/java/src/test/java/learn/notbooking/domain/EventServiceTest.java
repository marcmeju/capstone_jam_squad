package learn.notbooking.domain;

import learn.notbooking.data.EventRepository;
import learn.notbooking.models.Event;
import learn.notbooking.models.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EventServiceTest {

    @Autowired
    EventService service;

    @MockBean
    EventRepository eventRepository;

    @Test
    void shouldAdd() {

        Event event = new Event();
        event.setEventType("eventType");
        event.setEventName("EventName");
        event.setEventDate("EventDate");
        event.setEventPrice(150);
        event.setLocationId(1);
        event.setContactId(1);

        Event mockOut = new Event(5, "eventType","EventName","EventDate",150,1,1);

        when(eventRepository.add(event)).thenReturn(mockOut);

        Result<Event> actual = service.add(event);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldUpdate() {
        Event event = new Event();
        event.setEventId(2);
        event.setEventType("updatedEventType");
        event.setEventName("updatedEventName");
        event.setEventDate("20341212");
        event.setEventPrice(12345);
        event.setLocationId(1);
        event.setContactId(1);


        when(eventRepository.update(event)).thenReturn(true);
        Result<Event> actual = service.update(event);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        Event event = new Event();
        event.setEventId(89347985);
        event.setEventType("NotUpdatedEventType");
        event.setEventName("NotUpdatedEventName");
        event.setEventDate("4353452");
        event.setEventPrice(12345);
        event.setLocationId(1);
        event.setContactId(1);

        assertFalse(eventRepository.update(event));

    }

    /* Delete Tests */
    @Test
    void shouldNotDeleteMissing() {
        when(eventRepository.deleteById(35000)).thenReturn(false);
        assertFalse(service.deleteById(35000));
    }

    @Test
    void shouldDelete() {
        when(eventRepository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }
}