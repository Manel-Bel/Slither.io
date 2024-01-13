package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public interface DecisionMaker {

    public void setScore(int p);

    public int getScore();

    public Snake getSnake();

    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS ,double width,double heigth);

    // public void setDirectionSnake(KeyCode e, Coordinate coordinateFood,
    // Coordinate coordinateS);
}
