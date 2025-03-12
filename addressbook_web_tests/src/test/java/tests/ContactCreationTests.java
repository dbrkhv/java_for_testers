package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> randomContactsProvider() {
        var result = new ArrayList<ContactData>();
        for (int i =0; i < 5; i++) {
            result.add(new ContactData().withFirstName( randomString(i*10)).withMiddleName( randomString(i*10))
                    .withLastName(randomString(i*10)).withNickname(randomString(i*10)).withCompany(randomString(i*10))
                    .withAddress( randomString(i*10)).withHomePhone(randomPhoneString(i*10)).withMobilePhone(randomPhoneString(i*10))
                    .withWorkPhone(randomPhoneString(i*10)).withFax(randomPhoneString(i*10)).withEmail(randomEmailString(i*10))
                    .withEmail2(randomEmailString(i*10)).withEmail3(randomEmailString(i*10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("randomContactsProvider")
    public void canCreateContacts(ContactData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount + 1, newContactsCount);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData().withFirstName("firstname'").withMiddleName("").withLastName("").withNickname("")
                        .withCompany("").withAddress("").withHomePhone("").withMobilePhone("").withWorkPhone("").
                        withEmail("").withEmail2("").withEmail3("")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount, newContactsCount);
    }

    public static List<ContactData> oneContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData().withFirstName("firstname").withMiddleName("middlename").withLastName("").withNickname("")
                        .withCompany("").withAddress("").withHomePhone("").withMobilePhone("").withWorkPhone("").
                        withEmail("").withEmail2("").withEmail3("")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("oneContactProvider")
    public void canCreateContactByAddNextButton(ContactData contact) {
        int contactsCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        app.contacts().initAddNextContact();
        app.contacts().createContact(contact);
        int newContactsCount = app.contacts().getCount();
        Assertions.assertEquals(contactsCount + 2, newContactsCount);
    }

    public static List<ContactData> contactsProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "firstname")) {
            for (var middleName : List.of("", "middlename")) {
                result.add(new ContactData().withFirstName(firstName).withMiddleName(middleName)
                        .withLastName(randomString(5)).withNickname(randomString(5)).withCompany(randomString(5))
                        .withAddress( randomString(5)).withHomePhone(randomPhoneString(5)).withMobilePhone(randomPhoneString(5))
                        .withWorkPhone(randomPhoneString(5)).withEmail(randomEmailString(5)).withEmail2(randomEmailString(5)).withEmail3(randomEmailString(5)));
            }
        }
        return result;
    }
}
