package org.designPatterns.solid.liskovSubstitution.right;

// marker interface
interface Shape {

}

interface ShapesWithArea extends Shape {
    double calculateArea();
}

class Circle implements ShapesWithArea {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements ShapesWithArea {
    private double length, breadth;

    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public double calculateArea() {
        return length * breadth;
    }
}

class Line implements  Shape{

}

public class AreaCalculator {
    double calculateArea(ShapesWithArea shape) {
        return shape.calculateArea();
    }
}

