package org.LLD.snakeAndLadder.entity;

import lombok.Data;

@Data
public class Player {
    String name;
    int pos = 1;
    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.charAt(0)+"";
    }
}
