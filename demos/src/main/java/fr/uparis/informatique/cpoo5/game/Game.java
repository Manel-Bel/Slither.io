package fr.uparis.informatique.cpoo5.game;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.LinkedList;
import java.util.Random;
import fr.uparis.informatique.cpoo5.entities.*;
import fr.uparis.informatique.cpoo5.ui.Menu;
import fr.uparis.informatique.cpoo5.utils.*;

public class Game {
    private Cell[][] grid;
    private int nRows, nCols;
    private Food food = null;
    private Coordinate occupiedByFoodCell;
    private boolean endOfGame;
    private LinkedList<DataPlayer> dataPlayers;
    private boolean solo, ia;

    public Game(boolean solo, boolean ia) {
       // this.food=new Food(nRows, nCols);
        this.solo = solo;
        this.ia = ia;
        // init the grid
        nCols = (Menu.winWidth) / Cell.getCellWidth();
        nRows = (Menu.winHeight) / Cell.getCellWidth();
        //initGrid();

        occupiedByFoodCell = null;

        // add the players and their snakes
        dataPlayers = new LinkedList<>();
        initPlayers();
    }

    // return the row, col, posX, posy of a free cell
    private int[] chooseFreeCell() {
        Random rand = new Random();
        int r = 0, c = 0;
        double x, y;
        do {
            r = rand.nextInt(nRows);
            c = rand.nextInt(nCols);
            x = grid[r][c].getX();
            y = grid[r][c].getY();

        } while (grid[r][c].isOccupied());
        return new int[] { r, c, (int) x, (int) y };
    }

    // init players
    private void initPlayers() {
        int nbPlayer = (solo) ? 1 : 2;
        for (int i = 0; i < nbPlayer; i++) {
           // int[] tab = chooseFreeCell();
            //grid[tab[0]][tab[1]].setOccupied(true);
             DecisionMaker p;
            // if (ia && i == 1)
            //     p = new IA(tab[2], tab[3]);
            // else
                p = new Player("Player" + i, 0,100);
            DataPlayer c = new DataPlayer(p);
            c.occupiedCells.add(new Coordinate(0,100));
            dataPlayers.add(c);
        }
    }

