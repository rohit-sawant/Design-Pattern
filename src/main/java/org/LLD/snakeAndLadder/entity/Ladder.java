package org.LLD.snakeAndLadder.entity;

import org.LLD.snakeAndLadder.enums.ObstacleType;

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
