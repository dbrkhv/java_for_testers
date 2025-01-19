package ru.jft.geometry.figures;

import static java.lang.Math.pow;

public class Square {

    private double side;

    //Это конструктор. По нему создаются объекты данного класса
    public Square(double Side) {
        /*this - ключевое свойство для использоания текущего объекта
        this.side - объявляется для всего класса = свойство объекта,
         Side - что ждем на вход = свойство функции*/
        this.side = Side;
    }

    //Даем на вход объект, который создали, так как там вся информация есть
    public static void printArea(Square s) {
        //%f - вещественное число: float, double
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    /*static - ф-я может вызываться только в классе, если static нет - можно вызывать в объекте
    //Писали до конструктора:
    public static double area(double a) {
        return a * a;
    }*/

    //Используем в методахдалее this.side, потому что она задается в конструкторе
    public double area(){
        return pow(this.side,2);
    }

    public double perimetr() {
        return 4.0 * this.side;
    }
}
