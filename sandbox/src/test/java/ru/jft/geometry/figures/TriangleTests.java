package ru.jft.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    Triangle t1 = new Triangle(5.0, 12.0, 13.0);
    Triangle t2 = new Triangle(10.0, 10.0, 10.0);
    private double expPer = 30.0;
    private double expAr1 = 30.0;
    private double expAr2 = 43.30127018922193;

    @Test
    void checkCalculatePerimeter() {
        Assertions.assertEquals(expPer, t1.calculatePerimeter());
        Assertions.assertEquals(expPer, t2.calculatePerimeter());
    }

    @Test
    void checkCalculateArea() {
        Assertions.assertEquals(expAr1, t1.calculateArea());
        Assertions.assertEquals(expAr2, t2.calculateArea());
    }

    //Д.з.3
    @Test
    void cannotCrateIncorrectTriangleSideA () {
        try {
            new Triangle(-3.0, 4.0, 5.0);
            Assertions.fail();
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //После доработки конструктора сюда не должно заходить
        }
    }

    @Test
    void cannotCrateIncorrectTriangleSideB () {
        try {
            new Triangle(3.0, -4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //После доработки конструктора сюда не должно заходить
        }
    }

    @Test
    void cannotCrateIncorrectTriangleSideC () {
        try {
            new Triangle(3.0, 4.0, -5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //После доработки конструктора сюда не должно заходить
        }
    }

    @Test
    void cannotCreateBrokenEqualityAB() {
        try {
            new Triangle(3.0, 4.0, 8.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //После доработки конструктора сюда не должно заходить
        }
    }

    @Test
    void cannotCreateBrokenEqualityBC() {
        try {
            new Triangle(10.0, 4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //После доработки конструктора сюда не должно заходить
        }
    }

    @Test
    void cannotCreateBrokenEqualityAC() {
        try {
            new Triangle(3.0, 9.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //После доработки конструктора сюда не должно заходить
        }
    }

    //Д.з.4
    @Test
    void testEquality() {
        Triangle t1 = new Triangle(3.0, 4.0, 5.0);
        Triangle t2 = new Triangle(4.0, 5.0, 3.0);
        Assertions.assertTrue(t1.equals(t2));
    }
}


