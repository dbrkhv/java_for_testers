package ru.jft.geometry.figures;

import static java.lang.Math.sqrt;

public record Triangle(double a,
                       double b,
                       double c) {

    static double area;

    public Triangle{
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if (a + b < c || b + c < a || c + a < b) {
            throw new IllegalArgumentException("Triangle`s equality is violated");
        }
    }

    public double calculatePerimeter() {
        return (this.a+this.b+this.c);
    }

    public double calculateArea() {
        double hPer = calculatePerimeter()/2;
        area = sqrt(hPer*(hPer-this.a)*(hPer-this.b)*(hPer-this.c));
        return area;
    }
}
