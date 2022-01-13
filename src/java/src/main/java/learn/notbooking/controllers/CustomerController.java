package learn.notbooking.controllers;


import learn.notbooking.domain.Result;
import learn.notbooking.domain.ResultType;
import learn.notbooking.domain.CustomerService;
import learn.notbooking.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> findAll() {

        return service.findAll();

    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById(@PathVariable int customerId) {
        Customer customer = service.findById(customerId);
        try{
            if (customer == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<Customer> findByUsername(@PathVariable String username){
        Customer customer = service.findByUser(username);
        try{
            if (customer == null){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        Result<Customer> result = service.add(customer);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> update(@PathVariable int customerId, @RequestBody Customer customer) {

        // id conflict. stop immediately.
        if (customerId != customer.getCustomerId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 4. ResultType -> HttpStatus
        try {
            Result<Customer> result = service.update(customer);
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

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable int customerId) {
        try{
            if (service.deleteById(customerId)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
