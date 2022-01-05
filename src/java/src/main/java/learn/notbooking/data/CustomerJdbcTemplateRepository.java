package learn.notbooking.data;

import learn.notbooking.data.Mappers.CustomerMapper;
import learn.notbooking.models.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerJdbcTemplateRepository implements CustomerRepository{

    private final JdbcTemplate jdbcTemplate;

    public CustomerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Customer> findAll() {
        final String sql = "select * "
                + "from Customer ;";

        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    @Override
    public Customer findById(int customerId) {
        final String sql = "select * "
                + "from Customer "
                + "where customer_id = ?;";

        return jdbcTemplate.query(sql, new CustomerMapper(), customerId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Customer add(Customer customer) {
        final String sql = "insert into customer (customer_city, customer_state, customer_address, customer_zipcode) values (?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getCustomerFirstName());
            ps.setString(2, customer.getCustomerLastName());
            ps.setInt(3, customer.getUserId());
            ps.setInt(4, customer.getContactId());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        customer.setCustomerId(keyHolder.getKey().intValue());
        return customer;
    }

    @Override
    public boolean update(Customer customer) {
        final String sql = "update customer set customer_first_name = ?, customer_last_name = ?, user_id = ?, Contact_contact_id = ?  where customer_id = ?;";

        int rowsUpdated = jdbcTemplate.update(sql,
                customer.getCustomerFirstName(), customer.getCustomerLastName(), customer.getUserId(), customer.getContactId(), customer.getCustomerId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int customerId) {
        final String sql = "delete from customer where customer_id = ?;";
        return jdbcTemplate.update(sql, customerId) > 0;
    }
}
