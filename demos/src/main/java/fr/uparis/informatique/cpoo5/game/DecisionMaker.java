package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

/**
 * The DecisionMaker class represents an abstract decision maker in the game.
 * It defines the common behavior for entities that make decisions for snakes,
 * such as players and AI.
 * 
 * @author : Belguenbour Manel
 */
public abstract class DecisionMaker {
    String name;
    int score = 0;
    Snake snake;

    /***
     * Sets the score of the decision maker by adding the specified points.
     *
     * @param p The points to be added to the score.
     */
    public void setScore(int p) {
        score += p;
    }

    public int getScore() {
        return score;
    }

    /***
     * Gets the snake controlled by the decision maker.
     *
     * @return The snake controlled by the decision maker.
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Gets the name of the decision maker.
     *
     * @return The name of the decision maker.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * It represents the logic for moving the snake based on the given coordinates
     * of the food and snake.
     *
     * @param coordinateFood The coordinates of the food.
     * @param coordinateS    The coordinates of the snake.
     */
    public abstract void moveSnake(Coordinate coordinateFood, Coordinate coordinateS);

}
