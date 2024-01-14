package fr.uparis.informatique.cpoo5.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import fr.uparis.informatique.cpoo5.ui.Menu;
import fr.uparis.informatique.cpoo5.utils.Direction;

public final class Snake {
    private Rectangle snakeRec;
    private int snakeX, snakeY;
    private final int snakeWidth = 20;
    private int snakeHeight = 20;
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
    public void setDirection(Direction d) {
        /**
         * if dirrection UP/DOWN: only can set RIGHT/LEFT
         * if dirrection RIGHT/LEFT: only can set UP/DOWN
         */
        if (this.direction == null || ((this.direction == Direction.UP || this.direction == Direction.DOWN) &&
                (d == Direction.LEFT || d == Direction.RIGHT))
                || ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) &&
                        (d == Direction.UP || d == Direction.DOWN)))
            this.direction = d;
    }

    // to move the snake
    public void move() {
        if (this.direction == null) {
            return;
        }
        switch (direction) {
            case UP:
                snakeY -= speed;
                if (snakeY < 0) {
                    snakeY = Menu.winHeight - snakeHeight;
                }
                break;
            case DOWN:
                snakeY += speed;
                if (snakeY > Menu.winHeight - snakeHeight) {
                    snakeY = 0;
                }
                break;
            case LEFT:
                snakeX -= speed;
                if (snakeX < 0) {
                    snakeX = Menu.winWidth - snakeWidth;
                }
                break;
            case RIGHT:
                snakeX += speed;
                if (snakeX > Menu.winWidth - snakeWidth) {
                    snakeX = 0;
                }
                break;
            default:
                System.out.println("Invalid direction");
                return;
        }
        // move the body
        for (int i = body.size() - 1; i > 0; i--) {
            // get the priview
            Rectangle temp = body.get(i - 1);
            // update the position of i
            body.get(i).setX(temp.getX());
            body.get(i).setY(temp.getY());

        }
        // update the position of the head
        snakeRec.setX(snakeX);
        snakeRec.setY(snakeY);
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
        Rectangle last = body.get(body.size() - 1);

        switch (direction) {
            case UP:
                r.setX(last.getX());
                r.setY(last.getY() + snakeHeight);
                break;
            case DOWN:
                r.setX(last.getX());
                r.setY(last.getY() - snakeHeight);
                break;
            case LEFT:
                r.setX(last.getX() + snakeWidth);
                r.setY(last.getY());
                break;
            case RIGHT:
                r.setX(last.getX() - snakeWidth);
                r.setY(last.getY());
                break;
            default:
                System.out.println("Invalid direction");
                break;
        }

        // position the new rec at the last rec
        // r.setX(body.get(body.size() - 1).getX());
        // r.setY(body.get(body.size() - 1).getY() + snakeHeight);
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

    public int getSnackY() {
        return this.snakeY;
        
    }

    public int getSnackX() {
        return this.snakeX;
    }

}