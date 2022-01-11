package learn.notbooking.data;

import learn.notbooking.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerJdbcTemplateRepositoryTest {

        @Autowired
        CustomerJdbcTemplateRepository repository;

        @Autowired
        KnownGoodState knownGoodState;

        @BeforeEach
        void setup() {
            knownGoodState.set();
        }


        @Test
        void findAll() {
            List<Customer> all = repository.findAll();
            assertNotNull(all);

            assertTrue(all.size() >= 1);
        }

        @Test
        void findById() {

            Customer customerOne = new Customer(1, "customer1FirstName", "customer1LastName",1,1);
            Customer customerTwo = new Customer(2, "customer2FirstName", "customer2LastName",2,2);

            Customer actual = repository.findById(1);
            assertEquals(customerOne, actual);

            actual = repository.findById(2);
            assertEquals(customerTwo, actual);

            actual = repository.findById(5000);
            assertEquals(null, actual);
        }

        @Test
        void shouldAdd() {
            Customer customer = new Customer();
            customer.setCustomerFirstName("customerFirstName");
            customer.setCustomerLastName("CustomerLastName");
            customer.setUserId(1);
            customer.setContactId(1);

            Customer actual = repository.add(customer);
            customer.setCustomerId(32);
            assertNotNull(actual);
            assertEquals(customer, actual);
        }

        @Test
        void shouldUpdate() {
            Customer customer = new Customer();
            customer.setCustomerId(2);
            customer.setCustomerFirstName("updatedCustomerFirstName");
            customer.setCustomerLastName("updatedCustomerLastName");
            customer.setUserId(1);
            customer.setContactId(1);


            assertTrue(repository.update(customer));
            assertEquals(customer, repository.findById(2));
        }

        @Test
        void shouldNotUpdateMissing() {
            Customer customer = new Customer();
            customer.setCustomerId(89347985);
            customer.setCustomerFirstName("NotUpdatedCustomerFirstName");
            customer.setCustomerLastName("NotUpdatedCustomerLastName");
            customer.setUserId(1);
            customer.setContactId(1);

            assertFalse(repository.update(customer));

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