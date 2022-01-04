package learn.notbooking.data.Mappers;

import learn.notbooking.models.Customer;
import learn.notbooking.models.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerId(resultSet.getInt("customer_id"));
        customer.setCustomerFirstName(resultSet.getString("customer_first_name"));
        customer.setCustomerLastName(resultSet.getString("customer_last_name"));
        customer.setUserId(resultSet.getInt("user_id"));
        customer.setContactId(resultSet.getInt("Contact_contact_id"));

        return customer;
    }
}
