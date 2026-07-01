package org.LLD.snakeAndLadder.entity;

import org.LLD.snakeAndLadder.enums.ObstacleType;
import org.LLD.snakeAndLadder.factory.ObstacleFactory;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Board {
    Cell cells[][];
    int n;
    Queue<Player> players;
    int size;
    Dice dices[];

    Random random;
    public Board(int n, Queue<Player> players,int noOfDices) {
        this.n = n;
        this.players = players;
        this.size  = n*n;
        this.cells =  new Cell[n][n];
        populateBoard(n);
        dices = new Dice[noOfDices];
        for (int i = 0; i < noOfDices; i++) {
            dices[i] = new Dice();
        }
        random = new Random();
    }

    public boolean addObstacle(ObstacleType obstacleType, int position){
        Cell cell = cells[getRow(position)][getcol(position)];
        if(cell.getObstacle()!=null){
           return false;
        }

        Obstacle obstacle = ObstacleFactory.createObstacle(obstacleType,getRow(position),getcol(position));
        cell.setObstacle(obstacle);
        return true;
    }


    private void populateBoard(int n){
        boolean leftToRight = true;
        int position = 1;
        for(int i=0;i<n;i++){
            if (leftToRight) {
                for (int j = 0; j <n ; j++) {

                    cells[i][j] = new Cell(position++);
                }

            }
            else {
                for (int j = n-1; j >=0 ; j--) {
                    cells[i][j] = new Cell(position++);
                }
            }
            leftToRight = !leftToRight;
        }

    }
    public List<Player> getPlayerIsInPos(int pos){
        return players.stream().filter(player -> player.getPos()==pos).toList();
    }

    public boolean stillPlayersLeft(){
        return players.size()>1;
    }
    public boolean isEven(int n){
        return n%2==0;
    }
    public int getRow(int pos){
        return (pos-1)/n;
    }
    public int getcol(int pos){
        int remainder = (pos-1)%n;
        return isEven(getRow(pos))? remainder : n-remainder-1;
    }
    public void printBoard(){

        System.out.println("\n=========== Board =========");
        for (int i = n-1; i >=0; i--) {
            for (int j = 0; j < n; j++) {
                int pos = cells[i][j].getPosition();

                List<Player> playerInPos =  getPlayerIsInPos(pos);
                if(cells[i][j].hasObstacle()){
                    System.out.print(cells[i][j].getObstacle()+"\t");
                }
                else{
                    System.out.print((!playerInPos.isEmpty()?playerInPos:String.valueOf(pos))+"\t");
                }
            }
            System.out.println();
        }

    }

    public int getPos(int row,int col){
        int multiplier = row*n;
        if(multiplier%2!=0){
            return multiplier+col+1;
        }
        return multiplier+n-col;
    }

    public void generateObstacle(int n,ObstacleType obstacleType){
        for (int i = 0; i < n; i++) {
            int position = random.nextInt(size);
            while(!addObstacle(obstacleType,position)){};

        }
    }
    public int getPlayerNewPos(Player player,int diceValue){
        return player.getPos()+diceValue;
    }
    public void playPlayer() throws Exception {

        if(players.isEmpty()) throw new Exception("No player left to play");
        System.out.println("==========");
        Player player = players.poll();
        System.out.println(player+" is playing");

        int diceValue = rollDices();
        System.out.println("After rolling dice: "+diceValue);

        int position = getPlayerNewPos(player,diceValue);
//        if player win he walks out of game
        if(position==size-1){
            System.out.println(" wins the game");
            return;
        }
        else if(position<(size-1)){
            Cell cell =  getCell(position);
            if(cell.getObstacle()!=null){
                System.out.println("hit an obstacle");
                player.setPos(cell.getObstacle().getEndPos());
            }
            player.setPos(position);
        }

        players.add(player);


    }

    public Cell getCell(int position){

        return cells[getRow(position)][getcol(position)];
    }

    private int rollDices(){
        int sum = 0;
        for (int i = 0; i < dices.length; i++) {
            sum += dices[i].rollDice();
        }
        return sum;
    }
}
