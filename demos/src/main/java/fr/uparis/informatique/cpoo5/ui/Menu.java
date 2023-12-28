package fr.uparis.informatique.cpoo5.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import fr.uparis.informatique.cpoo5.game.Game;
import javafx.geometry.Pos;

public class Menu {
    private Stage stage;
    private Scene scene;
    private VBox root;
    private Label title;
    public static final int winWidth = 1000;
    public static final int winHeight = 600;

    // the menu of the game
    public Menu(Stage stage, double scale) {
        this.stage = stage;
        title = new Label("Menu");
        title.setId("MenuTitle");
        root = new VBox(10);
        root.setMinWidth(winWidth * scale);
        root.setMinHeight(winHeight * scale);
        root.setAlignment(Pos.TOP_CENTER);

        // btn
        Button soloGame = new Button("Solo Mode");
        Button online = new Button("Multiplayer");
        Button exitBtn = new Button("Exit");

        soloGame.setOnAction(e -> new Game(stage, scale));

        online.setOnAction(e -> Game.launchNetworked());

        exitBtn.setOnAction(e -> System.exit(0));

        root.getChildren().addAll(title, soloGame, online, exitBtn);

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        this.stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}
