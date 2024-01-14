package fr.uparis.informatique.cpoo5.ui;


import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Pause {
     private Scene scene;
    private VBox root;
    private Label title;
    public static final int winWidth = 1000;
    public static final int winHeight = 600;

    // the menu of the game
    public Pause(Stage stage,Scene menus, double scale ,boolean ia,boolean solo ,AnimationTimer timer) {
     
        title = new Label("Pause");
        
        root = new VBox(10);
        root.setMinWidth(winWidth * scale);
        root.setMinHeight(winHeight * scale);
        root.setAlignment(Pos.TOP_CENTER);

       

        Button menu = new Button("Menu");
         Button reprendre = new Button("Reprendre");
        Button Replay = new Button("Replay");


        

        
        menu.setOnAction(e -> stage.setScene(menus));
        reprendre.setOnAction(e-> timer.start());

        Replay.setOnAction(e -> new GameView(stage, scale, solo, ia,menus));


        root.getChildren().addAll(title, menu, reprendre,Replay);

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}

    




