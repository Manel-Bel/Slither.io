package fr.uparis.informatique.cpoo5.utils;

public class Coordinate {
    public int row;
    public int col;

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // string of the coordination
    @Override
    public String toString() {
        return "[row=" + row + ", col=" + col + "]";
    }
}
