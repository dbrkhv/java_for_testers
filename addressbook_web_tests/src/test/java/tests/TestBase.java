package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;

    //BeforeEach и AfterEach - фикстуры
    @BeforeEach
    public void setUp() {
        if (app == null){
            app = new ApplicationManager();
            app.init("chrome"); // chrome edge
        }
    }

    public static String randomString (int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        /*if (n < 20) {
            result = result + '\'';
        }*/
        return result;
    }
}
