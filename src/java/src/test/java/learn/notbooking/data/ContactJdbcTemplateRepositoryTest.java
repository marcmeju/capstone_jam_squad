package learn.notbooking.data;

import learn.notbooking.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ContactJdbcTemplateRepositoryTest {


    @Autowired
    ContactJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void findAll() {
        List<Contact> all = repository.findAll();
        assertNotNull(all);

        assertTrue(all.size() >= 1);
    }

    @Test
    void findById() {


        Contact contactOne = new Contact(1, "312456789", "contactName1@notexisting.com", "Event");
        Contact contactTwo = new Contact(2, "5174569780","contactName2@existingnot.com", "Customer");

        Contact actual = repository.findById(1);
        assertEquals(contactOne, actual);

        actual = repository.findById(2);
        assertEquals(contactTwo, actual);

        actual = repository.findById(5000);
        assertEquals(null, actual);
    }

    @Test
    void shouldAdd() {
        Contact contact = new Contact();
        contact.setPhone("312456789");
        contact.setEmail("newcontact@newemail.com");
        contact.setContactType("Event");

        Contact actual = repository.add(contact);
        contact.setContactId(32);
        assertNotNull(actual);
        assertEquals(contact, actual);
    }

    @Test
    void shouldUpdate() {
        Contact contact = new Contact();
        contact.setContactId(2);
        contact.setPhone("312456789");
        contact.setEmail("updatedContact@email.com");
        contact.setContactType("Updated Contact Type");


        assertTrue(repository.update(contact));
        assertEquals(contact, repository.findById(2));
    }

    @Test
    void shouldNotUpdateMissing() {
        Contact contact = new Contact();
        contact.setContactId(89347985);
        contact.setPhone("312456789");
        contact.setEmail("notupdatedContact@email.com");
        contact.setContactType("Not Updated Contact Type");


        assertFalse(repository.update(contact));

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