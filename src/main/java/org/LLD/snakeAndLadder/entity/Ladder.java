package org.LLD.snakeAndLadder.entity;

public class Ladder extends Obstacle{
    public Ladder(int startPos, int endPos) {
        super(startPos, endPos);
    }

    @Override
    ObstacleType getType() {
        return ObstacleType.LADDER;
    }

    @Override
    public String toString() {
        return "🪜";
    }
}
