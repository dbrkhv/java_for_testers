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
            result.add(new ContactData(randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10), randomString(i*10)));
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
                new ContactData("firstname'", "", "", "", "", "", "", "", "", "", "", "", "")));
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
                new ContactData("firstname", "middlename", "", "", "", "", "", "", "", "", "", "", "")));
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
                for (var lastName : List.of("", "lastname")) {
                    for (var nickname : List.of("", "nickname")) {
                        for (var company : List.of("", "company")) {
                            for (var address : List.of("", "address")) {
                                for (var homePhone : List.of("", "52314")) {
                                    for (var mobilePhone : List.of("", "89119119111")) {
                                        for (var workPhone : List.of("", "56567")) {
                                            for (var fax : List.of("", "333-000")) {
                                                for (var email : List.of("", "email1@test.com")) {
                                                    for (var email2 : List.of("", "email2@test.com")) {
                                                        for (var email3 : List.of("", "email3@test.com")) {
                                                            result.add(new ContactData(firstName, middleName, lastName, nickname, company, address,
                                                                    homePhone, mobilePhone, workPhone, fax, email, email2, email3));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
