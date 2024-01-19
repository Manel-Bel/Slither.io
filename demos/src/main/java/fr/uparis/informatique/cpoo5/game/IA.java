package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;
import fr.uparis.informatique.cpoo5.utils.Direction;
import javafx.scene.paint.Color;

/**
 * The IA class represents an artificial intelligence player in the game.
 * It extends the `DecisionMaker` class and implements the logic for the bot's
 * movements.
 * 
 * @author Mohamed Halim Nafyssata
 */
public class IA extends DecisionMaker {

    /**
     * Constructs a new instance of the `IA` class with the specified initial
     * coordinates.
     *
     * @param x The initial x-coordinate of the bot.
     * @param y The initial y-coordinate of the bot.
     */
    public IA(int x, int y) {
        name = "Bot";
        this.snake = new Snake(x, y);
        // set default direction to the snake
        snake.setDirection(Direction.RIGHT);
        snake.setColor(Color.RED);
    }

    /**
     * Moves the snake controlled by the bot based on the positions of the food and
     * the snake.
     *
     * @param coordinateFood The coordinates of the food in the game.
     * @param coordinateS    The coordinates of the snake in the game.
     */
    @Override
    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS) {
        setDirectionSnake(coordinateFood, coordinateS);
        snake.move();
    }

    /**
     * Sets the direction of the snake based on the positions of the food and the
     * snake.
     *
     * @param coordinateFood The coordinates of the food in the game.
     * @param coordinateS    The coordinates of the snake in the game.
     */
    private void setDirectionSnake(Coordinate coordinateFood, Coordinate coordinateS) {
        if (coordinateFood.row == coordinateS.row) {
            if (coordinateFood.col > coordinateS.col) {
                snake.setDirection(Direction.RIGHT);
            } else {
                snake.setDirection(Direction.LEFT);
            }
        } else if (coordinateFood.col == coordinateS.col) {
            if (coordinateFood.row > coordinateS.row) {
                snake.setDirection(Direction.DOWN);
            } else {
                snake.setDirection(Direction.UP);
            }
        } else {
            if (coordinateFood.row > coordinateS.row) {
                snake.setDirection(Direction.DOWN);
            } else {
                snake.setDirection(Direction.UP);
            }
        }
    }

}
