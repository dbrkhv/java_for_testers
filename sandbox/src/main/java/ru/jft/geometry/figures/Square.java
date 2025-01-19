package ru.jft.geometry.figures;

public class Square {
    public static void printArea(double side) {
        //%f - вещественное число: float, double
        String text = String.format("Площадь квадрата со стороной %f = %f", side, area(side));
        System.out.println(text);
    }

    public static double area(double a) {
        return a * a;
    }

    public static double perimetr(double a) {
        return 4.0 * a;
    }
}
