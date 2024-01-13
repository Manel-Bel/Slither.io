package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public final class Player implements DecisionMaker {
    private final String name;
    private int score = 0;
    private Snake snake;

    public Player(String name, double x, double y) {
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
    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS, double deltaT) {
        // move the snake directly
        snake.move(deltaT);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player : " + name + ", Score : " + score;
    }

}
