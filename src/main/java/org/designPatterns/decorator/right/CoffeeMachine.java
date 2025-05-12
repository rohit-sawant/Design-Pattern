package org.designPatterns.decorator.right;

public class CoffeeMachine {
    public static void main(String[] args) {
        Coffee coffee = new BasicCoffee();
        coffee = new MilkDecorator(new SugarDecorator(coffee));

        System.out.println(coffee.getDescription()); // Basic Coffee, Milk, Sugar
        System.out.println(coffee.getCost());
    }
}
