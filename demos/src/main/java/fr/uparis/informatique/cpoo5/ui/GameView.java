package fr.uparis.informatique.cpoo5.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.game.Game;
import fr.uparis.informatique.cpoo5.game.Player;
import fr.uparis.informatique.cpoo5.game.Game.DataPlayer;
import fr.uparis.informatique.cpoo5.utils.Direction;

public class GameView {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private double scale;
    private Animation timer;
    private Game game;
    private boolean solo, ia;

    public GameView(Stage stage, double scale, boolean solo, boolean ia) {
        this.scale = scale;
        this.solo = solo;
        this.ia = ia;
        this.gameStage = stage;
        gameStage.setTitle("CPOO5 - Slither - Game");

        this.gameRoot = new Pane();
        gameRoot.setPrefWidth(Menu.winWidth * this.scale);
        gameRoot.setPrefHeight(Menu.winHeight * this.scale);

        this.game = new Game(solo, ia);
        addGridToScreen();
        addSnakesToTheScreen();

        gamScene = new Scene(gameRoot);
        // set the keys for the scene
        setKeysScene();

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
    private void addSnakesToTheScreen() {
        for (DataPlayer data : game.getDataPlayer()) {
            gameRoot.getChildren().addAll(data.player.getSnake().getBody());
        }
    }

    // set the keys for the scene
    private void setKeysScene() {
        gamScene.setOnKeyPressed(e -> {
            var keyCode = e.getCode();
            if (!solo && !ia) {
                Player p = (Player) game.getDataPlayer().getLast().player;
                switch (keyCode) {
                    case Z:
                        p.getSnake().setDirection(Direction.UP);
                        break;
                    case S:
                        p.getSnake().setDirection(Direction.DOWN);
                        break;
                    case Q:
                        p.getSnake().setDirection(Direction.LEFT);
                        break;
                    case D:
                        p.getSnake().setDirection(Direction.RIGHT);
                        break;
                    default:
                        game.getDataPlayer().getFirst().player.getSnake()
                                .setDirection(Direction.getDirection(e.getCode()));
                        break;
                }
            } else
                game.getDataPlayer().getFirst().player.getSnake()
                        .setDirection(Direction.getDirection(e.getCode()));
        });
    }

    public static Object launchSolitaire() {
        return null;
    }

    public static Object launchNetworked() {
        return null;
    }

    // return true if the game ended
    private boolean update(double deltaT) {
        if (game.getFood() == null) {
            System.out.println("gameview generation of food");
            gameRoot.getChildren().add(game.generateFood());
        }
        // move all the snakes
        for (DataPlayer data : game.getDataPlayer()) {
            data.player.moveSnake(game.getCoordFood(), data.occupiedCells.getFirst(), deltaT);
            game.updateCell(data);
            // check collision
            if (game.isAutoCollision(data)) {
                // endOfGame = true;
                return true;
            }
            if (game.checkCollisionWithFood(data)) {
                System.out.println("check coll with food gameView");
                gameRoot.getChildren().remove(game.getFood().getFood());
                game.eatFood(data);
                Snake s = data.player.getSnake();
                gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));
            }
        }
        return false;
    }

    // classe for the animation of the game
    class Animation extends AnimationTimer {
        long last = 0;
        private final long waitInterval = 100_000_000; // 400ms

        @Override
        public void handle(long now) {
            // gameRoot.requestFocus();
            if (last == 0) {
                last = now;
                return;
            }

            if (now - last >= waitInterval) {
                double deltaT = (now - last) / 1e9;
                if (update(deltaT)) { // game ended
                    System.out.println("collision animation ends...");
                    stop();
                }
                last = now;
            }
        }
    }
}
