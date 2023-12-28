package fr.uparis.informatique.cpoo5.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import fr.uparis.informatique.cpoo5.entities.Cell;
import fr.uparis.informatique.cpoo5.entities.Food;
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

    public Game(Stage stage, double scale , int nbJoueur) {
        this.scale = scale;
        this.gameStage = stage;
        gameStage.setTitle("CPOO5 - Slither - Game");

        this.gameRoot = new Pane();
        gameRoot.setMinWidth(1000 * scale);
        gameRoot.setMinHeight(600 * scale);

        // init the grid
        nCols = (1000 * (int) scale) / Cell.getCellWidth();
        nRows = (600 * (int) scale) / Cell.getCellWidth();
        initGrid();

        // init the snake
        

        /*
         * position of the snake could be random on the grid
         * get the posX and posY of the picked grid
         */
     

        occupiedCells = new LinkedList<>();
        

        

        // creat  player and init the pos of its snake
        Random rand = new Random();
        this.players = new ArrayList<>();
        for(int i =0 ;i<nbJoueur ;i++){
              
            int cellX = rand.nextInt(nRows);
            int cellY = rand.nextInt(nCols);
            int sX = (int) grid[cellX][cellY].getX();
            int sY = (int) grid[cellX][cellY].getY();
            occupiedCells.add(new Coordinate(cellX, cellY));
            // set that the gris is occupied
            grid[cellX][cellY].setOccupied(true);

            Player p = new Player("Player"+i+"", sX, sY);
            players.add(p);
            gameRoot.getChildren().addAll(p.getSnake().getBody());
        }
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
      
        players.get(i).getSnake().move();
        if (players.get(i).getSnake().checkCollision(gameRoot.getWidth(), gameRoot.getHeight())) {
            System.out.println("Collision!");
            // inverse the direction of the snake
            players.get(i).getSnake().switchDirection();
        }
        updateCell(i);
        System.out.println(players.get(i).getSnake());
        if (food == null) {
            generateFood();
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

    private void updateCell(int i) {
        Direction d = players.get(i).getSnake().getDirection();
        if (d == null)
            return;
        // the head of the snake
        int r = occupiedCells.get(i).x;
        int c = occupiedCells.get(i).y;
        int privR = r;
        int privC = c;
        switch (d) {
            case UP:
                r -= 1;
                break;
            case RIGHT:
                c += 1;
                break;
            case DOWN:
                r += 1;
                break;
            case LEFT:
                c -= 1;
                break;
        }
        grid[privR][privC].setOccupied(false);
        // set to occupied the new cell
        grid[r][c].setOccupied(true);
        //System.out.println("the new cell occupied by the head of the snake " + grid[r][c]);
        occupiedCells.get(i).x = r;
        occupiedCells.get(i).y = c;
    }
    private int passe_la_main(int i){
        
        if(i>=players.size()){
           return 0;
        }else{
            return i++;
        }
        
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
              
                for (int i=0;i<players.size();i++) {
                    update(i);
                }
                
               
                // i++;
                // timer.stop();
                last = now;
            }
        }
    }
}
