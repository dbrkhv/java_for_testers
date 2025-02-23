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
        //Triangle triangle = (Triangle) o;
        return (Double.compare(a, this.a) == 0 && Double.compare(b, this.b) == 0 && Double.compare(c, this.c) == 0)||
                (Double.compare(a, this.b) == 0 && Double.compare(b, this.a) == 0 && Double.compare(c, this.a) == 0)||
                (Double.compare(a, this.c) == 0 && Double.compare(b, this.c) == 0 && Double.compare(c, this.b) == 0);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(a, b, c);
        return 1;
    }
}
