package fr.uparis.informatique.cpoo5.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import fr.uparis.informatique.cpoo5.ui.Menu;
import fr.uparis.informatique.cpoo5.utils.ColorManager;
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

    /**
     * Constructs a new Snake instance with the specified position.
     *
     * @param x The x-coordinate of the snake.
     * @param y The y-coordinate of the snake.
     */
    public Snake(int x, int y) {
        // the head
        snakeX = x;
        snakeY = y;
        this.snakeRec = new Rectangle(this.snakeX, this.snakeY, this.snakeWidth, this.snakeHeight);
        this.body = new ArrayList<>();
        this.body.add(snakeRec);
        this.direction = null;
        this.color = ColorManager.getAvailableColor();
        snakeRec.setFill(color);
    }

    /**
     * Sets the color of the snake.
     *
     * @param c The color to set.
     */
    public void setColor(Color c) {
        this.color = c;
        this.snakeRec.setFill(c);
    }

    /**
     * Gets the current direction of the snake.
     *
     * @return The direction of the snake.
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Gets the body of the snake.
     *
     * @return The body of the snake.
     */
    public ArrayList<Rectangle> getBody() {
        return this.body;
    }

    /**
     * Gets the color of the snake.
     *
     * @return The color of the snake.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the direction of the snake.
     *
     * @param d The direction to set.
     */
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

    /**
     * Moves the snake in the current direction.
     */
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

    /**
     * Checks if the snake has collided with the window boundaries.
     *
     * @param winWidth  The width of the game window.
     * @param winHeight The height of the game window.
     * @return True if the snake has collided, false otherwise.
     */
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

    /**
     * Extends the length of the snake by adding a new body part.
     */
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
        body.add(r);
    }

    /**
     * Changes the speed of the snake.
     *
     * @param s The speed to add to the current speed.
     */
    public void changeSpeed(int s) {
        speed += s;
    }

    // ovoride the methode tostring
    @Override
    public String toString() {
        return "Head is at [posX= " + snakeRec.getX() + ", posY=" + snakeRec.getY() + "]";
    }

}