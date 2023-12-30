package fr.uparis.informatique.cpoo5.game;

import java.util.LinkedList;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public class Player extends Person{
    //private final String name;
    //private int score = 0;
    private Snake snake;
    private LinkedList<Coordinate> occupiedCells;

    public Player(String name, int x, int y) {
        super(name);
        
        this.snake = new Snake(x, y);
        this.occupiedCells = new LinkedList<>();
    }

    public void addOccupiedCell(Coordinate c){
        this.occupiedCells.add(c);
    }

    public void setOccupiedCells(LinkedList<Coordinate> occupiedCells) {
        this.occupiedCells = occupiedCells;
    }

    public LinkedList<Coordinate> getOccupiedCells() {
        return this.occupiedCells;
    }

   

    public Snake getSnake() {
        return this.snake;
    }

   
}
