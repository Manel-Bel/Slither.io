package fr.uparis.informatique.cpoo5.game;

public abstract  class  Person {
    private final String name;
    private int score = 0;

    public Person(String name){
        this.name = name;
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
    @Override
    public String toString() {
        return "Player: " + name + ", Score: " + score;
    }
}
