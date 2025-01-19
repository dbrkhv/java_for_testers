package ru.jft.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void checkCalculatePerimeter() {
        Triangle t1 = new Triangle(3.0,4.0,5.0);
        Assertions.assertEquals(12.0, Triangle.calculatePerimeter(t1));
    }

    @Test
    void checkCalculateArea(){
        Triangle t1 = new Triangle(3.0,4.0,5.0);
        Assertions.assertEquals(12.0, Triangle.calculatePerimeter(t1));
    }

}
