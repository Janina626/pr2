package de.bht.pr2.lession08.e05b_simple_calculator_loesung;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Primitiver Calculator für SU 1 - LÖSUNG.
 * Enthält alle Erweiterungen aus den Mini-Aufgaben:
 * - Mini-Aufgabe 1: Clear-Button
 * - Mini-Aufgabe 2: Vollständige Fehlerbehandlung
 *
 * Die Start-Version (ohne diese Features) findet sich in: su1_e05a_simple_calculator
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

        // Buttons (Mini-Aufgabe 1: Clear-Button hinzugefügt)
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button calculateButton = new Button("Berechnen");
        calculateButton.setOnAction(e -> calculate());

        // LÖSUNG Mini-Aufgabe 1: Clear-Button
        Button clearButton = new Button("Löschen");
        clearButton.setOnAction(e -> clear());

        buttonBox.getChildren().addAll(calculateButton, clearButton);

        // Ergebnis
        resultLabel = new Label("Ergebnis: ");

        root.getChildren().addAll(titleLabel, inputGrid, buttonBox, resultLabel);

        Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("Einfacher Calculator - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Führt die Berechnung durch.
     * LÖSUNG Mini-Aufgabe 2: Vollständige Fehlerbehandlung
     */
    private void calculate() {
        try {
            // Eingaben auslesen
            String input1 = operand1Field.getText().trim();
            String input2 = operand2Field.getText().trim();

            // LÖSUNG: Prüfen ob Eingaben vorhanden
            if (input1.isEmpty() || input2.isEmpty()) {
                showError("Bitte beide Zahlen eingeben!");
                return;
            }

            // In Zahlen umwandeln
            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);
            String operator = operatorCombo.getValue();

            // Berechnung durchführen
            double result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> {
                    // LÖSUNG: Division durch 0 abfangen
                    if (num2 == 0) {
                        throw new ArithmeticException("Division durch 0!");
                    }
                    yield num1 / num2;
                }
                default -> 0;
            };

            // Ergebnis formatieren (ganze Zahlen ohne Dezimalstellen)
            String resultText;
            if (result == Math.floor(result) && !Double.isInfinite(result)) {
                resultText = String.format("%.0f %s %.0f = %.0f", num1, operator, num2, result);
            } else {
                resultText = String.format("%.2f %s %.2f = %.2f", num1, operator, num2, result);
            }

            resultLabel.setText("Ergebnis: " + resultText);

        } catch (NumberFormatException e) {
            showError("Ungültige Zahl eingegeben!");
        } catch (ArithmeticException e) {
            // LÖSUNG: Arithmetische Fehler abfangen
            showError(e.getMessage());
        }
    }

    /**
     * Zeigt eine Fehlermeldung an.
     */
    private void showError(String message) {
        resultLabel.setText("Fehler: " + message);
    }

    /**
     * LÖSUNG Mini-Aufgabe 1: Löscht alle Eingaben und das Ergebnis.
     */
    private void clear() {
        operand1Field.clear();
        operand2Field.clear();
        operatorCombo.setValue("+");
        resultLabel.setText("Ergebnis: ");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
