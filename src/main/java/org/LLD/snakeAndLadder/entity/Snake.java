package org.LLD.snakeAndLadder.entity;

import org.LLD.snakeAndLadder.enums.ObstacleType;

public class Snake extends Obstacle{
    public Snake(int startPos, int endPos) {
        super(startPos, endPos);
    }

    @Override
    ObstacleType getType() {
        return ObstacleType.SNAKE;
    }

    @Override
    public String toString() {
        return "🐍";
    }
}
