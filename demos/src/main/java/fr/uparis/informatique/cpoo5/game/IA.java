package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;
import fr.uparis.informatique.cpoo5.utils.Direction;
import javafx.scene.paint.Color;

public class IA extends DecisionMaker {

    public IA(int x, int y) {
        name = "Bot";
        this.snake = new Snake(x, y);
        // set default direction to the snake
        snake.setDirection(Direction.RIGHT);
        snake.setColor(Color.RED);
    }

    @Override
    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS) {
        setDirectionSnake(coordinateFood, coordinateS);
        snake.move();
    }

    // setting the direction of the snake
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
