/* exemple adapté du tutoriel de JavaFX
 * https://github.com/openjfx/samples/blob/master/HelloFX/Gradle/hellofx/src/main/java/HelloFX.java
 */

package fr.uparis.informatique.cpoo5.jfxdemo;

import fr.uparis.informatique.cpoo5.ui.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        System.out.println("JavaFX " + javafxVersion + " , running on Java " + javaVersion);
        // Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " +
        // javaVersion + ".");
        // Scene scene = new Scene(new StackPane(l), 640, 480);
        // stage.setScene(scene);
        stage.setTitle("Slither.io");
        stage.show();
        new Menu(stage, 1);
    }

    public static void main(String[] args) {
        launch();
    }

}