package learn.notbooking.data.Mappers;

import learn.notbooking.models.Booking;
import learn.notbooking.models.Booking;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(resultSet.getInt("booking_id"));
        booking.setPackageId(resultSet.getInt("package_id"));
        booking.setCustomerId(resultSet.getInt("customer_id"));
        booking.setNumOfGuest(resultSet.getInt("num_of_guest"));
        return booking;
    }
}
