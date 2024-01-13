package fr.uparis.informatique.cpoo5.utils;

public class Coordinate{
    public double row;
    public double col;

    public Coordinate(double row, double col) {
        this.row = row;
        this.col = col;
    }

    // string of the coordination
    @Override
    public String toString() {
        return "[row=" + row + ", col=" + col + "]";

    }
}
