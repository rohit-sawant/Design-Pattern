package org.designPatterns.decorator.right;

public class BasicCoffee implements Coffee {
    public String getDescription() {
        return "Basic Coffee";
    }

    public double getCost() {
        return 5.0;
    }
}

