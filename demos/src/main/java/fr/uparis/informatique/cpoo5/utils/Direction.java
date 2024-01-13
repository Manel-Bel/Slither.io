package fr.uparis.informatique.cpoo5.utils;

import javafx.scene.input.KeyCode;

public enum Direction {
    NONE,
    UP,
    RIGHT,
    DOWN,
    LEFT;

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
                return Direction.NONE;
        }
    }

    // get opposite direction
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
            default:
                break;
        }
        return d;
    }
}
