package ru.jft.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double a, double b) {
        String text = String.format("Площадь прямоугольника со сторонами %f b %f = %f",
                a,b,rectangleArea(a, b));
        System.out.println(text);
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
