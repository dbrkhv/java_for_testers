package ru.jft.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
     void canCalculateArea() {
        Square s = new Square(4.0);
        Assertions.assertEquals(25.0, s.area());
        /*if (!(s.area() == 25)) {
                throw new AssertionError(String.format("Expected: %f, actual: %f",  25.0, s.area()));
        }*/
    }

    @Test
    void canCalculatePerimetr() {
        Assertions.assertEquals(20.0, new Square(5.0).perimetr());
    }

    @Test
    void cannotCrateSquareWithNegativeSide () {
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //Ok
        }
    }
}
