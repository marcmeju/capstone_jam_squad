package learn.notbooking.data;

import learn.notbooking.models.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookingJdbcTemplateRepositoryTest {

    @Autowired
    BookingJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<Booking> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {

        Booking bookingOne = new Booking(1, 1, 1,1);
        Booking bookingTwo = new Booking(2, 2,2,2);

        Booking actual = repository.findById(1);
        assertEquals(bookingOne, actual);

        actual = repository.findById(2);
        assertEquals(bookingTwo, actual);

        actual = repository.findById(5000);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        Booking booking = new Booking();
        booking.setCustomerId(1);
        booking.setPackageId(1);
        booking.setNumOfGuest(1);


        Booking actual = repository.add(booking);
        booking.setBookingId(32);
        assertNotNull(actual);
        assertEquals(booking, actual);
    }

    @Test
    void shouldUpdate() {
        Booking booking = new Booking();
        booking.setBookingId(2);
        booking.setCustomerId(1);
        booking.setPackageId(1);
        booking.setNumOfGuest(600);


        assertTrue(repository.update(booking));
        assertEquals(booking, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Booking booking = new Booking();
        booking.setBookingId(89347985);
        booking.setCustomerId(1);
        booking.setPackageId(1);
        booking.setNumOfGuest(600);


        assertFalse(repository.update(booking));

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