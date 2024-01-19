package fr.uparis.informatique.cpoo5.entities;

import fr.uparis.informatique.cpoo5.utils.ColorManager;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Food class represents a food item in the game.
 * It is consumed by snakes to increase their score.
 * 
 * @author : Belguenbour Manel
 */

public class Food {

    private Circle foodCircle;
    private int foodX, foodY;
    private Color color;
    private final double size;

    /**
     * Constructs a new instance of the Food class with the specified initial
     * coordinates.
     *
     * @param x The initial x-coordinate of the food.
     * @param y The initial y-coordinate of the food.
     */
    public Food(int x, int y) {
        size = 5;
        foodX = x;
        foodY = y;
        color = ColorManager.getAvailableColor();
        foodCircle = new Circle(x, y, size, color);
    }

    /**
     * Gets the circle representing the food.
     *
     * @return The Circle object representing the food.
     */
    public Circle getFood() {
        return foodCircle;
    }

    /**
     * Gets the x-coordinate of the food.
     *
     * @return The x-coordinate of the food.
     */
    public int getX() {
        return foodX;
    }

    /**
     * Gets the y-coordinate of the food.
     *
     * @return The y-coordinate of the food.
     */
    public int getY() {
        return foodY;
    }

    /**
     * Sets the x-coordinate of the food and updates the circle's center.
     *
     * @param x The new x-coordinate of the food.
     */
    public void setX(int x) {
        this.foodX = x;
        foodCircle.setCenterX(x);
    }

    /**
     * Sets the y-coordinate of the food and updates the circle's center.
     *
     * @param y The new y-coordinate of the food.
     */
    public void setY(int y) {
        this.foodY = y;
        foodCircle.setCenterY(y);
    }

    /**
     * Gets the color of the food
     *
     * @return color of the food
     */
    public Color getColor() {
        return color;
    }
}
