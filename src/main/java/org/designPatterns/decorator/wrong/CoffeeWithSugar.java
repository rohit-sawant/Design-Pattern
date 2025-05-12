package org.designPatterns.decorator.wrong;

class CoffeeWithSugar extends Coffee {
    @Override
    public int cost() {
        return 10 + 1;
    }

    @Override
    public String description() {
        return "Coffee with Sugar";
    }
}