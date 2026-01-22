package de.bht.pr2.lession08.e05a_simple_calculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Primitiver Calculator für SU 8 - START-VERSION.
 * Demonstriert grundlegendes Event-Handling ohne MVP-Pattern oder FXML.
 *
 * HINWEIS: Dieser Calculator ist absichtlich unvollständig!
 * In den Mini-Aufgaben sollen folgende Erweiterungen vorgenommen werden:
 * - Mini-Aufgabe 1: Clear-Button hinzufügen
 * - Mini-Aufgabe 2: Fehlerbehandlung verbessern
 *
 * Die fertige Lösung findet sich in: e05b_simple_calculator_loesung
 */
public class SimpleCalculator extends Application {

    private TextField operand1Field;
    private TextField operand2Field;
    private ComboBox<String> operatorCombo;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Titel
        Label titleLabel = new Label("Einfacher Taschenrechner");

        // Eingabebereich mit GridPane
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.CENTER);

        // Erste Zahl
        Label operand1Label = new Label("Zahl 1:");
        operand1Field = new TextField();
        operand1Field.setPromptText("Erste Zahl eingeben");
        operand1Field.setPrefWidth(150);
        inputGrid.add(operand1Label, 0, 0);
        inputGrid.add(operand1Field, 1, 0);

        // Operator
        Label operatorLabel = new Label("Operator:");
        operatorCombo = new ComboBox<>(
            FXCollections.observableArrayList("+", "-", "*", "/")
        );
        operatorCombo.setValue("+");
        operatorCombo.setPrefWidth(150);
        inputGrid.add(operatorLabel, 0, 1);
        inputGrid.add(operatorCombo, 1, 1);

        // Zweite Zahl
        Label operand2Label = new Label("Zahl 2:");
        operand2Field = new TextField();
        operand2Field.setPromptText("Zweite Zahl eingeben");
        operand2Field.setPrefWidth(150);
        inputGrid.add(operand2Label, 0, 2);
        inputGrid.add(operand2Field, 1, 2);

        // Berechnen-Button
        Button calculateButton = new Button("Berechnen");
        calculateButton.setOnAction(e -> calculate());

        // Löschen-Button
        Button deleteButton = new Button("Löschen");
        deleteButton.setOnAction(e -> delete());

        // Ergebnis
        resultLabel = new Label("Ergebnis: ");
        // resultLabel.setStyle("-fx-font-size: 20");

//        final Hbox hBox = new HBox;
//        hBox.getChildren().addAll(calculateButton, deleteButton);
//        root.getChildren().addAll(titleLabel, inputGrid, hBox, resultLabel);
//      entweder das auskommentierte oder das unten
//      unten ist alles untereinander, oben sind die Buttons nebeneinander
        root.getChildren().addAll(titleLabel, inputGrid, calculateButton, deleteButton, resultLabel);

        Scene scene = new Scene(root, 350, 280);
        primaryStage.setTitle("Einfacher Calculator - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Führt die Berechnung durch.
     * HINWEIS: Fehlerbehandlung ist minimal - wird in Mini-Aufgabe 2 verbessert.
     */
    private void calculate() {
        // Eingaben auslesen
        String input1 = operand1Field.getText();
        String input2 = operand2Field.getText();

        // In Zahlen umwandeln (ohne Fehlerbehandlung!)
        double num1 = Double.parseDouble(input1);
        double num2 = Double.parseDouble(input2);
        String operator = operatorCombo.getValue();

        // Berechnung durchführen
        double result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;  // Division durch 0 nicht abgefangen!
            default -> 0;
        };

        // Ergebnis anzeigen
        resultLabel.setText("Ergebnis: " + num1 + " " + operator + " " + num2 + " = " + result);
    }

    private void delete() {
        operand1Field.setText("");
        operand2Field.setText("");
        resultLabel.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
