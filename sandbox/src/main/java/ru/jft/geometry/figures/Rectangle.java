package ru.jft.geometry.figures;

import org.w3c.dom.css.Rect;

import java.util.Objects;

public record Rectangle(
        double width,
        double length
                            ) {
    public Rectangle {
        if (width < 0 || length < 0){
            throw new IllegalArgumentException("Rectangle side should be non-negative");
        }
    }

    public static void printArea(Rectangle r) {
        String text = String.format("Площадь прямоугольника со сторонами %f b %f = %f",
                r.width, r.length, area(r));
        System.out.println(text);
    }

    private static double area(Rectangle r) {
        return r.width * r.length;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(width, this.width) == 0 && Double.compare(length, this.length) == 0)||
                (Double.compare(width, this.length) == 0 && Double.compare(length, this.width) == 0);
    }

    @Override
    public int hashCode() {
        //ускорение сравнения 2х объектов, предварительное быстрое сравнение 2х объектов. Напр, сранвение 2х больших спсиков
        //return Objects.hash(width, length);
        return 1;
    }
}
