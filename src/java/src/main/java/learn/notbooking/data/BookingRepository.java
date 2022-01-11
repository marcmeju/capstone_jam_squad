package learn.notbooking.data;

import learn.notbooking.models.Booking;

import java.util.List;

public interface BookingRepository {
    List<Booking> findAll();
    Booking findById(int bookingId);
    public Booking findBookingByCustomerId(int customerId);
    Booking add(Booking booking);

    boolean update(Booking booking);

    boolean deleteById(int bookingId);
}
