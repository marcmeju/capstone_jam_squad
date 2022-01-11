package learn.notbooking.models;

public class PackageEvent {
    private int packageEventId;
    private int eventId;
    private int packageId;

    public PackageEvent() {
    }

    public PackageEvent(int packageEventId, int eventId, int packageId) {
        this.packageEventId = packageEventId;
        this.eventId = eventId;
        this.packageId = packageId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    private Event event;
    private Package pack;

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    private Tier tier;

    public int getPackageEventId() {
        return packageEventId;
    }

    public void setPackageEventId(int packageEventId) {
        this.packageEventId = packageEventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
