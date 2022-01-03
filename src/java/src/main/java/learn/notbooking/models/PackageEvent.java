package learn.notbooking.models;

public class PackageEvent {
    private int packageEventId;
    private int eventId;
    private int packageId;

    private Event event;

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
