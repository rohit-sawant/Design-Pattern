package org.designPatterns.decorator.wrong;

public class CoffeeWithMilk extends Coffee {
    @Override
    public int cost() {
        return 10 + 2;
    }

    @Override
    public String description() {
        return "Coffee with Milk";
    }
}