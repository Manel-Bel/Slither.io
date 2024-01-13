package fr.uparis.informatique.cpoo5.ui;

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
        title = new Label("Choice");
        title.setId("Title");
        root = new VBox(10);
        root.setMinWidth(winWidth * scale);
        root.setMinHeight(winHeight * scale);
        root.setAlignment(Pos.TOP_CENTER);

        // btn
        Button ia = new Button("Palyer vs IA");
        Button twoPlayer = new Button("2 Players");
        Button exitBtn = new Button("Return");

        // ia.setOnAction(e -> new GameView(stage, scale, false, true));
        // twoPlayer.setOnAction(e -> new GameView(stage, scale, false, false)); // nb player
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
