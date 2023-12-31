package fr.uparis.informatique.cpoo5.ui;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.game.Game;
import fr.uparis.informatique.cpoo5.game.Player;

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

        this.game = new Game();
        addGridToScreen();
        addPlayerToTheScreen();

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

    // add the grid
    private void addGridToScreen() {
        for (int i = 0; i < game.getNrows(); i++) {
            for (int j = 0; j < game.getNcols(); j++) {
                gameRoot.getChildren().add(game.getGrid()[i][j]);
            }
        }
    }

    // add the snakes to th screen
    public void addPlayerToTheScreen() {
        for (Player p : game.getPlayers()) {
            gameRoot.getChildren().addAll(p.getSnake().getBody());
        }
    }

    public static Object launchSolitaire() {
        return null;
    }

    public static Object launchNetworked() {
        return null;
    }

    // return true if the game ended
    private boolean update() {
        if (game.getFood() == null) {
            System.out.println("gameview generation of food");
            gameRoot.getChildren().add(game.generateFood());
        }
        // move all the snakes
        for (Player p : game.getPlayers()) {
            p.getSnake().move(Menu.winWidth, Menu.winHeight);
            game.updateCell();

            // check collision
            if (game.isAutoCollision(0)) {
                // endOfGame = true;
                return true;
            }
            if (game.checkCollisionWithFood()) {
                System.out.println("check coll with food gameView");
                gameRoot.getChildren().remove(game.getFood().getFood());
                game.eatFood();
                Snake s = p.getSnake();
                gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));
            }
        }
        return false;
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
                if (update()) { // game ended
                    System.out.println("collision animation ends...");
                    stop();
                }
                last = now;
            }
        }
    }
}
