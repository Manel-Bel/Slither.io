package fr.uparis.informatique.cpoo5.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food {

    private Circle foodCircle;
    private double foodX, foodY;
    private Color color;
    private final double size;

    public Food(double x, double y) {
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
    public double getX() {
        return foodX;
    }

    // get position Y
    public double getY() {
        return foodY;
    }

    public void setX(double x) {
        this.foodX = x;
        foodCircle.setCenterX(x);
    }

    public void setY(double y) {
        this.foodY = y;
        foodCircle.setCenterY(y);
    }
}
