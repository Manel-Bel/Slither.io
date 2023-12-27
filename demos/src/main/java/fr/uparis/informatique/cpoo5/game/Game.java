package fr.uparis.informatique.cpoo5.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

import fr.uparis.informatique.cpoo5.entities.Cell;

public class Game {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private ArrayList<Player> players;
    private Animation timer;
    private Cell[][] grid;
    private int nRows, nCols;
    private double scale;

    public Game(Stage stage, double scale) {
        this.scale = scale;
        this.gameStage = stage;
        gameStage.setTitle("CPOO5 - Slither - Game");

        this.gameRoot = new Pane();
        gameRoot.setMinWidth(1000 * scale);
        gameRoot.setMinHeight(600 * scale);

        // creat first player and init the pos of its snake
        Player p = new Player("Player0", 20, 20);
        this.players = new ArrayList<>();

        players.add(p);

        gameRoot.getChildren().addAll(p.getSnake().getBody());
        // init the grid
        nCols = (1000 * (int) scale) / Cell.getCellWidth();
        nRows = (600 * (int) scale) / Cell.getCellWidth();
        initGrid();

        // int the scene
        gamScene = new Scene(gameRoot);
        gamScene.setOnKeyPressed(e -> {
            players.get(0).getSnake().setDirection(e.getCode());
        });
        animate();

        gameStage.setScene(gamScene);
    }

    // init the grid for the game
    private void initGrid() {
        // calculate the number of rows and cells
        grid = new Cell[nRows][nCols];
        System.out.println("init grid");
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                grid[i][j] = new Cell(j * Cell.getCellWidth(), i * Cell.getCellWidth());
                gameRoot.getChildren().add(grid[i][j]);
            }
        }
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
