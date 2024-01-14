package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

/***
 * The `Player` class represents a human player in the game.
 * It extends the DecisionMaker class and provides the logic for the player's
 * movements.
 * 
 * @author : Belguenbour Manel
 */
public final class Player extends DecisionMaker {

    /**
     * Constructs a new instance of the Player class with the specified name and
     * initial coordinates.
     *
     * @param name The name of the player.
     * @param x    The initial x-coordinate of the player's snake.
     * @param y    The initial y-coordinate of the player's snake.
     */
    public Player(String name, int x, int y) {
        this.name = name;
        this.snake = new Snake(x, y);
    }

    /***
     * Moves the snake controlled by the player.
     * without additional logic.
     *
     * @param coordinateFood The coordinates of the food in the game.
     * @param coordinateS    The coordinates of the snake in the game.
     */
    @Override
    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS) {
        // move the snake directly
        snake.move();
    }

    /**
     * Returns a string representation of the player, including the name and score.
     *
     * @return A string representing the player's name and score.
     */
    @Override
    public String toString() {
        return "Name : " + name + "Score : " + score;
    }

}
