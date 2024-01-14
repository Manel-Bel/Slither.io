package fr.uparis.informatique.cpoo5.ui;

import java.time.format.TextStyle;
import java.util.LinkedList;

import fr.uparis.informatique.cpoo5.game.DecisionMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public final class GameHeader {
    private Text score;
    private Text nameA, nameB;
    private int nbPlayer;
    private LinkedList<Text> attributes;
    private GridPane nav;
    private Button pause;

    public GameHeader(LinkedList<DecisionMaker> playerList) {
        nbPlayer = (playerList.size() == 2) ? 2 : 1;
        attributes = new LinkedList<>();
        this.nameA = new Text(playerList.get(0).getName());
        attributes.add(nameA);
        score = new Text();
        attributes.add(score);

        if (nbPlayer == 1) {
            score.setText("000");
        } else {
            score.setText("000:000");
            this.nameB = new Text(playerList.get(2).getName());
            attributes.add(nameB);
        }
        pause = new Button();
        pause.setText("Pause");
        pause.setStyle("-fx-font: 20 ARCADECLASSIC");
        pause.setStyle("-fx-background-color: black");
        setStyle();
        addElementToNav();

    }

    public void updateScore(int scoreA, int scoreB) {
        String a = String.format("%03d", scoreA);
        if (nbPlayer == 2) {
            String b = String.format("%03d", scoreB);
            score.setText(a + " : " + b);
        } else {
            score.setText(a);
        }
    }

    private void setStyle() {
        for (Text e : attributes) {
            e.setFill(Color.WHITE);
            e.setStyle("-fx-font: 30 ARCADECLASSIC");
        }
    }

    private void addElementToNav() {
        nav = new GridPane();
        nav.setStyle("-fx-background-color: white");
        nav.setMinHeight(70);
        nav.setMinWidth(Menu.winWidth);
        nav.setHgap(10);
        nav.add(pause, 0, 0);

        for (int i = 0; i < attributes.size(); i++) {
            nav.add(attributes.get(i), i + 1, 0);
        }
        nav.setStyle("-fx-background-color: black");
    }

    public Button getPauseBtn() {
        return pause;
    }

    public GridPane getNavBar() {
        return nav;
    }

}
