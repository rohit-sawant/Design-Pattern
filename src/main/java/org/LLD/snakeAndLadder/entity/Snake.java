package org.LLD.snakeAndLadder.entity;

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
