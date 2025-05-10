package org.example.solid.openClosed.shape.wrong;

public class AreaCalculator {
    public double calculateArea(Object shape) {
        // any new addition has to do code change
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius * c.radius;
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.length * r.breadth;
        }
        return 0;
    }
}

 class Circle {
    public double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
}

 class Rectangle {
    public double length, breadth;
    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }
}
