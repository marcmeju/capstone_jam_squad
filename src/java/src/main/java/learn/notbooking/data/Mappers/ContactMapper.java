package learn.notbooking.data.Mappers;

import learn.notbooking.models.Contact;
import learn.notbooking.models.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setContactId(resultSet.getInt("contact_id"));
        contact.setPhone(resultSet.getString("phone"));
        contact.setEmail(resultSet.getString("email"));
        contact.setContactType(resultSet.getString("contact_type"));

        return contact;
    }
}
