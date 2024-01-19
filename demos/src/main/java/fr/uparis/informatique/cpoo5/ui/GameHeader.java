package fr.uparis.informatique.cpoo5.ui;

import java.util.LinkedList;
import java.util.StringJoiner;

import fr.uparis.informatique.cpoo5.game.DecisionMaker;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * GameHeader : The header of the game includes details like the names
 * of the players and their scores.
 * 
 * @author : Belguenbour Manel
 */
public class GameHeader {
    private Text score;
    private Text nameA, nameB;
    private int nbPlayer;
    private LinkedList<Text> attributes;
    private GridPane nav;
    private Button pause;
    private LinkedList<String> scoresList;

    /**
     * Construct a new instance of GameHeader
     * 
     * @param playerList The list of all the Players.
     * 
     */
    public GameHeader(LinkedList<DecisionMaker> playerList) {
        nbPlayer = (playerList.size() == 2) ? 2 : 1;
        attributes = new LinkedList<>();
        scoresList = new LinkedList<>();
        this.nameA = new Text(playerList.get(0).getName());
        attributes.add(nameA);
        initScoresString();
        score = new Text();
        buildScoreText();
        attributes.add(score);

        if (nbPlayer == 2) {
            this.nameB = new Text(playerList.get(1).getName());
            attributes.add(nameB);
        }
        pause = new Button();
        pause.setText("Pause");
        pause.setStyle("-fx-font: 30 ARCADECLASSIC");
        pause.setStyle("-fx-background-color: white");
        pause.setCursor(Cursor.HAND);
        setStyle();
        addElementToNav();
    }

    /**
     * initialise the score string with 000 * the number of players
     * 
     */
    public void initScoresString() {
        for (int i = 0; i < nbPlayer; i++) {
            scoresList.add("000");
        }
    }

    /**
     * build a string from a table , separated with ':'
     * 
     */
    public void buildScoreText() {
        StringJoiner j = new StringJoiner(":");
        scoresList.forEach(j::add);
        score.setText(j.toString());
    }

    /**
     * update the score text
     * 
     * @param playernumber indicate the number of the player in our list
     * @param score        the new score of the player indicated previously
     * 
     */
    public void updateScore(int playernumber, int score) {
        String a = String.format("%03d", score);
        scoresList.set(playernumber, a);
        buildScoreText();
    }

    private void setStyle() {
        for (Text e : attributes) {
            e.setFill(Color.WHITE);
            e.setStyle("-fx-font: 30 ARCADECLASSIC");
        }
    }

    private void addElementToNav() {
        nav = new GridPane();
        nav.setMinHeight(70);
        nav.setMinWidth(Menu.winWidth);
        if (nbPlayer == 1) {
            nav.setHgap(150);
        } else
            nav.setHgap(100);
        nav.setVgap(20);
        nav.add(pause, 0, 0);

        for (int i = 0; i < attributes.size(); i++) {
            nav.add(attributes.get(i), i + 2, 0);
        }
        nav.setStyle("-fx-background-color: black");
    }

    /**
     * getter
     * 
     * @return the pause button
     */
    public Button getPauseBtn() {
        return pause;
    }

    /**
     * getter of the pane that represent the header of the game
     * 
     * @return nav
     */
    public GridPane getNavBar() {
        return nav;
    }

}
