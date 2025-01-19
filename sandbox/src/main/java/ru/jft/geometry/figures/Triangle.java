package ru.jft.geometry.figures;

import static java.lang.Math.sqrt;

public record Triangle(double a,
                       double b,
                       double c) {

    static double area;
    static double halfperimeter;

    public static double calculatePerimeter(Triangle t) {
        halfperimeter = (t.a+t.b+t.c)/2;
        return 2.0 * halfperimeter;
    }

    public double calculateArea(Triangle t) {
        area = sqrt(t.halfperimeter*(t.halfperimeter-t.a)*(t.halfperimeter-t.b)*(t.halfperimeter-t.c));
        return area;
    }
}
