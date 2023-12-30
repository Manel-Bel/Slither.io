package fr.uparis.informatique.cpoo5.game;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

import fr.uparis.informatique.cpoo5.entities.Cell;
import fr.uparis.informatique.cpoo5.entities.Food;
import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.entities.SnakeAbstract;
import fr.uparis.informatique.cpoo5.game.Game.Animation;
import fr.uparis.informatique.cpoo5.ui.Menu;
import fr.uparis.informatique.cpoo5.utils.Coordinate;
import fr.uparis.informatique.cpoo5.utils.Direction;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameIA {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private Player joueur;
    private IA ia;
    private Animation timer;
    private Cell[][] grid;
    private int nRows, nCols;
    private double scale;
    private Food food = null;
    private Coordinate occupiedByFoodCell;

    public GameIA(Stage stage, double scale) {
        this.gameStage = stage;
        
        this.scale = scale;
        gameStage.setTitle("CPOO5 - Slither - Game");


        this.gameRoot = new Pane();
        gameRoot.setPrefWidth(Menu.winWidth);
        gameRoot.setPrefHeight(Menu.winHeight);
        nCols = (Menu.winWidth * (int) scale) / Cell.getCellWidth();
        nRows = (Menu.winHeight * (int) scale) / Cell.getCellWidth();
        initGrid();
        initPlayer();
        gameRoot.getChildren().addAll(((Player) joueur).getSnake().getBody());
        gameRoot.getChildren().addAll(((IA) ia).getSnake().getBody());
        ((IA) ia).getSnake().setColor(Color.RED);
        occupiedByFoodCell = null;

        gamScene = new Scene(gameRoot);
        gamScene.setOnKeyPressed(e -> {((Snake) joueur.getSnake()).setDirectionP(e.getCode());});
        ((IA) ia).getSnake().setDirection(Direction.UP);
        
        animate();
        
        gameStage.setScene(gamScene);
    }
    private void initPlayer(){
            Random rand = new Random();

            int cellX = rand.nextInt(nRows);
            int cellY = rand.nextInt(nCols);
            int sX = (int) grid[cellX][cellY].getX();
            int sY = (int) grid[cellX][cellY].getY();
            
            grid[cellX][cellY].setOccupied(true);
            joueur=new Player("joueur 1", sX, sY);

            
            int cellZ = rand.nextInt(nRows);
            int cellW = rand.nextInt(nCols);
            int sZ = (int) grid[cellZ][cellW].getX();
            int sW = (int) grid[cellZ][cellW].getY();

            ia=new IA("AI", sZ, sW);
        }

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
        //System.out.println(grid[r][c]);
        occupiedByFoodCell = new Coordinate(r, c);
        // occupiedCells.add(occupiedByFoodCell);
        gameRoot.getChildren().add(food.getFood());
    }


    private void update(Person joueur) {
        if (food == null) {
            generateFood();
    
        }
        joueur.getSnake().move(Menu.winWidth, Menu.winHeight);
        updateCell(joueur);

        if (checkCollisionWithFood(joueur)) {
            eatFood(joueur);
        }
    
    }

    private void updateCell(Person p) {
      
        Direction d = p.getSnake().getDirection();
        if (d == null)
            return;
        // the head of the snake
        int r = p.getOccupiedCells().get(0).x;
        int c = p.getOccupiedCells().get(0).y;
        int privR = r;
        int privC = c;
        switch (d) {
            case UP:
                r -= 1;
                if (r < 0) {
                    r = nRows - 1;
                }
                break;
            case RIGHT:
                c += 1;
                if (c >= nCols) {
                    c = 0;
                }
                break;
            case DOWN:
                r += 1;
                if (r >= nRows) {
                    r = 0;
                }
                break;
            case LEFT:
                c -= 1;
                if (c < 0) {
                    c = nCols - 1;
                }
                break;
        }
        grid[privR][privC].setOccupied(false);
        // set to occupied the new cell
        grid[r][c].setOccupied(true);
        //System.out.println("the new cell occupied by the head of the snake " + grid[r][c]);
        p.getOccupiedCells().get(0).x = r;
        p.getOccupiedCells().get(0).y = c;
    }

    private boolean checkCollisionWithFood(Person p) {
        return (p.getOccupiedCells().get(0).x == occupiedByFoodCell.x) &&
                (p.getOccupiedCells().get(0).y == occupiedByFoodCell.y);
    }

    private void eatFood(Person p) {
        gameRoot.getChildren().remove(food.getFood());
        food = null;
        SnakeAbstract s =p.getSnake();
        s.extendBody();
        p.setScore(1);
        // add the new part of the body to the game
        gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));

    }

  




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
                update(ia);
                update(joueur);
                
               
                last = now;
            }
        }

        
    }

}
