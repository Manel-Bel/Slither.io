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
        this.solo = solo;
        this.ia = ia;
        // init the grid
        nCols = (Menu.winWidth) / Cell.getCellWidth();
        nRows = (Menu.winHeight) / Cell.getCellWidth();
        initGrid();

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
            int[] tab = chooseFreeCell();
            grid[tab[0]][tab[1]].setOccupied(true);
            DecisionMaker p;
            if (ia && i == 1)
                p = new IA(tab[2], tab[3]);
            else
                p = new Player("Player" + i, tab[2], tab[3]);
            DataPlayer c = new DataPlayer(p);
            c.occupiedCells.add(new Coordinate(tab[0], tab[1]));
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

    // generate foor at a random position
    public Circle generateFood() {
        int[] tab = chooseFreeCell();
        tab[2] += (Cell.getCellWidth() / 2);
        tab[3] += (Cell.getCellWidth() / 2);
        System.out.println("food x=" + tab[2] + ",y=" + tab[3]);
        food = new Food((int) tab[2], (int) tab[3]);
        grid[tab[0]][tab[1]].setOccupied(true);
        occupiedByFoodCell = new Coordinate(tab[0], tab[1]);
        return food.getFood();
    }

    public void updateCell(DataPlayer data) {
        Direction d = data.player.getSnake().getDirection();
        if (d == null)
            return;
        // the head of the snake
        int headR = data.occupiedCells.get(0).row;
        int headC = data.occupiedCells.get(0).col;
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
        grid[data.occupiedCells.getLast().row][data.occupiedCells.getLast().col].setOccupied(false);
        // update the cells of the body
        for (int i = data.occupiedCells.size() - 1; i > 0; i--) {
            // occupiedCells.set(i, occupiedCells.get(i - 1));
            data.occupiedCells.get(i).row = data.occupiedCells.get(i - 1).row;
            data.occupiedCells.get(i).col = data.occupiedCells.get(i - 1).col;
        }
        // set to occupied the new cell (head of the snake)
        grid[headR][headC].setOccupied(true);
        data.occupiedCells.get(0).row = headR;
        data.occupiedCells.get(0).col = headC;
    }

    // checks the collision between the head of the snake and the food
    public boolean checkCollisionWithFood(DataPlayer data) {
        return (data.occupiedCells.get(0).row == occupiedByFoodCell.row) &&
                (data.occupiedCells.get(0).col == occupiedByFoodCell.col);
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
        Coordinate c = new Coordinate((int) (r.getY() / Cell.getCellWidth()), (int) (r.getX() / Cell.getCellWidth()));
        grid[c.row][c.col].setOccupied(true);
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
}
