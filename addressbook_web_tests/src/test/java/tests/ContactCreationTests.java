package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithEmptyValues() {
        app.contacts().createContact(new ContactData("", "", "", "", "",
                "", "", "", "", "", "", "", ""));
        app.contacts().returnToHomePage();
    }

    @Test
    public void canCreateFullfilledContact() {
        app.contacts().createContact(new ContactData("firstname", "middlename", "lastname",
                "nickname", "company", "address", "52314", "89119119111",
                "56567", "333-000", "email1@test.com", "email2@test.com", "email3@test.com"));
        app.contacts().returnToHomePage();
    }

    @Test
    public void canCreatePartlyFieldContact() {
        app.contacts().createContact(new ContactData().withLastName("lastname").withAddress("address").withMobilePhone("89119119111"));
        app.contacts().returnToHomePage();
    }

    @Test
    public void canCreateContactByAddNextButton() {
        app.contacts().createContact(new ContactData().withLastName("lastname"));
        app.contacts().initAddNextContact();
        app.contacts().createContact(new ContactData().withLastName("add next"));
        app.contacts().returnToHomePage();
    }
}
