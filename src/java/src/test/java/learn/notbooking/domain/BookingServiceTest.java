package learn.notbooking.domain;

import learn.notbooking.data.BookingJdbcTemplateRepository;
import learn.notbooking.data.BookingRepository;
import learn.notbooking.data.KnownGoodState;
import learn.notbooking.models.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookingServiceTest {

    @Autowired
BookingService service;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    void shouldAdd() {
        Booking booking = new Booking();
        booking.setCustomerId(1);
        booking.setPackageId(1);
        booking.setNumOfGuest(1);

        Booking mockOut = new Booking(5,1,1,1);

        when(bookingRepository.add(booking)).thenReturn(mockOut);

        Result<Booking> actual = service.add(booking);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldUpdate() {
        Booking booking = new Booking();
        booking.setBookingId(2);
        booking.setCustomerId(1);
        booking.setPackageId(1);
        booking.setNumOfGuest(600);

        when(bookingRepository.update(booking)).thenReturn(true);
        Result<Booking> actual = service.update(booking);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        Booking booking = new Booking();
        booking.setBookingId(89347985);
        booking.setCustomerId(1);
        booking.setPackageId(1);
        booking.setNumOfGuest(600);


        when(bookingRepository.update(booking)).thenReturn(false);
        Result<Booking> actual = service.update(booking);
        assertEquals(ResultType.NOT_FOUND, actual.getType());

    }


    /* Delete Tests */
    @Test
    void shouldNotDeleteMissingAlias() {
        when(bookingRepository.deleteById(35000)).thenReturn(false);
        assertFalse(service.deleteById(35000));
    }

    @Test
    void shouldDelete() {
        when(bookingRepository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }
}