package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveOneContact() {
       if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withLastName("lastname"));
        }
        app.contacts().removeContact();
    }

    @Test
    public void canRemoveAllContacts() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withLastName("lastname"));
        }
        app.contacts().removeAllContacts();
    }
}
