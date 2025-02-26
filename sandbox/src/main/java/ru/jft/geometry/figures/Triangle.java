package ru.jft.geometry.figures;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (//(3,4,5)
                Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0)||
                //(4,5,3)
                (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0)||
                //(5,3,4)
                (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.b) == 0)||
                //(3,5,4)
                (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.b) == 0)||
                //(5,4,3)
                (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.a) == 0)||
                //(4,3,5)
                (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(a, b, c);
        return 1;
    }
}
