package org.designPatterns.decorator.wrong;

 class CoffeeWithMilkAndSugar extends Coffee {
    @Override
    public int cost() {
        return 10 + 2 + 1; // base + milk + sugar
    }

    @Override
    public String description() {
        return "Coffee with Milk and Sugar";
    }
}