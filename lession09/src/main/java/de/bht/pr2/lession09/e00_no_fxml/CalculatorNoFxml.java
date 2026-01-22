package de.bht.pr2.lession09.e00_no_fxml;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * BEISPIEL: Monolithischer Calculator OHNE FXML.
 *
 * Dieser Code zeigt die PROBLEME eines rein programmatischen Ansatzes:
 *
 * PROBLEM 1: UI und Logik sind vermischt
 * - Die Berechnungslogik ist direkt im Event-Handler
 * - Schwer zu testen, schwer zu warten
 *
 * PROBLEM 2: Styling ist inline
 * - Jedes Element hat eigene setStyle() Aufrufe
 * - Keine Wiederverwendbarkeit
 * - Aenderungen erfordern Neukompilierung
 *
 * PROBLEM 3: Layout ist programmatisch
 * - Schwer zu visualisieren ohne Starten
 * - Designer koennen nicht mitarbeiten
 * - Aenderungen erfordern Neukompilierung
 *
 * PROBLEM 4: Keine Trennung der Verantwortlichkeiten
 * - Alles in einer Klasse
 * - Schlechte Skalierbarkeit
 *
 * In SU 2 lernen wir die Loesung:
 * - FXML fuer deklarative UI-Definition
 * - CSS fuer zentrales Styling
 * - MVP fuer klare Architektur
 */
public class CalculatorNoFxml extends Application {

    // UI-Elemente als Instanzvariablen (vermischt mit Logik-Zustand)
    private TextField number1Field;
    private TextField number2Field;
    private Label resultLabel;

    // Logik-Zustand (sollte in eigenem Model sein)
    private double result = 0;

    @Override
    public void start(Stage primaryStage) {

        // === PROBLEM 3: Layout programmatisch erstellen ===
        // Schwer zu lesen, schwer zu aendern

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Inline-Styling ueberall (PROBLEM 2)
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Titel mit Inline-Styling
        Label titleLabel = new Label("Einfacher Rechner");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");

        // Formular-Grid
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setAlignment(Pos.CENTER);

        // Erstes Eingabefeld mit Inline-Styling
        Label label1 = new Label("Zahl 1:");
        label1.setStyle("-fx-font-size: 14px;");
        number1Field = new TextField();
        number1Field.setPromptText("Erste Zahl eingeben");
        number1Field.setStyle("-fx-padding: 8px; -fx-font-size: 14px;");

        // Zweites Eingabefeld mit Inline-Styling
        Label label2 = new Label("Zahl 2:");
        label2.setStyle("-fx-font-size: 14px;");
        number2Field = new TextField();
        number2Field.setPromptText("Zweite Zahl eingeben");
        number2Field.setStyle("-fx-padding: 8px; -fx-font-size: 14px;");

        formGrid.add(label1, 0, 0);
        formGrid.add(number1Field, 1, 0);
        formGrid.add(label2, 0, 1);
        formGrid.add(number2Field, 1, 1);

        // Buttons mit Inline-Styling (wiederholt sich!)
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button addButton = new Button("+");
        addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                          "-fx-font-size: 16px; -fx-min-width: 50px; -fx-cursor: hand;");

        Button subtractButton = new Button("-");
        subtractButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; " +
                               "-fx-font-size: 16px; -fx-min-width: 50px; -fx-cursor: hand;");

        Button multiplyButton = new Button("*");
        multiplyButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; " +
                               "-fx-font-size: 16px; -fx-min-width: 50px; -fx-cursor: hand;");

        Button divideButton = new Button("/");
        divideButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; " +
                             "-fx-font-size: 16px; -fx-min-width: 50px; -fx-cursor: hand;");

        buttonBox.getChildren().addAll(addButton, subtractButton, multiplyButton, divideButton);

        // Ergebnis-Label
        resultLabel = new Label("Ergebnis: -");
        resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        // === PROBLEM 1: Logik direkt in Event-Handlern ===
        // Sollte in eigenem Model/Presenter sein

        addButton.setOnAction(e -> {
            try {
                double n1 = Double.parseDouble(number1Field.getText());
                double n2 = Double.parseDouble(number2Field.getText());
                result = n1 + n2;
                resultLabel.setText("Ergebnis: " + result);
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");
            } catch (NumberFormatException ex) {
                resultLabel.setText("Fehler: Ungueltige Eingabe!");
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
            }
        });

        subtractButton.setOnAction(e -> {
            try {
                double n1 = Double.parseDouble(number1Field.getText());
                double n2 = Double.parseDouble(number2Field.getText());
                result = n1 - n2;
                resultLabel.setText("Ergebnis: " + result);
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");
            } catch (NumberFormatException ex) {
                resultLabel.setText("Fehler: Ungueltige Eingabe!");
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
            }
        });

        multiplyButton.setOnAction(e -> {
            try {
                double n1 = Double.parseDouble(number1Field.getText());
                double n2 = Double.parseDouble(number2Field.getText());
                result = n1 * n2;
                resultLabel.setText("Ergebnis: " + result);
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");
            } catch (NumberFormatException ex) {
                resultLabel.setText("Fehler: Ungueltige Eingabe!");
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
            }
        });

        divideButton.setOnAction(e -> {
            try {
                double n1 = Double.parseDouble(number1Field.getText());
                double n2 = Double.parseDouble(number2Field.getText());
                if (n2 == 0) {
                    resultLabel.setText("Fehler: Division durch Null!");
                    resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
                } else {
                    result = n1 / n2;
                    resultLabel.setText("Ergebnis: " + result);
                    resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Fehler: Ungueltige Eingabe!");
                resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: red;");
            }
        });

        // Clear Button
        Button clearButton = new Button("Loeschen");
        clearButton.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; " +
                            "-fx-font-size: 14px; -fx-cursor: hand;");
        clearButton.setOnAction(e -> {
            number1Field.clear();
            number2Field.clear();
            resultLabel.setText("Ergebnis: -");
            resultLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");
        });

        // Alles zusammenfuegen
        root.getChildren().addAll(titleLabel, formGrid, buttonBox, resultLabel, clearButton);

        // Scene und Stage
        Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("Calculator (ohne FXML - Problematisch!)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
