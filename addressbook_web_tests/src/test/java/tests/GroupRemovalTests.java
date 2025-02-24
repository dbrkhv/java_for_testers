package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (! app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("group 1 name", "group 1 header", "group 1 footer"));
        }
        app.groups().removeGroup();
    }

}
