package fr.uparis.informatique.cpoo5.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import fr.uparis.informatique.cpoo5.game.GameApp;
import javafx.geometry.Pos;

public class Menu {
    private Stage stage;
    private Scene scene;
    private VBox root;
    private Label title;

    // the menu of the game
    public Menu(Stage stage, double scale) {
        this.stage = stage;
        title = new Label("Menu");
        title.setId("MenuTitle");
        root = new VBox(10);
        root.setMinWidth(1000 * scale + 2 * 50);
        root.setMinHeight(600 * scale);
        root.setAlignment(Pos.TOP_CENTER);

        // btn
        Button soloGame = new Button("Solo Mode");
        Button multiplayer = new Button("Multiplayer");
        Button exitBtn = new Button("Exit");

        soloGame.setOnAction(e -> GameApp.launchSolitaire());

        multiplayer.setOnAction(e -> GameApp.launchNetworked());

        exitBtn.setOnAction(e -> System.exit(0));

        root.getChildren().addAll(title, soloGame, multiplayer, exitBtn);

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}
