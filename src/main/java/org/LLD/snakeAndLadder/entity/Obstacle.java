package org.LLD.snakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.LLD.snakeAndLadder.enums.ObstacleType;

@Data
@AllArgsConstructor
public abstract class Obstacle {
    int startPos;
    int endPos;

    abstract ObstacleType getType();
}
