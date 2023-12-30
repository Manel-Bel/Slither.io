package fr.uparis.informatique.cpoo5.ui;

import fr.uparis.informatique.cpoo5.game.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameView {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private double scale;
    private Animation timer;
    private Game game;

    public GameView(Stage stage, double scale) {
        this.scale = scale;
        this.gameStage = stage;
        gameStage.setTitle("CPOO5 - Slither - Game");

        this.gameRoot = new Pane();
        gameRoot.setPrefWidth(Menu.winWidth * this.scale);
        gameRoot.setPrefHeight(Menu.winHeight * this.scale);

        this.game = new Game(gameRoot);

        // int the scene
        gamScene = new Scene(gameRoot);
        gamScene.setOnKeyPressed(e -> {
            game.getPlayers().get(0).getSnake().setDirection(e.getCode());
        });

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

    // classe for the animation of the game
    class Animation extends AnimationTimer {
        long last = 0;
        private final long waitInterval = 500_000_000; // 400ms

        @Override
        public void handle(long now) {
            // gameRoot.requestFocus();
            if (last == 0) {
                last = now;
                return;
            }
            // double deltaT = (now - last) / 1e9;
            if (now - last >= waitInterval) {
                if (game.update()) { // game ended
                    System.out.println("collision animation ends...");
                    stop();
                }
                last = now;
            }
        }
    }
}
