package org.LLD.snakeAndLadder;

import org.LLD.snakeAndLadder.entity.Board;
import org.LLD.snakeAndLadder.entity.Obstacle;
import org.LLD.snakeAndLadder.entity.ObstacleType;
import org.LLD.snakeAndLadder.entity.Player;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadderGame {

    public static void main(String[] args) throws Exception {
        Queue<Player> players = new LinkedList<>();
        players.add(new Player("rohit"));

        players.add(new Player("aarush"));
        Board game = new Board(
                10,
                new LinkedList<>(players),
                1
        );
        game.printBoard();
        System.out.println("\n\n===== adding Obstacle ========");
        game.generateObstacle(3, ObstacleType.SNAKE);
        game.generateObstacle(3, ObstacleType.LADDER);
        game.printBoard();

        System.out.println("\n\n===== Play game ========");

        while(game.stillPlayersLeft()){

            game.playPlayer();

            game.printBoard();
        }



    }
}
