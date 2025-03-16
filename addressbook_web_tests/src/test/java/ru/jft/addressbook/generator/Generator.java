package ru.jft.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.jft.addressbook.common.CommonFunctions;
import ru.jft.addressbook.model.ContactData;
import ru.jft.addressbook.model.GroupData;

import java.util.ArrayList;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-0"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;

    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        //--type groups --output groups.json --format json --count 3
        var result = new ArrayList<GroupData>();
        for (int i =0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i*10))
                    .withHeader(CommonFunctions.randomString(i*10))
                    .withFooter(CommonFunctions.randomString(i*10)));
        }
        return result;
    }

    private Object generateContacts() {
        //--type contacts --output contacts.json --format json --count 3
        var result = new ArrayList<ContactData>();
        for (int i =0; i < count; i++) {
            result.add(new ContactData().withFirstName( CommonFunctions.randomString(i*10)).withMiddleName( CommonFunctions.randomString(i*10))
                    .withLastName(CommonFunctions.randomString(i*10)).withNickname(CommonFunctions.randomString(i*10)).withCompany(CommonFunctions.randomString(i*10))
                    .withAddress( CommonFunctions.randomString(i*10)).withHomePhone(CommonFunctions.randomPhoneString(i*10)).withMobilePhone(CommonFunctions.randomPhoneString(i*10))
                    .withWorkPhone(CommonFunctions.randomPhoneString(i*10)).withFax(CommonFunctions.randomPhoneString(i*10)).withEmail(CommonFunctions.randomEmailString(i*10))
                    .withEmail2(CommonFunctions.randomEmailString(i*10)).withEmail3(CommonFunctions.randomEmailString(i*10)).withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
        }
        return result;
    }

    private void save(Object data) {

    }
}
