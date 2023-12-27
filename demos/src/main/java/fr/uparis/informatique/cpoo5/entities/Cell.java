package fr.uparis.informatique.cpoo5.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    private boolean occupied;
    private Color color;

    private static final int gridWidth = 20;
    // private int posX, posY;

    // private final int cellWidth, cellHeight;

    public Cell(int x, int y) {
        super(gridWidth, gridWidth);

        this.setX(x);
        this.setY(y);
        this.occupied = false;
        this.color = Color.BLACK;
        this.setFill(null);
        this.setStroke(color);
    }

    // change color
    public void setColor(Color c) {
        this.color = c;
        this.setFill(color);
    }

    // set position x
    public void setPosX(int x) {
        this.setX(x);
    }

    // set position y
    public void setPosY(int y) {
        this.setY(y);
    }

    // check if the cell is free
    public boolean isOccupied() {
        return occupied;
    }

    // change the value (if is is an occupied cell or not)
    public void setOccupied(boolean b) {
        this.occupied = b;
    }

    @Override
    public String toString() {
        return "Cell [posX=" + getX() + ", posY=" + getY() + ", width=" + getWidth() +
                ", height=" + getHeight() + " is occupied ?:" + occupied + "]";
    }
    /*
     * + ", color=0x" + Integer.toHexString((int) (color).getRed()) +
     * Integer.toHexString((int) (color).getGreen()) + Integer.toHexString((int)
     * (color).getBlue())
     */

    public static int getCellWidth() {
        return gridWidth;
    }

}
