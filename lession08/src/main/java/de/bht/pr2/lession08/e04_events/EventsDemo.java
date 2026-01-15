package de.bht.pr2.lession08.e04_events;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Demonstriert JavaFX Event-Handling.
 * Vereinfachte Version ohne Drag-Events für SU 1.
 */
public class EventsDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label statusLabel = new Label("Events werden hier angezeigt");
        statusLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // 1. Button-Klick-Event
        Label buttonLabel = new Label("1. Button-Klick-Event:");
        Button clickButton = new Button("Klick mich!");
        clickButton.setOnAction(e -> statusLabel.setText("Button wurde geklickt!"));

        // 2. Maus-Events
        Label mouseLabel = new Label("2. Maus-Events (fahre über den Kreis):");
        Circle circle = new Circle(40, Color.BLUE);
        circle.setOnMouseEntered(e -> {
            circle.setFill(Color.RED);
            statusLabel.setText("Maus ist in den Kreis eingetreten");
        });
        circle.setOnMouseExited(e -> {
            circle.setFill(Color.BLUE);
            statusLabel.setText("Maus hat den Kreis verlassen");
        });
        circle.setOnMouseClicked(e -> {
            statusLabel.setText(String.format("Kreis geklickt bei (%.0f, %.0f)", e.getX(), e.getY()));
        });

        // 3. Tastatur-Events
        Label keyLabel = new Label("3. Tastatur-Events (tippe im Feld, drücke Enter):");
        TextField keyField = new TextField();
        keyField.setPromptText("Hier tippen...");
        keyField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                statusLabel.setText("Enter gedrückt! Text: " + keyField.getText());
            } else {
                statusLabel.setText("Taste gedrückt: " + e.getCode());
            }
        });
        keyField.setOnKeyReleased(e -> {
            if (e.getCode() != KeyCode.ENTER) {
                statusLabel.setText("Taste losgelassen: " + e.getCode());
            }
        });

        root.getChildren().addAll(
            statusLabel,
            buttonLabel, clickButton,
            mouseLabel, circle,
            keyLabel, keyField
        );

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("Events Demo - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
