package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public abstract class DecisionMaker {
    String name;
    int score = 0;
    Snake snake;

    public void setScore(int p) {
        score += p;
    }

    public int getScore() {
        return score;
    }

    public Snake getSnake() {
        return snake;
    }

    public abstract void moveSnake(Coordinate coordinateFood, Coordinate coordinateS);

    // public void setDirectionSnake(KeyCode e, Coordinate coordinateFood,
    // Coordinate coordinateS);
}
