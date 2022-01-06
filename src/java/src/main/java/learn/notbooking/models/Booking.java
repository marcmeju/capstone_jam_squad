package learn.notbooking.models;

public class Booking {
    private int bookingId;
    private int packageId;
    private int customerId;
    private int numOfGuest;

    public int getBookingId() { return bookingId;}

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getNumOfGuest() { return numOfGuest; }

    public void setNumOfGuest(int numOfGuest) { this.numOfGuest = numOfGuest; }
}
