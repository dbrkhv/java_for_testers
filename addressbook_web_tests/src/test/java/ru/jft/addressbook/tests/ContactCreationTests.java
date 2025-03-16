package ru.jft.addressbook.tests;

import ru.jft.addressbook.common.CommonFunctions;
import ru.jft.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> randomContactsProvider() {
        var result = new ArrayList<ContactData>();
        for (int i =0; i < 2; i++) {
            result.add(new ContactData().withFirstName( CommonFunctions.randomString(i*10)).withMiddleName( CommonFunctions.randomString(i*10))
                    .withLastName(CommonFunctions.randomString(i*10)).withNickname(CommonFunctions.randomString(i*10)).withCompany(CommonFunctions.randomString(i*10))
                    .withAddress( CommonFunctions.randomString(i*10)).withHomePhone(CommonFunctions.randomPhoneString(i*10)).withMobilePhone(CommonFunctions.randomPhoneString(i*10))
                    .withWorkPhone(CommonFunctions.randomPhoneString(i*10)).withFax(CommonFunctions.randomPhoneString(i*10)).withEmail(CommonFunctions.randomEmailString(i*10))
                    .withEmail2(CommonFunctions.randomEmailString(i*10)).withEmail3(CommonFunctions.randomEmailString(i*10)).withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("randomContactsProvider")
    public void canCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        //Функция для сортировки, чтобы было возможно сранить списки
        Comparator<ContactData> compareContactsById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareContactsById);

        var expectedContacts = new ArrayList<>(oldContacts);
        //Добавляем в ожидаемый список созданные контакты
        expectedContacts.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withMiddleName("")
                .withNickname("").withCompany("").withAddress("").withHomePhone("").withMobilePhone("").withWorkPhone("")
                .withFax("").withEmail("").withEmail2("").withEmail3("").withPhoto(""));
        expectedContacts.sort(compareContactsById);

        Assertions.assertEquals(expectedContacts, newContacts);
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
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

    public static List<ContactData> oneContactProvider() {
        var result = new ArrayList<>(List.of(
                new ContactData().withFirstName("firstname").withMiddleName("middlename").withLastName("").withNickname("")
                        .withCompany("").withAddress("").withHomePhone("").withMobilePhone("").withWorkPhone("").
                        withEmail("").withEmail2("").withEmail3("")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("oneContactProvider")
    public void canCreateContactByAddNextButton(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        app.contacts().initAddNextContact();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        //Функция для сортировки, чтобы было возможно сранить списки
        Comparator<ContactData> compareContactsById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareContactsById);

        var expectedContacts = new ArrayList<>(oldContacts);
        //Т.к. в тесте проверяется кнопка "добавить еще один", то добавляем в ожидаемый список 2 контакта
        expectedContacts.add(contact.withId(newContacts.get(newContacts.size()-2).id()).withMiddleName("")
                .withNickname("").withCompany("").withAddress("").withHomePhone("").withMobilePhone("").withWorkPhone("")
                .withFax("").withEmail("").withEmail2("").withEmail3(""));
        expectedContacts.add(contact.withId(newContacts.get(newContacts.size()-1).id()).withMiddleName("")
                .withNickname("").withCompany("").withAddress("").withHomePhone("").withMobilePhone("").withWorkPhone("")
                .withFax("").withEmail("").withEmail2("").withEmail3(""));
        expectedContacts.sort(compareContactsById);

        Assertions.assertEquals(expectedContacts, newContacts);
    }

    public static List<ContactData> contactsProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "firstname")) {
            for (var middleName : List.of("", "middlename")) {
                result.add(new ContactData().withFirstName(firstName).withMiddleName(middleName)
                        .withLastName(CommonFunctions.randomString(5)).withNickname(CommonFunctions.randomString(5)).withCompany(CommonFunctions.randomString(5))
                        .withAddress( CommonFunctions.randomString(5)).withHomePhone(CommonFunctions.randomPhoneString(5)).withMobilePhone(CommonFunctions.randomPhoneString(5))
                        .withWorkPhone(CommonFunctions.randomPhoneString(5)).withEmail(CommonFunctions.randomEmailString(5)).withEmail2(CommonFunctions.randomEmailString(5)).withEmail3(CommonFunctions.randomEmailString(5)));
            }
        }
        return result;
    }
}
