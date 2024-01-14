package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public final class Player extends DecisionMaker {

    public Player(String name, int x, int y) {
        this.name = name;
        this.snake = new Snake(x, y);
    }

    @Override
    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS) {
        // move the snake directly
        snake.move();
    }

    @Override
    public String toString() {
        return "Name : " + name + "Score : " + score;
    }

}
