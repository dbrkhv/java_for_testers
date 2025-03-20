package ru.jft.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import ru.jft.addressbook.common.CommonFunctions;
import ru.jft.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List <GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        /*for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")){
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }*/
        /*var json = "";
        try (var reader = new FileReader("groups.json");
            var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }*/
        //var mapper = new ObjectMapper();
        //var mapper = new YamlMapper();
        var mapper = new XmlMapper();
        var readValues = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {});
        result.addAll(readValues);
        return result;
    }

    public static List <GroupData> singleRandomGroup() {
        return List.of(new GroupData()
                        .withName(CommonFunctions.randomString(10))
                        .withHeader(CommonFunctions.randomString(20))
                        .withFooter(CommonFunctions.randomString(30)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        var oldGroups = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        var maxId = group.withId(newGroups.get(newGroups.size() - 1).id());

        expectedList.add(maxId);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void compareUiAndDbGroups() {
        var dbGroups = app.jdbc().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        dbGroups.sort(compareById);
        var uiGroups = app.groups().getList();
        uiGroups.sort(compareById);
        var dbGroupsExpected = new ArrayList<GroupData>();
        int i = 0;
        while (i <= dbGroups.size()-1 ) {
            dbGroupsExpected.add(dbGroups.get(i).withFooter("").withHeader(""));
            i++;
        }
        Assertions.assertEquals(uiGroups, dbGroupsExpected);
    }

    public static List <GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData ("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }

    /*Эти сценарии покрываются в вынесенной параметризации groupProvider и сценарии canCreateMultipleGroups
    @ParameterizedTest
    //В аннотации @ValueSource можно использовать только (!)фиксированные длины строк
    @ValueSource(strings = {"group name", "group name'"})
    public void canCreateGroup(String name) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(new GroupData(name, "group header", "group footer"));
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }*/
}
