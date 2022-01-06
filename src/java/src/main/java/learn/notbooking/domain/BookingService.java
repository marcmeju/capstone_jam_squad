package learn.notbooking.domain;

import learn.notbooking.data.BookingRepository;
import learn.notbooking.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }


    public Booking findById(int bookingId){return repository.findById(bookingId);}


    public List<Booking> findAll() {
        return repository.findAll();
    }


    public Result<Booking> add(Booking booking) {
        Result<Booking> result = validate(booking);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Booking p = repository.add(booking);
        result.setPayload(p);
        return result;
    }


    public Result<Booking> update(Booking booking) {
        Result<Booking> result = validate(booking);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(booking)) {
            result.addMessage("booking id " + booking.getBookingId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int bookingId) {
        return repository.deleteById(bookingId);
    }





    // ********************** validate ************************
    private Result<Booking> validate(Booking booking) {
        Result<Booking> result = new Result<>();

        if (booking == null) {
            result.addMessage("booking cannot be null", ResultType.INVALID);
            return result;
        }

        if (booking.getPackageId() <=0) {
            result.addMessage("Package ID is required", ResultType.INVALID);
        }

        if (booking.getCustomerId() <=0) {
            result.addMessage("Customer ID is required", ResultType.INVALID);
        }

        if (booking.getNumOfGuest() <=0) {
            result.addMessage("Valid number of guests is required", ResultType.INVALID);
        }

        for(Booking sc : findAll()){
            if (booking == sc){
                result.addMessage("You have made this booking already", ResultType.INVALID);
            }
        }


        return result;
    }

}
