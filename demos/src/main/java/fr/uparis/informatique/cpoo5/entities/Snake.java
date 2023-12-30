package fr.uparis.informatique.cpoo5.entities;

// import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

import fr.uparis.informatique.cpoo5.utils.Coordinate;
import fr.uparis.informatique.cpoo5.utils.Direction;

public class Snake extends SnakeAbstract{
   

    public Snake(int x, int y) {
       super(x, y);
    }

    

    // setting the direction of the snake
    public void setDirectionP(KeyCode keyCode) {
        /**
         * TODO: if dirrection UP/DOWN: only can set RIGHT/LEFT
         * TODO: if dirrection RIGHT/LEFT: only can set UP/DOWN
         */
        Direction d = Direction.getDirection(keyCode);
        if((
            (this.direction == Direction.UP || this.direction == Direction.DOWN) && 
            (d == Direction.LEFT || d == Direction.RIGHT)
            ) || (
            (this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && 
            (d == Direction.UP || d == Direction.DOWN)
            )
            || this.direction == null
        )
            this.direction = d;
    }



}