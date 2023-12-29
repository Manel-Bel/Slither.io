package fr.uparis.informatique.cpoo5.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import fr.uparis.informatique.cpoo5.entities.*;
import fr.uparis.informatique.cpoo5.ui.Menu;
import fr.uparis.informatique.cpoo5.utils.Coordinate;
import fr.uparis.informatique.cpoo5.utils.Direction;

public class Game {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private ArrayList<Player> players;
    private Animation timer;
    private Cell[][] grid;
    private int nRows, nCols;
    private double scale;
    private Food food = null;
    private LinkedList<Coordinate> occupiedCells;
    private Coordinate occupiedByFoodCell;

    public Game(Stage stage, double scale) {
        this.scale = scale;
        this.gameStage = stage;
        gameStage.setTitle("CPOO5 - Slither - Game");

        this.gameRoot = new Pane();
        gameRoot.setPrefWidth(Menu.winWidth);
        gameRoot.setPrefHeight(Menu.winHeight);

        // init the grid
        nCols = (Menu.winWidth * (int) scale) / Cell.getCellWidth();
        nRows = (Menu.winHeight * (int) scale) / Cell.getCellWidth();
        initGrid();

        /*
         * position of the snake could be random on the grid
         * get the posX and posY of the picked grid
         */
        Random rand = new Random();
        int cellR = rand.nextInt(nRows);
        int cellC = rand.nextInt(nCols);
        int sX = (int) grid[cellR][cellC].getX();
        int sY = (int) grid[cellR][cellC].getY();
        // set that the gris is occupied
        grid[cellR][cellC].setOccupied(true);

        occupiedCells = new LinkedList<>();
        occupiedCells.add(new Coordinate(cellR, cellC));

        occupiedByFoodCell = null;

        // creat first player and init the pos of its snake
        Player p = new Player("Player0", sX, sY);
        this.players = new ArrayList<>();

        players.add(p);

        gameRoot.getChildren().addAll(p.getSnake().getBody());

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
                if (i == nRows - 1) {
                    grid[i][j].setColor(Color.BLUEVIOLET);
                }
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
        if (food == null) {
            generateFood();
        }
        players.get(0).getSnake().move(Menu.winWidth, Menu.winWidth);
        updateCell();
        // System.out.println(players.get(0).getSnake());
        if (isAutoCollision(0)) {
            timer.stop();
        }
        if (checkCollisionWithFood()) {
            eatFood();
        }
    }

    // generate foor at a random position
    private void generateFood() {
        Random rand = new Random();
        int r = 0, c = 0;
        double x, y;
        do {
            r = rand.nextInt(nRows);
            c = rand.nextInt(nCols);
            x = grid[r][c].getX() + (Cell.getCellWidth() / 2);
            y = grid[r][c].getY() + (Cell.getCellWidth() / 2);
            System.out.println("x " + x + " y " + y);

        } while (grid[r][c].isOccupied());
        food = new Food((int) x, (int) y);
        grid[r][c].setOccupied(true);
        System.out.println(grid[r][c]);
        occupiedByFoodCell = new Coordinate(r, c);
        // occupiedCells.add(occupiedByFoodCell);
        gameRoot.getChildren().add(food.getFood());
    }

    private void updateCell() {
        Direction d = players.get(0).getSnake().getDirection();
        if (d == null)
            return;
        // the head of the snake
        int headR = occupiedCells.get(0).row;
        int headC = occupiedCells.get(0).col;
        // int privR = r,privC = c;
        switch (d) {
            case UP:
                headR = (headR - 1 + nRows) % nRows;
                break;
            case RIGHT:
                headC = (headC + 1) % nCols;
                break;
            case DOWN:
                headR = (headR + 1) % nRows;
                break;
            case LEFT:
                headC = (headC - 1 + nCols) % nCols;
                break;
        }
        // set free the last cell of the grid
        grid[occupiedCells.getLast().row][occupiedCells.getLast().col].setOccupied(false);
        // grid[privR][privC].setOccupied(false);

        // update the cells of the body
        for (int i = occupiedCells.size() - 1; i > 0; i--) {
            // occupiedCells.set(i, occupiedCells.get(i - 1));
            occupiedCells.get(i).row = occupiedCells.get(i - 1).row;
            occupiedCells.get(i).col = occupiedCells.get(i - 1).col;
        }

        // set to occupied the new cell (head of the snake)
        grid[headR][headC].setOccupied(true);

        occupiedCells.get(0).row = headR;
        occupiedCells.get(0).col = headC;
    }

    // checks the collision between the head of the snake and the food
    private boolean checkCollisionWithFood() {
        return (occupiedCells.get(0).row == occupiedByFoodCell.row) &&
                (occupiedCells.get(0).col == occupiedByFoodCell.col);
    }

    private boolean isAutoCollision(int snakeIndex) {
        // normally we retrive the cells based of the snake
        for (int i = 0; i < occupiedCells.size() - 1; i++) {
            for (int j = i + 1; j < occupiedCells.size(); j++) {
                if (occupiedCells.get(i).row == occupiedCells.get(j).row
                        && occupiedCells.get(i).col == occupiedCells.get(j).col) {
                    System.out.println("Auto collision!");
                    return true;
                }
            }
        }
        return false;
    }

    // eatFood(): to remove the food from the frame and extend the snake
    private void eatFood() {
        gameRoot.getChildren().remove(food.getFood());
        food = null;
        Snake s = players.get(0).getSnake();
        s.extendBody();
        // add the new part of the body to the game
        gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));
        // add the new occupied cell
        Rectangle r = s.getBody().get(s.getBody().size() - 1);
        System.out.println("new body part [x= " + r.getX() + " y= " + r.getY() + "]");
        Coordinate c = new Coordinate((int) (r.getY() / Cell.getCellWidth()), (int) (r.getX() / Cell.getCellWidth()));
        grid[c.row][c.col].setOccupied(true);
        System.out.println(c);
        occupiedCells.add(c);
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
                update();
                last = now;
            }
        }
    }
}
