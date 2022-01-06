package learn.notbooking.data;

import learn.notbooking.data.Mappers.ContactMapper;
import learn.notbooking.models.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ContactJdbcTemplateRepository implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContactJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Contact> findAll() {
        final String sql = "select * "
                + "from Contact ;";

        return jdbcTemplate.query(sql, new ContactMapper());
    }

    @Override
    public Contact findById(int contactId) {
        final String sql = "select * "
                + "from Contact "
                + "where contact_id = ?;";

        return jdbcTemplate.query(sql, new ContactMapper(), contactId)
                .stream()
                .findFirst().orElse(null);
    }

    @Override
    public Contact add(Contact contact) {
        final String sql = "insert into Contact (phone, email, contact_type) values (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getPhone());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getContactType());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;}
        contact.setContactId(keyHolder.getKey().intValue());
        return contact;
    }

    @Override
    public boolean update(Contact contact) {
        final String sql = "update Contact set phone = ?, email = ?, contact_type = ?  where contact_id = ?;";

  //      System.out.println("Printing out ---> " + contact.getPhone() + contact.getEmail() + contact.getContactType() + contact.getContactId());
        int rowsUpdated = jdbcTemplate.update(sql,
                contact.getPhone(), contact.getEmail(), contact.getContactType(), contact.getContactId());

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(int contactId) {
        final String sql = "delete from Contact where contact_id = ?;";
        return jdbcTemplate.update(sql, contactId) > 0;
    }

}
