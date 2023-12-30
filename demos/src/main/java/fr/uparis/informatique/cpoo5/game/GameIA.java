package fr.uparis.informatique.cpoo5.game;

import java.util.ArrayList;

import fr.uparis.informatique.cpoo5.entities.Cell;
import fr.uparis.informatique.cpoo5.entities.Food;
import fr.uparis.informatique.cpoo5.game.Game.Animation;
import fr.uparis.informatique.cpoo5.ui.Menu;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameIA {
    private Stage gameStage;
    private Scene gamScene;
    private Pane gameRoot;
    private Person joueur;
    private Person IA;
    private Animation timer;
    private Cell[][] grid;
    private int nRows, nCols;
    private double scale;
    private Food food = null;

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


}
