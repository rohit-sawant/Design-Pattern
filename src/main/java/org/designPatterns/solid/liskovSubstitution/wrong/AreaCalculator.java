package org.designPatterns.solid.liskovSubstitution.wrong;


/**
 * 🛑 wrong examples as All shapes(line) do not have an area
 */
interface Shapes {
    double calculateArea();

}

class Circle implements Shapes {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shapes {
    private double length, breadth;

    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double calculateArea() {
        return length * breadth;
    }
}

public class AreaCalculator {
    double calculateArea(Shapes shape) {
        return shape.calculateArea();
    }
}

