import java.io.File;

public class Hello {
    public static void main (String[] args) {
            var x = 1;
            var y = 0;
            if (y == 0) {
                System.out.println("Division by zero is not allowed");
            } else {
                int z = divide(x, y);
                System.out.println("Hello, world!");
            }

        var configfile = new File("sandbox/build.graddle");
        System.out.println(configfile.getAbsolutePath());
        System.out.println(configfile.exists());

        System.out.println(new File("").getAbsolutePath());
    }

    public static int divide (int x, int y){
        var z = x/y;
        return z;
    }
}
