package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;

public class Player {
    private final String name;
    private int score = 0;
    private Snake snake;

    public Player(String name) {
        this.name = name;
        this.snake = new Snake();
    }

    public void setScore(int p) {
        this.score += p;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public Snake getSnake() {
        return this.snake;
    }
}
