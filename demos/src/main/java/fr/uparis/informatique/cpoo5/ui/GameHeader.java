package fr.uparis.informatique.cpoo5.ui;

import fr.uparis.informatique.cpoo5.game.DecisionMaker;
import javafx.scene.text.Text;

public final class GameHeader {
    private Text score;
    private Text nameA, nameB;
    private int nbPlayer;

    public GameHeader(DecisionMaker player1, DecisionMaker player2) {
        nbPlayer = (player2 == null) ? 1 : 2;

        if (nbPlayer == 1) {
            score = new Text("000");
        } else {
            score = new Text("000:000");
        }

        // this.nameA = new

    }

}
