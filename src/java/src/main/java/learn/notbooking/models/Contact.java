package learn.notbooking.models;

public class Contact {
    private int contactId;
    private String phone;
    private String email;
    private String contactType;

    public Contact() {
    }

    public Contact(int contactId, String phone, String email, String contactType) {
        this.contactId = contactId;
        this.phone = phone;
        this.email = email;
        this.contactType = contactType;
    }


    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
}
