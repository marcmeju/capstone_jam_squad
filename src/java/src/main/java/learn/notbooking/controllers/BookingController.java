    package learn.notbooking.controllers;


    import learn.notbooking.domain.Result;
    import learn.notbooking.domain.ResultType;
    import learn.notbooking.domain.BookingService;
    import learn.notbooking.models.Booking;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @CrossOrigin(origins = {"http://localhost:3000"})
    @RequestMapping("/booking")
    public class BookingController {

        private final BookingService service;

        public BookingController(BookingService service) {
            this.service = service;
        }

        @GetMapping
        public List<Booking> findAll() {

            return service.findAll();

        }

        @GetMapping("/{bookingId}")
        public ResponseEntity<Booking> findById(@PathVariable int bookingId) {
            Booking booking = service.findById(bookingId);
            try{
                if (booking == null) {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(booking, HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/customer/{customerId}")
        public ResponseEntity<List<Booking>> findBookingByCustomerId(@PathVariable int customerId) {
            List<Booking> booking = service.findBookingByCustomerId(customerId);
            try{
                if (booking == null) {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(booking, HttpStatus.OK);
            }catch (Exception ex){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PostMapping
        public ResponseEntity<Booking> add(@RequestBody Booking booking) {
            Result<Booking> result = service.add(booking);
            if (result.getType() == ResultType.INVALID) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        @PutMapping("/{bookingId}")
        public ResponseEntity<Void> update(@PathVariable int bookingId, @RequestBody Booking booking) {

            // id conflict. stop immediately.
            if (bookingId != booking.getBookingId()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // 4. ResultType -> HttpStatus
            try {
                Result<Booking> result = service.update(booking);
                if (result.getType() == ResultType.INVALID) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                } else if (result.getType() == ResultType.NOT_FOUND) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch (Exception ex){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @DeleteMapping("/{bookingId}")
        public ResponseEntity<Void> delete(@PathVariable int bookingId) {
            try{
                if (service.deleteById(bookingId)) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception ex){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
