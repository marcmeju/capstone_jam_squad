package learn.notbooking.domain;

import learn.notbooking.data.ContactRepository;
import learn.notbooking.domain.Result;
import learn.notbooking.domain.ResultType;
import learn.notbooking.models.Contact;

import learn.notbooking.models.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }


    public Contact findById(int contactId){return repository.findById(contactId);}


    public List<Contact> findAll() {
        return repository.findAll();
    }


    public Result<Contact> add(Contact contact) {
        Result<Contact> result = validate(contact);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Contact p = repository.add(contact);
        result.setPayload(p);
        return result;
    }


    public Result<Contact> update(Contact contact) {
        Result<Contact> result = validate(contact);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        if (!repository.update(contact)) {
            result.addMessage("contact id " + contact.getContactId() + " not found", ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int contactId) {
        return repository.deleteById(contactId);
    }





    // ********************** validate ************************
    private Result<Contact> validate(Contact contact) {
        Result<Contact> result = new Result<>();

        if (contact == null) {
            result.addMessage("contact cannot be null", ResultType.INVALID);
            return result;
        }

        if (contact.getPhone().trim().length() <=0) {
            result.addMessage("Phone is required", ResultType.INVALID);
        }

        if (contact.getEmail().trim().length() <=0) {
            result.addMessage("Email is required", ResultType.INVALID);
        }

        if (contact.getContactType().trim().length() <=0) {
            result.addMessage("Contact Type is required", ResultType.INVALID);
        }

        for(Contact sc : findAll()){
            if (contact == sc){
                result.addMessage("You have made this contact already", ResultType.INVALID);
            }
            if (contact.getEmail().equalsIgnoreCase(sc.getEmail()) ){
                result.addMessage("Email already exists", ResultType.INVALID);
            }


        }


        return result;
    }

}
