package learn.notbooking.models;

public class Event {
    private int eventId;
    private String eventType;
    private String eventName;
    private String eventDate;
    private int eventPrice;
    private int locationId;
    private int contactId;

    public Event() {
    }

    public Event(int eventId, String eventType, String eventName, String eventDate, int eventPrice, int locationId, int contactId) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventPrice = eventPrice;
        this.locationId = locationId;
        this.contactId = contactId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(int eventPrice) {
        this.eventPrice = eventPrice;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
