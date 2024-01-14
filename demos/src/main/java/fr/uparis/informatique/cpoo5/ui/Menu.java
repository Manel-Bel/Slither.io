package fr.uparis.informatique.cpoo5.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        title.setId("Title");
        root = new VBox(10);
        root.setMinWidth(winWidth * scale);
        root.setMinHeight(winHeight * scale);
        root.setAlignment(Pos.TOP_CENTER);

        // btn
        Button soloGame = new Button("Solo Mode");

        Button twoPlayer = new Button("2 joueurs");
        Button online = new Button("Multiplayer");
        Button exitBtn = new Button("Exit");

        soloGame.setOnAction(e -> new GameView(stage, scale, true, false ,this.scene));
        twoPlayer.setOnAction(e -> new TwoPlayer(stage, scale,this.scene));

        online.setOnAction(e -> GameView.launchNetworked());

        exitBtn.setOnAction(e -> System.exit(0));

        root.getChildren().addAll(title, soloGame, twoPlayer, online, exitBtn);

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        this.stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}
