package fr.uparis.informatique.cpoo5.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Cell class represents a cell in the game grid.
 * It extends the JavaFX Rectangle class and includes additional functionality
 * to manage the state and appearance of the cell.
 * 
 * @author : Belguenbour Manel
 */
public class Cell extends Rectangle {
    private boolean occupied;
    private Color color;

    private static final int gridWidth = 20;

    /**
     * Constructs a new instance of the Cell class.
     *
     * @param x The x-coordinate of the cell.
     * 
     * @param y The y-coordinate of the cell
     * 
     */
    public Cell(int x, int y) {
        super(gridWidth, gridWidth);

        this.setX(x);
        this.setY(y);
        this.occupied = false;
        this.color = Color.BLACK;
        this.setFill(null);
        this.setStroke(color);
    }

    /**
     * Changes the color of the cell.
     *
     * @param c The new color for the cell.
     */
    public void setColor(Color c) {
        this.color = c;
        this.setFill(color);
    }

    /**
     * Sets the x-coordinate of the cell.
     *
     * @param x The new x-coordinate for the cell.
     */
    public void setPosX(int x) {
        this.setX(x);
    }

    /**
     * Sets the y-coordinate of the cell.
     *
     * @param y The new y-coordinate for the cell.
     */
    public void setPosY(int y) {
        this.setY(y);
    }

    /**
     * Checks if the cell is occupied.
     *
     * @return True if the cell is occupied, false otherwise.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Changes the occupied state of the cell.
     *
     * @param b The new occupied state for the cell.
     */
    public void setOccupied(boolean b) {
        this.occupied = b;
    }

    @Override
    public String toString() {
        return "Cell [posX=" + getX() + ", posY=" + getY() + ", width=" + getWidth() +
                ", height=" + getHeight() + " is occupied ?:" + occupied + "]";
    }

    /**
     * Gets the width of a cell.
     *
     * @return The width of a cell.
     */
    public static int getCellWidth() {
        return gridWidth;
    }

}
