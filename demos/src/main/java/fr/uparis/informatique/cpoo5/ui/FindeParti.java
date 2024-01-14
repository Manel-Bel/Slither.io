package fr.uparis.informatique.cpoo5.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FindeParti  {
    
    private Scene scene;
    private VBox root;
    private Label title;
    public static final int winWidth = 1000;
    public static final int winHeight = 600;

    // the menu of the game
    public FindeParti(Stage stage,Scene menus, double scale ,boolean ia,boolean solo , int scoreMAX , String nom) {
     
        title = new Label("FIN DE PARTI");
        
        root = new VBox(10);
        root.setMinWidth(winWidth * scale);
        root.setMinHeight(winHeight * scale);
        root.setAlignment(Pos.TOP_CENTER);

        // btn
        Text Score = new Text(" SCORE MAX : "+scoreMAX);
        Text Nom = new Text(nom + " a gagné ");

        Button menu = new Button("Menu");
        Button Replay = new Button("Replay");
        

        
        menu.setOnAction(e -> stage.setScene(menus));

        Replay.setOnAction(e -> new GameView(stage, scale, solo, ia,menus));


        root.getChildren().addAll(title, Score, Nom, menu, Replay);

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/All.css").toExternalForm());
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}

    

