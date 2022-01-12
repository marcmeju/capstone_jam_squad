package learn.notbooking.domain;


import learn.notbooking.data.EventRepository;
import learn.notbooking.models.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }


    public Event findById(int eventId){return repository.findById(eventId);}


    public List<Event> findAll() {
        return repository.findAll();
    }


    public Result<Event> add(Event event) {
        Result<Event> result = validate(event);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Event p = repository.add(event);
        result.setPayload(p);
        return result;
    }


    public Result<Event> update(Event event) {
        Result<Event> result = validate(event);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(event)) {
            result.addMessage("event id " + event.getEventId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int eventId) {
        return repository.deleteById(eventId);
    }





    // ********************** validate ************************
    private Result<Event> validate(Event event) {
        Result<Event> result = new Result<>();

        if (event == null) {
            result.addMessage("event cannot be null", ResultType.INVALID);
            return result;
        }

        if (event.getEventType().trim().length() <=0) {
            result.addMessage("Event Type is required", ResultType.INVALID);
        }

        if (event.getEventName().trim().length() <=0) {
            result.addMessage("Event name is required", ResultType.INVALID);
        }

        if (event.getEventDate().trim().length() <=0 ) {
            result.addMessage("Event Date is required", ResultType.INVALID);
        }
        if ( LocalDate.parse(event.getEventDate()).isBefore(LocalDate.now())) {
            result.addMessage("Event Date cannot be in the past", ResultType.INVALID);
        }
        if (String.valueOf(event.getEventPrice()).trim().length() < 1 ) {
            result.addMessage("Event price cannot be blank", ResultType.INVALID);
        }
        if (event.getEventPrice() < 0) {
            result.addMessage("Event price cannot be less than being free, C'mon!", ResultType.INVALID);
        }
        if (String.valueOf(event.getLocationId()).trim().length() < 1 || event.getLocationId() <=0  ) {
            result.addMessage("Event Contact ID is invalid", ResultType.INVALID);
        }

        for(Event sc : findAll()){
            if (event == sc){
                result.addMessage("This event already exists", ResultType.INVALID);
            }

        }


        return result;
    }

}
