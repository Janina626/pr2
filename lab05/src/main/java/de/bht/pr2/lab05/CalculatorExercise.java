package de.bht.pr2.lab05;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Übung 1: Calculator mit Historie
 *
 * Aufgaben für Studierende:
 * 1. Erweitern Sie die Operatoren um Modulo (%), Quadratwurzel (√) etc.
 * 2. Der "Löschen" Button soll alle Eingabefelder und das Ergebnis zurücksetzen.
 * 2. Implementieren Sie die Berechnungshistorie:
 *    3.1 Fügen Sie ein Label "Berechnungs-Historie:" hinzu
 *    3.2 Fügen Sie eine ListView hinzu, um die Historie anzuzeigen
 *    3.3 Fügen Sie einen Button "Historie löschen" hinzu, um die Historie zu leeren
 *    3.4 Informieren Sie sich über ObservableList und ListView in JavaFX
 * 3. Verbessern Sie die Fehlerbehandlung
 * 4. Überraschen Sie Ihren Dozenten mit weiteren Features!
 *
 */
public class CalculatorExercise extends Application {

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
        Label titleLabel = new Label("Calculator mit Historie");

        // Eingabebereich
        GridPane inputGrid = createInputArea();

        // Buttons
        HBox buttonBox = createButtons();

        // Ergebnis
        resultLabel = new Label("Ergebnis: ");

        root.getChildren().addAll(
            titleLabel,
            inputGrid,
            buttonBox,
            resultLabel
        );

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Übung 1: Calculator mit Historie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createInputArea() {
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.CENTER);

        // Erste Zahl
        Label operand1Label = new Label("Zahl 1:");
        operand1Field = new TextField();
        operand1Field.setPromptText("Erste Zahl");
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
        operand2Field.setPromptText("Zweite Zahl");
        operand2Field.setPrefWidth(150);
        inputGrid.add(operand2Label, 0, 2);
        inputGrid.add(operand2Field, 1, 2);

        return inputGrid;
    }

    private HBox createButtons() {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button calculateButton = new Button("Berechnen");
        calculateButton.setOnAction(e -> calculate());

        Button clearButton = new Button("Löschen");

        buttonBox.getChildren().addAll(calculateButton, clearButton);
        return buttonBox;
    }

    /**
     * Führt die Berechnung durch.
     */
    private void calculate() {
        try {
            String input1 = operand1Field.getText().trim();
            String input2 = operand2Field.getText().trim();

            if (input1.isEmpty() || input2.isEmpty()) {
                showError("Bitte beide Zahlen eingeben!");
                return;
            }

            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);
            String operator = operatorCombo.getValue();

            double result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> {
                    if (num2 == 0) {
                        throw new ArithmeticException("Division durch 0!");
                    }
                    yield num1 / num2;
                }
                default -> 0;
            };

            // Ergebnis anzeigen
            String calculation = formatResult(num1, operator, num2, result);
            resultLabel.setText("Ergebnis: " + calculation);


        } catch (NumberFormatException e) {
            showError("Ungültige Zahl eingegeben!");
        } catch (ArithmeticException e) {
            showError(e.getMessage());
        }
    }

    /**
     * Formatiert das Ergebnis für die Anzeige.
     */
    private String formatResult(double num1, String operator, double num2, double result) {
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.format("%.0f %s %.0f = %.0f", num1, operator, num2, result);
        }
        return String.format("%.2f %s %.2f = %.2f", num1, operator, num2, result);
    }

    /**
     * Zeigt eine Fehlermeldung an.
     */
    private void showError(String message) {
        resultLabel.setText("Fehler: " + message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
