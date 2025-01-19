package ru.jft.geometry;

import ru.jft.geometry.figures.Rectangle;
import ru.jft.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printArea(7.0);
        Square.printArea(5.0);
        Square.printArea(3.0);

        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);
    }
}
