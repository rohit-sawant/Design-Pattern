package org.LLD.snakeAndLadder.factory;

import org.LLD.snakeAndLadder.entity.Ladder;
import org.LLD.snakeAndLadder.entity.Obstacle;
import org.LLD.snakeAndLadder.enums.ObstacleType;
import org.LLD.snakeAndLadder.entity.Snake;

public class ObstacleFactory {
    public static Obstacle createObstacle(ObstacleType obstacleType, int pos, int endPos){

        if(pos>endPos){
            int temp = pos;
            pos=endPos;
            endPos=temp;
        }
        System.out.println("==== adding "+obstacleType+" at ("+pos+","+endPos+") ===========");
        return switch (obstacleType){
            case SNAKE -> new Snake(endPos,pos);
            case LADDER -> new Ladder(pos,endPos);
            default -> null;
        };
    }
}
