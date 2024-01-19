package fr.uparis.informatique.cpoo5.utils;

/**
 * Represents a coordinate with row and column values.
 * 
 * @author : Belguenbour Manel
 */
public class Coordinate {
    public int row;
    public int col;

    /**
     * Constructs a Coordinate object with the specified row and column values.
     *
     * @param row The row value.
     * @param col The column value.
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns a string representation of the coordinate.
     *
     * @return A string containing the row and column values.
     */
    @Override
    public String toString() {
        return "[row=" + row + ", col=" + col + "]";

    }

    /**
     * Checks if the specified object's row and column are equal to this Coordinate.
     *
     * @param obj The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate && obj != null) {
            Coordinate c = (Coordinate) obj;
            return this.row == c.row && this.col == c.col;
        }
        return false;
    }

}
