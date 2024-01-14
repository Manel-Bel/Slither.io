package fr.uparis.informatique.cpoo5.ui;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.LinkedList;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.game.DecisionMaker;
import fr.uparis.informatique.cpoo5.game.Game;
import fr.uparis.informatique.cpoo5.game.Player;
import fr.uparis.informatique.cpoo5.game.Game.DataPlayer;
import fr.uparis.informatique.cpoo5.utils.Direction;

public class GameView {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private GridPane root;
    private double scale;
    private Animation timer;
    private Game game;
    private boolean solo, ia;
    private GameHeader gameHeader;
    private PauseWin pauseWin;

    public GameView(Stage stage, double scale, boolean solo, boolean ia) {
        this.scale = scale;
        this.solo = solo;
        this.ia = ia;
        this.gameStage = stage;
        gameStage.setTitle("CPOO5 - Slither - Game");

        this.gameRoot = new Pane();
        gameRoot.setPrefWidth(Menu.winWidth * this.scale);
        gameRoot.setPrefHeight(Menu.winHeight - 80);

        this.game = new Game(solo, ia);
        addGridToScreen();
        addSnakesToTheScreen();

        root = new GridPane();
        gameRoot.setPrefWidth(Menu.winWidth);
        gameRoot.setMinHeight(Menu.winHeight);
        root.add(gameHeader.getNavBar(), 0, 0);
        root.add(gameRoot, 0, 1);

        gamScene = new Scene(root);
        root.requestFocus();
        // set the keys for the scene
        setKeysScene();
        setKeyPause();

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
        LinkedList<DecisionMaker> p = new LinkedList<>();
        for (DataPlayer data : game.getDataPlayer()) {
            gameRoot.getChildren().addAll(data.player.getSnake().getBody());
            p.add(data.player);
        }
        gameHeader = new GameHeader(p);
    }

    // set the keys for the scene
    private void setKeysScene() {
        gamScene.setOnKeyPressed(e -> {
            var keyCode = e.getCode();
            System.out.println(e.getCode());
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

    private void setKeyPause() {
        gameHeader.getPauseBtn().setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (event.getSource() == gameHeader.getPauseBtn()) {
                    timer.stop();
                    pauseWin = new PauseWin();
                    gameHeader.getPauseBtn().setDisable(true);
                }
            }
        });
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
        for (DataPlayer data : game.getDataPlayer()) {
            data.player.moveSnake(game.getCoordFood(), data.occupiedCells.getFirst());
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
        private final long waitInterval = 400_000_000; // 400ms

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

    // pause class
    private class PauseWin {
        private Stage pauseStage;
        private Scene pauseSc;
        private VBox root;
        private Button quitBtn, resumeBtn;

        public PauseWin() {
            this.root = new VBox(10);
            this.root.setMinWidth(250);
            this.root.setMinHeight(250);
            this.root.setAlignment(Pos.CENTER);
            Label titre = new Label("Pause");
            titre.setId("titrelabel");
            resumeBtn = new Button("Resume the game");
            quitBtn = new Button("Quit");
            quitBtn.setMaxSize(170, 170);
            handleBtn();

            this.root.getChildren().addAll(titre, resumeBtn, quitBtn);

            pauseSc.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());

            this.pauseStage = new Stage();
            pauseStage.setTitle("Pause");
            pauseStage.setScene(pauseSc);
            pauseStage.setResizable(false);
            pauseStage.centerOnScreen();
            pauseStage.toFront();
            pauseStage.show();

        }

        private void handleBtn() {
            pauseStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    gameHeader.getPauseBtn().setDisable(false);
                    GameView.this.animate();
                }
            });
            quitBtn.setOnAction(e -> new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    if (event.getSource() == quitBtn) {
                        PauseWin.this.pauseStage.close();
                        GameView.this.timer.stop();

                    }
                }
            });

        }
    }
}
