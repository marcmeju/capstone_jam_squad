package learn.notbooking.domain;

import learn.notbooking.data.ContactRepository;
import learn.notbooking.domain.ContactService;
import learn.notbooking.data.ContactRepository;
import learn.notbooking.models.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ContactServiceTest {

    @Autowired
    ContactService service;

    @MockBean
    ContactRepository contactRepository;

    @Test
    void shouldAdd() {
        Contact contact = new Contact();
        contact.setPhone("312456789");
        contact.setEmail("newcontact@newemail.com");
        contact.setContactType("Event");

        Contact mockOut = new Contact(3, "312456789","newcontact@newemail.com", "Event" );

        when(contactRepository.add(contact)).thenReturn(mockOut);

        Result<Contact> actual = service.add(contact);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldUpdate() {
        Contact contact = new Contact();
        contact.setContactId(2);
        contact.setPhone("312456789");
        contact.setEmail("updatedContact@email.com");
        contact.setContactType("Updated Contact Type");

        when(contactRepository.update(contact)).thenReturn(true);
        Result<Contact> actual = service.update(contact);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        Contact contact = new Contact();
        contact.setContactId(89347985);
        contact.setPhone("312456789");
        contact.setEmail("notupdatedContact@email.com");
        contact.setContactType("Not Updated Contact Type");


        when(contactRepository.update(contact)).thenReturn(false);
        Result<Contact> actual = service.update(contact);
        assertEquals(ResultType.NOT_FOUND, actual.getType());

    }

    /* Delete Tests */
    @Test
    void shouldNotDeleteMissing() {
        when(contactRepository.deleteById(35000)).thenReturn(false);
        assertFalse(service.deleteById(35000));
    }

    @Test
    void shouldDelete() {
        when(contactRepository.deleteById(2)).thenReturn(true);
        assertTrue(service.deleteById(2));
    }


}