package org.LLD.snakeAndLadder.entity;

import java.util.Random;

public class Dice {
    int maxValue = 6;
    public int rollDice(){
        Random random = new Random();
        return random.nextInt(maxValue)+1;
    }
}
