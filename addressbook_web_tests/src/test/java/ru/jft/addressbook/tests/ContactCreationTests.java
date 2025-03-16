package ru.jft.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.jft.addressbook.common.CommonFunctions;
import ru.jft.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.jft.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactsProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        var mapper = new ObjectMapper();
        //XmlMapper YAMLMapper xml yaml
        var readValues = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(readValues);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactsProvider")
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
}
