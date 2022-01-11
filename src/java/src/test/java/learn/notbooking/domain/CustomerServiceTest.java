package learn.notbooking.domain;

import learn.notbooking.data.CustomerRepository;
import learn.notbooking.models.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerServiceTest {

    @Autowired
    CustomerService service;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    void shouldAdd() {
        Customer customer = new Customer();
        customer.setCustomerFirstName("customerFirstName");
        customer.setCustomerLastName("CustomerLastName");
        customer.setUserId(1);
        customer.setContactId(1);

        Customer mockOut = new Customer(5, "customerFirstName", "CustomerLastName", 1,1);

        when(customerRepository.add(customer)).thenReturn(mockOut);

        Result<Customer> actual = service.add(customer);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldUpdate() {
        Customer customer = new Customer();
        customer.setCustomerId(2);
        customer.setCustomerFirstName("updatedCustomerFirstName");
        customer.setCustomerLastName("updatedCustomerLastName");
        customer.setUserId(1);
        customer.setContactId(1);


        when(customerRepository.update(customer)).thenReturn(true);
        Result<Customer> actual = service.update(customer);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        Customer customer = new Customer();
        customer.setCustomerId(89347985);
        customer.setCustomerFirstName("NotUpdatedCustomerFirstName");
        customer.setCustomerLastName("NotUpdatedCustomerLastName");
        customer.setUserId(1);
        customer.setContactId(1);

        when(customerRepository.update(customer)).thenReturn(false);
        Result<Customer> actual = service.update(customer);
        assertEquals(ResultType.NOT_FOUND, actual.getType());

    }


    /* Delete Tests */
    @Test
    void shouldNotDeleteMissing() {
        when(customerRepository.deleteById(35000)).thenReturn(false);
        assertFalse(service.deleteById(35000));
    }

    @Test
    void shouldDelete() {
        when(customerRepository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }

}