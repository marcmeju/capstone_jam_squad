package learn.notbooking.data;

import learn.notbooking.models.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();
    Customer findById(int customerId);

    Customer add(Customer customer);

    boolean update(Customer customer);

    boolean deleteById(int customerId);
}
