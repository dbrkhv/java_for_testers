package ru.jft.geometry.figures;

import static java.lang.Math.sqrt;

public record Triangle(double a,
                       double b,
                       double c) {

    static double area;

    public double calculatePerimeter() {
        return (this.a+this.b+this.c);
    }

    public double calculateArea() {
        double hPer = calculatePerimeter()/2;
        area = sqrt(hPer*(hPer-this.a)*(hPer-this.b)*(hPer-this.c));
        return area;
    }
}
