package fr.uparis.informatique.cpoo5.game;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import fr.uparis.informatique.cpoo5.entities.*;
import fr.uparis.informatique.cpoo5.ui.Menu;
import fr.uparis.informatique.cpoo5.utils.*;

public class Game {
    private ArrayList<Player> players;
    private Cell[][] grid;
    private int nRows, nCols;
    private Food food = null;
    private LinkedList<Coordinate> occupiedCells;
    private Coordinate occupiedByFoodCell;
    // private Pane gameRoot;
    private boolean endOfGame;

    public Game() {
        // this.gameRoot = gameRoot;
        // init the grid
        nCols = (Menu.winWidth) / Cell.getCellWidth();
        nRows = (Menu.winHeight) / Cell.getCellWidth();
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
        // gameRoot.getChildren().addAll(p.getSnake().getBody());
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

    // return true if the game ended
    // public boolean update() {
    // if (food == null) {
    // generateFood();
    // }
    // players.get(0).getSnake().move(Menu.winWidth, Menu.winWidth);
    // updateCell();
    // // System.out.println(players.get(0).getSnake());
    // // check auto collision or death
    // if (isAutoCollision(0)) {
    // endOfGame = true;
    // return endOfGame;
    // }
    // if (checkCollisionWithFood()) {
    // eatFood();
    // }
    // return false;
    // }

    // generate foor at a random position
    public Circle generateFood() {
        Random rand = new Random();
        int r = 0, c = 0;
        double x, y;
        do {
            r = rand.nextInt(nRows);
            c = rand.nextInt(nCols);
            x = grid[r][c].getX() + (Cell.getCellWidth() / 2);
            y = grid[r][c].getY() + (Cell.getCellWidth() / 2);

        } while (grid[r][c].isOccupied());
        System.out.println("food x=" + x + ",y=" + y);
        food = new Food((int) x, (int) y);
        grid[r][c].setOccupied(true);
        System.out.println(grid[r][c]);
        occupiedByFoodCell = new Coordinate(r, c);
        // gameRoot.getChildren().add(food.getFood());
        return food.getFood();
    }

    public void updateCell() {
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
    public boolean checkCollisionWithFood() {
        return (occupiedCells.get(0).row == occupiedByFoodCell.row) &&
                (occupiedCells.get(0).col == occupiedByFoodCell.col);
    }

    public boolean isAutoCollision(int snakeIndex) {
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
    public void eatFood() {
        // gameRoot.getChildren().remove(food.getFood());
        food = null;
        Snake s = players.get(0).getSnake();
        s.extendBody();
        // add the new part of the body to the game
        // gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));
        // add the new occupied cell
        Rectangle r = s.getBody().get(s.getBody().size() - 1);
        System.out.println("new body part [x=" + r.getX() + " y=" + r.getY() + "]");
        Coordinate c = new Coordinate((int) (r.getY() / Cell.getCellWidth()), (int) (r.getX() / Cell.getCellWidth()));
        grid[c.row][c.col].setOccupied(true);
        System.out.println(c);
        occupiedCells.add(c);
    }

    public boolean isGameEnded() {
        return endOfGame;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
