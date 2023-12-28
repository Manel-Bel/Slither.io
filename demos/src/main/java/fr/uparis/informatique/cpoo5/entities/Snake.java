package fr.uparis.informatique.cpoo5.entities;

// import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

import fr.uparis.informatique.cpoo5.utils.Direction;

public class Snake {
    private Rectangle snakeRec;
    private int snakeX, snakeY;
    private final int snakeWidth = 20;
    private double snakeHeight = 20;
    private Direction direction;
    private ArrayList<Rectangle> body;
    private double speed = 20;
    private Color color;

    public Snake(int x, int y) {
        // the head
        snakeX = x;
        snakeY = y;
        this.snakeRec = new Rectangle(this.snakeX, this.snakeY, this.snakeWidth, this.snakeHeight);
        this.body = new ArrayList<>();
        this.body.add(snakeRec);
        this.direction = null;
        this.color = Color.GREEN;
        snakeRec.setFill(color);
    }

    // set the color
    public void setColor(Color c) {
        this.color = c;
        this.snakeRec.setFill(c);
    }

    public Direction getDirection() {
        return this.direction;
    }

    // get the body
    public ArrayList<Rectangle> getBody() {
        return this.body;
    }

    // get color
    public Color getColor() {
        return this.color;
    }

    // setting the direction of the snake
    public void setDirection(KeyCode keyCode) {
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

    // set opposite direction
    public void switchDirection() {
        this.direction = Direction.getOpposite(direction);
    }

    // to move the snake
    public void move() {
        if (this.direction == null) {
            // System.out.println("No direction assigned");
            return;
        }
        switch (direction) {
            case UP:
                snakeY -= speed;
                break;
            case DOWN:
                snakeY += speed;
                break;
            case LEFT:
                snakeX -= speed;
                break;
            case RIGHT:
                snakeX += speed;
                break;
            default:
                System.out.println("Invalid direction");
                return;
        }
        snakeRec.setX(snakeX);
        snakeRec.setY(snakeY);
        // System.out.println("direction set!!");
    }

    // check the colision
    public boolean checkCollision(double winWidth, double winHeight) {
        for (Rectangle r : body) {
            if (r.getX() < 0 || r.getX() + snakeWidth > winWidth || r.getY() < 0
                    || r.getY() + snakeHeight > winHeight) {
                return true;
            }
        }
        // the snake is in the frame
        return false;
    }

    // extend the length of the snake
    public void extendBody() {
        Rectangle r = new Rectangle(snakeWidth, snakeHeight);
        // position the new rec at the last rec
        r.setX(body.get(body.size() - 1).getX());
        r.setY(body.get(body.size() - 1).getY() + snakeHeight);
        body.add(r);
    }

    // to change the speed
    public void changeSpeed(int s) {
        speed += s;
    }

    // ovoride the methode tostring
    @Override
    public String toString() {
        return "Head is at [posX= " + snakeRec.getX() + ", posY=" + snakeRec.getY() + "]";
    }

}
