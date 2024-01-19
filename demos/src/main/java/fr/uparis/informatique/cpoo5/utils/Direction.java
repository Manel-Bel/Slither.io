package fr.uparis.informatique.cpoo5.utils;

import javafx.scene.input.KeyCode;

/**
 * Represents the possible directions in the game
 */
public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    /**
     * Gets the corresponding direction from a KeyCode.
     *
     * @param keyCode The KeyCode to convert to a Direction.
     * @return The corresponding Direction.
     */
    public static Direction getDirection(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                return Direction.UP;
            case RIGHT:
                return Direction.RIGHT;
            case DOWN:
                return Direction.DOWN;
            case LEFT:
                return Direction.LEFT;
            default:
                break;
        }
        return null;
    }

    /**
     * Gets the opposite direction of the given direction.
     *
     * @param d The original direction.
     * @return The opposite direction.
     */
    public static Direction getOpposite(Direction d) {
        switch (d) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
        }
        return d;
    }
}
