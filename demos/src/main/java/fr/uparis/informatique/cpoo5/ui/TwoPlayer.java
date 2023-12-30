package fr.uparis.informatique.cpoo5.ui;

import fr.uparis.informatique.cpoo5.game.Game;
import fr.uparis.informatique.cpoo5.game.GameIA;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TwoPlayer {
     private Stage stage;
    private Scene scene;
    private VBox root;
    private Label title;
    public static final int winWidth = 1000;
    public static final int winHeight = 600;

     public TwoPlayer(Stage stage, double scale) {
        this.stage = stage;
        title = new Label("Choix");
        title.setId("choix");
        root = new VBox(10);
        root.setMinWidth(winWidth * scale);
        root.setMinHeight(winHeight * scale);
        root.setAlignment(Pos.TOP_CENTER);

        // btn
        Button ia = new Button("Joueur VS IA");
        
        Button twoPlayer = new Button("2 joueurs");
        
        Button exitBtn = new Button("Retour");

        ia.setOnAction(e -> new GameIA(stage, scale));
        twoPlayer.setOnAction(e->new Game(stage, scale));

        exitBtn.setOnAction(e -> new Menu(stage, scale));

        root.getChildren().addAll(title, ia, twoPlayer, exitBtn);

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        this.stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }

   

}
