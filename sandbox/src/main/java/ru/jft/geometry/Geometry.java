package ru.jft.geometry;

import ru.jft.geometry.figures.Rectangle;
import ru.jft.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printArea(new Square(7.0));
        Square.printArea(new Square(5.0));
        Square.printArea(new Square(3.0));

        Rectangle.printArea(new Rectangle(3.0, 5.0));
        Rectangle.printArea(new Rectangle(7.0, 9.0));
    }
}
