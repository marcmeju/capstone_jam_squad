package learn.notbooking.domain;

import learn.notbooking.data.CustomerRepository;
import learn.notbooking.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }


    public Customer findById(int customerId){return repository.findById(customerId);}

    public Customer findByUser(String username){return repository.findByUsername(username);}

    public List<Customer> findAll() {
        return repository.findAll();
    }


    public Result<Customer> add(Customer customer) {
        Result<Customer> result = validate(customer);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Customer p = repository.add(customer);
        result.setPayload(p);
        return result;
    }


    public Result<Customer> update(Customer customer) {
        Result<Customer> result = validate(customer);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(customer)) {
            result.addMessage("customer id " + customer.getCustomerId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int customerId) {
        return repository.deleteById(customerId);
    }





    // ********************** validate ************************
    private Result<Customer> validate(Customer customer) {
        Result<Customer> result = new Result<>();

        if (customer == null) {
            result.addMessage("customer cannot be null", ResultType.INVALID);
            return result;
        }

        if (customer.getCustomerFirstName().trim().length() <=0) {
            result.addMessage("Customer first name is required", ResultType.INVALID);
        }

        if (customer.getCustomerLastName().trim().length() <=0) {
            result.addMessage("Customer Last name is required", ResultType.INVALID);
        }

        if (customer.getUserId() <=0) {
            result.addMessage("Customer user ID is required", ResultType.INVALID);
        }
        if (customer.getContactId() <=0  ) {
            result.addMessage("Customer Contact ID is invalid", ResultType.INVALID);
        }

        for(Customer sc : findAll()){
            if (customer == sc){
                result.addMessage("This customer already exists", ResultType.INVALID);
            }

        }


        return result;
    }

}
