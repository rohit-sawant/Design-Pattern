package org.LLD.snakeAndLadder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cell {
    int position;
    Obstacle obstacle;

    public Cell(int position) {
        this.position = position;
    }

    boolean hasObstacle()
    {
        return obstacle!=null;
    }
}
