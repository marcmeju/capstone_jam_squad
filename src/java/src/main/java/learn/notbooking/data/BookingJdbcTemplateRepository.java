package learn.notbooking.data;

import learn.notbooking.data.Mappers.BookingMapper;
import learn.notbooking.models.Booking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BookingJdbcTemplateRepository implements BookingRepository{
    private final JdbcTemplate jdbcTemplate;

    public BookingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Booking> findAll() {
        final String sql = "select * "
                + "from Booking ;";

        return jdbcTemplate.query(sql, new BookingMapper());

    }

    @Override
    public Booking findById(int bookingId) {

        final String sql = "select * "
                + "from Booking "
                + "where booking_id = ?;";

        return jdbcTemplate.query(sql, new BookingMapper(), bookingId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Booking add(Booking booking) {
        final String sql = "insert into Booking (package_id, customer_id) values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, booking.getPackageId());
            ps.setInt(2, booking.getCustomerId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        booking.setBookingId(keyHolder.getKey().intValue());
        return booking;
    }

    @Override
    public boolean update(Booking booking) {
        final String sql = "update Booking set package_id = ?, customer_id = ?  where booking_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                booking.getPackageId(), booking.getCustomerId(), booking.getBookingId());

        return rowsUpdated > 0;

    }

    @Override
    public boolean deleteById(int bookingId) {
        final String sql = "delete from Booking where booking_id = ?;";
        return jdbcTemplate.update(sql, bookingId) > 0;
    }

}
