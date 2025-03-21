package ru.jft.addressbook.tests;

import ru.jft.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveOneContact() {
       if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withLastName("lastname"));
        }
       var oldContacts = app.contacts().getList();
       var rnd = new Random();
       var index = rnd.nextInt(oldContacts.size());
       app.contacts().removeContact(oldContacts.get(index));
       var newContacts = app.contacts().getList();
       var expectedContacts = new ArrayList<>(oldContacts);
       expectedContacts.remove(index);
       Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public void canRemoveAllContacts() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withLastName("lastname"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

    @Test
    public void canRemoveEmptyContactList() {
        app.contacts().removeEmptyContactList();
    }
}
