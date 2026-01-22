package de.bht.pr2.lab6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Calculator-Anwendung mit FXML und CSS.
 * Demonstriert eine monolithische JavaFX-Anwendung.
 *
 * UEBUNG 6: Diese Anwendung soll zu MVP refaktoriert werden!
 */
public class CalculatorApplication extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // FXML laden - Controller wird automatisch erstellt
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculatorController.fxml"));
        final Parent root = loader.load();

        // Scene erstellen und CSS hinzufuegen
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());

        // Stage konfigurieren
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Fenster verschiebbar machen
        enableDrag(root, primaryStage);
    }

    private void enableDrag(Node node, Stage stage) {
        node.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        node.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
