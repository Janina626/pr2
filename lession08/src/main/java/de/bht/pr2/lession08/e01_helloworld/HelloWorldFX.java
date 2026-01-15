package de.bht.pr2.lession08.e01_helloworld;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Hello World Beispiel für JavaFX.
 * Demonstriert die grundlegende Struktur einer JavaFX-Anwendung.
 */
public class HelloWorldFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // UI aufbauen
        Label label = new Label("Hallo, JavaFX!");
        VBox root = new VBox(label);

        // Scene und Stage verbinden
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
