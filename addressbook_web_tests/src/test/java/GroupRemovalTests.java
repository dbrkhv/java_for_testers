import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (! isGroupPresent()) {
            createGroup("group 1 name", "group 1 header", "group 1 footer");
        }
        removeGroup();
    }

}
