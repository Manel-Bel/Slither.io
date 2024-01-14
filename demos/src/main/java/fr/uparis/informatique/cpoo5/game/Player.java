package fr.uparis.informatique.cpoo5.game;

import java.util.LinkedList;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public final class Player implements DecisionMaker {
    private final String name;
    private int score = 0;
    private Snake snake;

    public Player(String name, int x, int y) {
        this.name = name;
        this.snake = new Snake(x, y);
    }

    @Override
    public void setScore(int p) {
        this.score += p;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public Snake getSnake() {
        return this.snake;
    }

    @Override
    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS) {
        // move the snake directly
        snake.move();
    }
    public String getNom(){
        return this.name;
    }

}
