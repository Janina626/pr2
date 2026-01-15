package de.bht.pr2.lession09.e01_fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Demonstriert das Laden einer UI aus einer FXML-Datei.
 * FXML ermöglicht die Trennung von UI-Design und Anwendungslogik.
 */
public class FXMLDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // FXML-Datei laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DemoController.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("FXML Demo - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
