package fr.uparis.informatique.cpoo5.game;

import java.util.LinkedList;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.entities.SnakeAbstract;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public abstract  class  Person {
    private final String name;
    private int score = 0;
    SnakeAbstract snake;
    private LinkedList<Coordinate> occupiedCells;

    public Person(String name ){
        this.name = name;

        this.occupiedCells = new LinkedList<>();
         
    }



    public void addOccupiedCell(Coordinate c){
        this.occupiedCells.add(c);
    }

    public void setOccupiedCells(LinkedList<Coordinate> occupiedCells) {
        this.occupiedCells = occupiedCells;
    }
    public void setScore(int p) {
        this.score += p;
    }

    public LinkedList<Coordinate> getOccupiedCells() {
        return this.occupiedCells;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return "Player: " + name + ", Score: " + score;
    }
    public SnakeAbstract getSnake(){
        return this.snake;
    }
}
