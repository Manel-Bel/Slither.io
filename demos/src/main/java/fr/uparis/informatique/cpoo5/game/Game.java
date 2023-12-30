package fr.uparis.informatique.cpoo5.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import fr.uparis.informatique.cpoo5.entities.Cell;
import fr.uparis.informatique.cpoo5.entities.Food;
import fr.uparis.informatique.cpoo5.entities.Snake;
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
    
    private Coordinate occupiedByFoodCell;

    public Game(Stage stage, double scale , int nbJoueur) {
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

        // init the snake
        

        /*
         * position of the snake could be random on the grid
         * get the posX and posY of the picked grid
         */
     

        // occupiedCells = new LinkedList<>();
        

        

        // creat  player and init the pos of its snake
        Random rand = new Random();
        this.players = new ArrayList<>();
        for(int i =0 ;i<nbJoueur ;i++){
              
            int cellX = rand.nextInt(nRows);
            int cellY = rand.nextInt(nCols);
            int sX = (int) grid[cellX][cellY].getX();
            int sY = (int) grid[cellX][cellY].getY();
            // occupiedCells.add(new Coordinate(cellX, cellY));
            // set that the gris is occupied
            grid[cellX][cellY].setOccupied(true);

            Player p = new Player("Player"+i+"", sX, sY);

            p.addOccupiedCell(new Coordinate(cellX, cellY));
            players.add(p);
            
            gameRoot.getChildren().addAll(p.getSnake().getBody());
        }
        players.get(1).getSnake().setColor(Color.RED);
        occupiedByFoodCell = null;
        
       

        

        

        // int the scene
        gamScene = new Scene(gameRoot);
        gamScene.setOnKeyPressed(e -> {
            var keyCode = e.getCode();
        
            
            if(players.size() > 1 ){
                switch (keyCode) {
                    case Z:
                        players.get(1).getSnake().setDirection(KeyCode.UP);
                        break;
                    case S:
                        players.get(1).getSnake().setDirection(KeyCode.DOWN);
                        break;
                    case Q:
                        players.get(1).getSnake().setDirection(KeyCode.LEFT);
                        break;
                    case D:
                        players.get(1).getSnake().setDirection(KeyCode.RIGHT);
                        break;
                    default:
                        players.get(0).getSnake().setDirection(e.getCode());
                        break;
                }
            }else{
                players.get(0).getSnake().setDirection(e.getCode());
            }

        
        });

        animate();

        gameStage.setScene(gamScene);
    }
    
    //pour 2v2
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

    private void update(int i) {
         if (food == null) {
            generateFood();
    
        }
        players.get(i).getSnake().move(Menu.winWidth, Menu.winHeight);
        // if (players.get(i).getSnake().checkCollision(gameRoot.getWidth(), gameRoot.getHeight())) {
        //     System.out.println("Collision!");
        //     // inverse the direction of the snake
        //     players.get(i).getSnake().switchDirection();
        // }
        updateCell(i);
        //System.out.println(players.get(i).getSnake());
       // players.get(0).getSnake().move(Menu.winWidth, Menu.winWidth);
        // // if (players.get(0).getSnake().checkCollision(gameRoot.getWidth(),
        // // gameRoot.getHeight())) {
        // // System.out.println("Collision!");
        // // // inverse the direction of the snake
        // // players.get(0).getSnake().switchDirection();
        // // }
        
        if (checkCollisionWithFood(i)) {
            eatFood(i);
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
        //System.out.println(grid[r][c]);
        occupiedByFoodCell = new Coordinate(r, c);
        // occupiedCells.add(occupiedByFoodCell);
        gameRoot.getChildren().add(food.getFood());
    }
    private void updateIA(){
            if (food == null) {
            generateFood();
    
            }
            System.out.println("fx ="+occupiedByFoodCell.x+" , fy= "+occupiedByFoodCell.y+" x ="+players.get(1).getOccupiedCells().get(0).x + " y= "+ players.get(1).getOccupiedCells().get(0).y);
        if(occupiedByFoodCell.x == players.get(1).getOccupiedCells().get(0).x){
            if(occupiedByFoodCell.y > players.get(1).getOccupiedCells().get(0).y){
                players.get(1).getSnake().setDirection(KeyCode.RIGHT);
            }else{
                players.get(1).getSnake().setDirection(KeyCode.LEFT);
            }
        }else if (occupiedByFoodCell.y == players.get(1).getOccupiedCells().get(0).y) {
            if(occupiedByFoodCell.x > players.get(1).getOccupiedCells().get(0).x){
                players.get(1).getSnake().setDirection(KeyCode.DOWN);
            }else{
                players.get(1).getSnake().setDirection(KeyCode.UP);
            }
            
        }else {
            if(occupiedByFoodCell.x > players.get(1).getOccupiedCells().get(0).x){
                players.get(1).getSnake().setDirection(KeyCode.DOWN);
            }else{
                players.get(1).getSnake().setDirection(KeyCode.UP);
            }
        }
        players.get(1).getSnake().move(Menu.winWidth, Menu.winHeight);
        
         updateCell(1);
         if (checkCollisionWithFood(1)) {
            eatFood(1);
        }
       
            
        }
     

    private void updateCell(int i) {
        Player p = players.get(i);
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
  

    // checks the collision between the head of the snake and the food
    private boolean checkCollisionWithFood(int i) {
        return (players.get(i).getOccupiedCells().get(0).x == occupiedByFoodCell.x) &&
                (players.get(i).getOccupiedCells().get(0).y == occupiedByFoodCell.y);
    }

    // eatFood(): to remove the food from the frame and extend the snake
    private void eatFood(int i) {
        gameRoot.getChildren().remove(food.getFood());
        food = null;
        Snake s = players.get(i).getSnake();
        s.extendBody();
        // add the new part of the body to the game
        gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));

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
                updateIA();
                 update(0);
                //
                // for (int i=0;i<players.size();i++) {

                   
                // }
                
               
                // i++;
                // timer.stop();
                last = now;
            }
        }
    }
}
