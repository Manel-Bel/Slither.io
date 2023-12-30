package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.entities.SnakeIA;

public class IA extends Person{
    private SnakeIA snake;
    public IA(String name , int x ,int y) {
        super(name);
        this.snake = new SnakeIA(x, y);
        
    }
    public SnakeIA getSnake() {
        return this.snake;
    }

    
}
