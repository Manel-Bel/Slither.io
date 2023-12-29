package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;

public final class Player {
    private final String name;
    private int score = 0;
    private Snake snake;

    public Player(String name, int x, int y) {
        this.name = name;
        this.snake = new Snake(x, y);
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

    @Override
    public String toString() {
        return "Player: " + name + ", Score: " + score;
    }
}
