package fr.uparis.informatique.cpoo5.entities;

// import javafx.scene.control.skin.TextInputControlSkin.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

import fr.uparis.informatique.cpoo5.utils.Direction;

public class Snake {
    private Rectangle snakeRec;
    private double snakeX = 50, snakeY = 50;
    private final double snakeWidth = 20;
    private double snakeHeight = 20;
    private Direction direction;
    private ArrayList<Rectangle> body;
    private double speed = 1;

    public Snake() {
        this.snakeRec = new Rectangle(this.snakeX, this.snakeY, this.snakeWidth, this.snakeHeight);
        this.body = new ArrayList<>();
        this.body.add(snakeRec);
        this.direction = Direction.UP;
    }

    public Direction getDirection() {
        return this.direction;
    }

    // get the body
    public ArrayList<Rectangle> getBody() {
        return this.body;
    }

    // setting the direction of the snake
    public void setDirection(KeyCode keyCode) {
        this.direction = Direction.getDirection(keyCode);
    }

    // set opposite direction
    public void switchDirection() {
        this.direction = Direction.getOpposite(direction);
    }

    // to move the snake
    public void move() {
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
                break;
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
}
