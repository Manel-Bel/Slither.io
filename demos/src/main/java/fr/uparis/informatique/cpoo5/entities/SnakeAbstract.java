package fr.uparis.informatique.cpoo5.entities;

import java.util.ArrayList;

import fr.uparis.informatique.cpoo5.utils.Direction;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class SnakeAbstract {
    protected Rectangle snakeRec;
    protected int snakeX;
    protected int snakeY;
    protected final int snakeWidth = 20;
    protected int snakeHeight = 20;
    protected Direction direction;
    protected ArrayList<Rectangle> body;
    protected double speed = 20;
    protected Color color;


    public SnakeAbstract(int x, int y) {
        // the head
        this.snakeX = x;
        snakeY = y;
        this.snakeRec = new Rectangle(this.snakeX, this.snakeY, this.snakeWidth, this.snakeHeight);
        this.body = new ArrayList<>();
        this.body.add(snakeRec);
        this.direction = null;
        this.color = Color.GREEN;
        snakeRec.setFill(color);
    }
    public void setColor(Color c) {
        this.color = c;
        this.snakeRec.setFill(c);
    }

    public Direction getDirection() {
        return this.direction;
    }
    public ArrayList<Rectangle> getBody() {
        return this.body;
    }

    // get color
    public Color getColor() {
        return this.color;
    }
    public void switchDirection() {
        this.direction = Direction.getOpposite(direction);
    }
    public void setDirection(Direction dir){
        this.direction = dir;
    }
    
    // to move the snake
    public void move(int widthBound, int heightBound) {
        if (this.direction == null) {
            return;
        }
        switch (direction) {
            case UP:
                snakeY -= speed;
                if (snakeY < 0) {
                    snakeY = heightBound - snakeHeight;
                }
                break;
            case DOWN:
                snakeY += speed;
                if (snakeY > heightBound - snakeHeight) {
                    snakeY = 0;
                }
                break;
            case LEFT:
                snakeX -= speed;
                if (snakeX < 0) {
                    snakeX = widthBound - snakeWidth;
                }
                break;
            case RIGHT:
                snakeX += speed;
                if (snakeX > widthBound - snakeWidth) {
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
}


    



