package ru.jft.geometry.figures;

import org.w3c.dom.css.Rect;

public record Rectangle(
        double width,
        double length
                            ) {

    public static void printArea(Rectangle r) {
        String text = String.format("Площадь прямоугольника со сторонами %f b %f = %f",
                r.width, r.length, area(r));
        System.out.println(text);
    }

    private static double area(Rectangle r) {
        return r.width * r.length;
    }
}
