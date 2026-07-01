package org.LLD.snakeAndLadder.entity;

public class ObstacleFactory {
    public static Obstacle createObstacle(ObstacleType obstacleType,int pos,int endPos){

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
