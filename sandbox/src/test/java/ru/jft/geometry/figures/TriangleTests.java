package ru.jft.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void checkCalculatePerimeter() {
        Triangle t1 = new Triangle(5.0, 12.0, 13.0);
        Assertions.assertEquals(30.0, Triangle.calculatePerimeter(t1));

        Triangle t2 = new Triangle(10.0, 10.0, 10.0);
        Assertions.assertEquals(30.0, Triangle.calculatePerimeter(t2));
    }

    @Test
    void checkCalculateArea() {
        Triangle t1 = new Triangle(5.0, 12.0, 13.0);
        Assertions.assertEquals(30.0, Triangle.calculatePerimeter(t1));

        Triangle t2 = new Triangle(10.0, 10.0, 10.0);
        Assertions.assertEquals(43.30127, Triangle.calculatePerimeter(t2));
    }


