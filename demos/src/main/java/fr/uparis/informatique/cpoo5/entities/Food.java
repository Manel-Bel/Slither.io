package fr.uparis.informatique.cpoo5.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food {

    private Circle foodCircle;
    private int foodX, foodY;
    private Color color;
    private final double size;

    public Food(int x, int y) {
        size = 5;
        foodX = x;
        foodY = y;
        color = Color.RED;
        foodCircle = new Circle(x, y, size, color);
    }

    // get the circle
    public Circle getFood() {
        return foodCircle;
    }

    // get position x
    public int getX() {
        return foodX;
    }

    // get position Y
    public int getY() {
        return foodY;
    }

    public void setX(int x) {
        this.foodX = x;
        foodCircle.setCenterX(x);
    }

    public void setY(int y) {
        this.foodY = y;
        foodCircle.setCenterY(y);
    }
}
