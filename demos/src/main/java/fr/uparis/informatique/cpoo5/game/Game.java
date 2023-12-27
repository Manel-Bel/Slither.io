package fr.uparis.informatique.cpoo5.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

import fr.uparis.informatique.cpoo5.utils.Direction;

public class Game {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private ArrayList<Player> players;
    private Animation timer;

    public Game(Stage stage, double scale) {
        this.gameStage = stage;
        gameStage.setTitle("CPOO5 - Game");

        this.gameRoot = new Pane();
        gameRoot.setMinWidth(1000 * scale + 2 * 50);
        gameRoot.setMinHeight(600 * scale);
        Player p = new Player("Player0");
        this.players = new ArrayList<>();

        players.add(p);

        gamScene = new Scene(gameRoot);
        gamScene.setOnKeyPressed(e -> {
            players.get(0).getSnake().setDirection(e.getCode());
        });
        gameRoot.getChildren().addAll(p.getSnake().getBody());
        animate();

        gameStage.setScene(gamScene);
    }

    private void animate() {
        timer = new Animation();
        timer.start();
    }

    public static Object launchSolitaire() {
        return null;
    }

    public static Object launchNetworked() {
        return null;
    }

    private void update() {
        players.get(0).getSnake().move();
        if (players.get(0).getSnake().checkCollision(gameRoot.getWidth(), gameRoot.getHeight())) {
            System.out.println("Collision!");
            // inverse the direction of the snake
            players.get(0).getSnake().switchDirection();

        }
    }

    class Animation extends AnimationTimer {
        long last = 0;

        @Override
        public void handle(long now) {
            // gameRoot.requestFocus();
            if (last == 0) {
                last = now;
                return;
            }
            double deltaT = (now - last) / 1e9;
            last = now;
            update();
        }
    }
}