    // init the grid for the game
    private void initGrid() {
        // calculate the number of rows and cells
        grid = new Cell[nRows][nCols];
        System.out.println("init grid");
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                grid[i][j] = new Cell(j * Cell.getCellWidth(), i * Cell.getCellWidth());
                // gameRoot.getChildren().add(grid[i][j]);
            }
        }
    }
    private double [] generateCoord(double width,double heigth){
        Random rand = new Random();
      double [] tab=new double[2];
   
           tab[0] = rand.nextDouble(width);
            tab[1] = rand.nextDouble(heigth);
            return tab;
          

        
    }

    // generate foor at a random position
    public Circle generateFood(double width ,double heigth) {
       
        //System.out.println("food x=" + tab[2] + ",y=" + tab[3]);
        double [] tab = generateCoord(width, heigth);
        food = new Food(tab[0], tab[1]);
       
        occupiedByFoodCell = new Coordinate(tab[0], tab[1]);
        return food.getFood();
    }
     public Circle generateFoodD() {
        Random rand = new Random();
        double r = 0, c = 0;
        double x, y;
        
            r = rand.nextDouble(500);
            c = rand.nextDouble(500);
            // x = grid[r][c].getX();
            // y = grid[r][c].getY();

        
        food = new Food(r,c);
       
        occupiedByFoodCell = new Coordinate(r,c);
        return food.getFood();
    }

    public void updateCell(DataPlayer data) {
        Direction d = data.player.getSnake().getDirection();
        if (d == null)
            return;
        // the head of the snake
        double headR = data.occupiedCells.get(0).row;
        double headC = data.occupiedCells.get(0).col;
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
        //grid[data.occupiedCells.getLast().row][data.occupiedCells.getLast().col].setOccupied(false);
        // update the cells of the body
        for (int i = data.occupiedCells.size() - 1; i > 0; i--) {
            // occupiedCells.set(i, occupiedCells.get(i - 1));
            data.occupiedCells.get(i).row = data.occupiedCells.get(i - 1).row;
            data.occupiedCells.get(i).col = data.occupiedCells.get(i - 1).col;
        }
        // set to occupied the new cell (head of the snake)
        //grid[headR][headC].setOccupied(true);
        data.occupiedCells.get(0).row = headR;
        data.occupiedCells.get(0).col = headC;
    }

    // checks the collision between the head of the snake and the food
    // public boolean checkCollisionWithFood(DataPlayer data ,coordinate food) {
    //     if(data.player.getSnake().getSnackX()>=food.getX() && data.player.getSnake().getSnackX()>=food.getX() ){
    //         return true;
    //     }
    //     return (data.occupiedCells.get(0).row == occupiedByFoodCell.row) &&
    //             (data.occupiedCells.get(0).col == occupiedByFoodCell.col);
    // }
    public boolean checkCollisionWithFood(DataPlayer data ) {
        double snakeLeft = data.player.getSnake().getSnackX();
        double snakeRight = snakeLeft + 20; // Snake width
        double snakeTop = data.player.getSnake().getSnackY();
        double snakeBottom = snakeTop + 20; // Snake height
    
        double foodLeft = food.getX();
        double foodRight = foodLeft + 20; // Food width
        double foodTop = food.getY();
        double foodBottom = foodTop + 20; // Food height
    
        // Vérification de la collision
        boolean collisionX = snakeLeft < foodRight && snakeRight > foodLeft;
        boolean collisionY = snakeTop < foodBottom && snakeBottom > foodTop;
    
        return collisionX && collisionY;
    }
    

    public boolean isAutoCollision(DataPlayer data) {
        // normally we retrive the cells based of the snake
        for (int i = 0; i < data.occupiedCells.size() - 1; i++) {
            for (int j = i + 1; j < data.occupiedCells.size(); j++) {
                if (data.occupiedCells.get(i).row == data.occupiedCells.get(j).row
                        && data.occupiedCells.get(i).col == data.occupiedCells.get(j).col) {
                    System.out.println("Auto collision!");
                    return true;
                }
            }
        }
        return false;
    }

    // remove the food from the frame and extend the snake
    public void eatFood(DataPlayer data) {
        food = null;
        Snake s = data.player.getSnake();
        s.extendBody();
        data.player.setScore(1);
        // add the new occupied cell
        Rectangle r = s.getBody().get(s.getBody().size() - 1);
        System.out.println("new body part [x=" + r.getX() + " y=" + r.getY() + "]");
        Coordinate c = new Coordinate( (r.getY() / Cell.getCellWidth()),  (r.getX() / Cell.getCellWidth()));
        //grid[c.row][c.col].setOccupied(true);
        System.out.println(c);
        data.occupiedCells.add(c);
    }

    public boolean isGameEnded() {
        return endOfGame;
    }

    public LinkedList<DataPlayer> getDataPlayer() {
        return dataPlayers;
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    // get the number of rows
    public int getNrows() {
        return nRows;
    }

    // get the number of colones
    public int getNcols() {
        return nCols;
    }

    // get the food
    public Food getFood() {
        return this.food;
    }

    // get the coordinate of the food
    public Coordinate getCoordFood() {
        return occupiedByFoodCell;
    }

    public final class DataPlayer {
        public DecisionMaker player;
        public LinkedList<Coordinate> occupiedCells;

        public DataPlayer(DecisionMaker p) {
            this.player = p;
            this.occupiedCells = new LinkedList<>();
        }
    }

    public void resetScore() {
        for (DataPlayer d : dataPlayers) {
            d.player.setScore(0);
        }
    }

    public void reset() {
        // reset the grid
        //initGrid();
        // reset the players
        dataPlayers.clear();
        initPlayers();
        // reset the food
        food = null;
        occupiedByFoodCell = null;
        endOfGame = false;
    }
}
