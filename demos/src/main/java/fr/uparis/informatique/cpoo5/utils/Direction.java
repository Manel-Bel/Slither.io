package fr.uparis.informatique.cpoo5.utils;

import javafx.scene.input.KeyCode;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT, UP_LEFT, DOWN_LEFT, DOWN_RIGHT, UP_RIGHT;

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
        }
        return d;
    }
}
