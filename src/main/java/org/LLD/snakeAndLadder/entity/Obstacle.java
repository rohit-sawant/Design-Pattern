package org.LLD.snakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Obstacle {
    int startPos;
    int endPos;

    abstract ObstacleType getType();
}
