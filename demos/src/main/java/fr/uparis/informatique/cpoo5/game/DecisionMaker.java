package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.utils.Coordinate;

public sealed interface DecisionMaker permits AI, Player {

    public void setScore(int p);

    public int getScore();

    public Snake getSnake();

    public void moveSnake(Coordinate coordinateFood, Coordinate coordinateS, double deltaT);

    // public void setDirectionSnake(KeyCode e, Coordinate coordinateFood,
    // Coordinate coordinateS);
}
