package de.bht.pr2.lab05;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
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
    private Label errorLabel;
    private Label historyLabel;
    private ListView<String> historyView;
    private ObservableList<String> historyList;


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
        errorLabel = new Label("");
        errorLabel.setStyle("-fx-text-fill: red");
        errorLabel.setWrapText(true);
        // VBox.setMargin(errorLabel, new Insets(0, 0, 20, 0));
        historyLabel = new Label("Berechnungs-Historie: ");
        historyList= FXCollections.observableArrayList();
        historyView = new ListView<>(historyList);

        // Historie per Doppelklick wiederverwenden
        historyView.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                String entry = historyView.getSelectionModel().getSelectedItem();
                if (entry == null) return;
                String[] parts = entry.split(" ");
                if (parts.length >= 3) {
                    operand1Field.setText(parts[0]);
                    operatorCombo.setValue(parts[1]);
                    operand2Field.setText(parts[2]);
                }
            }
        });

        root.getChildren().addAll(
            titleLabel,
            inputGrid,
            buttonBox,
            resultLabel,
            errorLabel,
            historyLabel,
            historyView
        );

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Übung 1: Calculator mit Historie");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(450);
        primaryStage.show();
        root.layout();

        // Enter zum Berechnen
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                calculate();
            }
        });
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
            FXCollections.observableArrayList("+", "-", "*", "/", "%", "√", "x^y", "n!", "1/x")
        );
        operatorCombo.setValue("+");
        operatorCombo.setPrefWidth(150);
        inputGrid.add(operatorLabel, 0, 1);
        inputGrid.add(operatorCombo, 1, 1);

        // operand2 deaktivieren bei nur ein Zahl-Operand
        operatorCombo.setOnAction(e -> {
            String op = operatorCombo.getValue();
            boolean single = op.equals("n!") || op.equals("1/x");
            operand2Field.setDisable(single);
            if (single) operand2Field.clear();
        });

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
        clearButton.setOnAction(e -> clear());

        Button clearHistoryButton = new Button("Historie Löschen");
        clearHistoryButton.setOnAction(e -> clearHistory());

        buttonBox.getChildren().addAll(calculateButton, clearButton, clearHistoryButton);
        return buttonBox;
    }

    /**
     * Führt die Berechnung durch.
     */
    private void calculate() {
        try {
            String operator = operatorCombo.getValue();
            double num1 = Double.parseDouble(operand1Field.getText().trim());

            double result;
            String calculation;

            // Eine Zahl
            if (operator.equals("n!") || operator.equals("1/x")) {

                result = switch (operator) {
                    case "n!" -> {
                        if (num1 < 0 || num1 % 1 != 0)
                            throw new IllegalArgumentException("Fakultät nur für ganze Zahlen ≥ 0");
                        double f = 1;
                        for (int i = 1; i <= (int) num1; i++) f *= i;
                        yield f;
                    }
                    case "1/x" -> {
                        if (num1 == 0)
                            throw new ArithmeticException("Division durch 0");
                        yield 1 / num1;
                    }
                    default -> 0;
                };

                calculation = operator + "(" + num1 + ") = " + result;
            }

            // Zwei Zahlen
            else {
                double num2 = Double.parseDouble(operand2Field.getText().trim());

                result = switch (operator) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> {
                        if (num2 == 0)
                            throw new ArithmeticException("Division durch 0");
                        yield num1 / num2;
                    }
                    case "%" -> {
                        if (num2 == 0)
                            throw new ArithmeticException("Modulo durch 0 ist nicht definiert");
                        if (num1 % 1 != 0 || num2 % 1 != 0)// prüft, ob num1 eine Dezimalstelle hat
                            throw new IllegalArgumentException("Traditionell ist Modulo nur für ganze Zahlen definiert.");
                        if (num1 < 0 || num2 < 0)
                            throw new IllegalArgumentException("Die Modulo-Operation ist für negative Zahlen nicht universell definiert.");
                        yield num1 % num2;
                    }
                    case "x^y" -> {
                        if (num1 == 0 && num2 == 0)
                            throw new ArithmeticException("Null hoch Null nicht definiert");
                        if (num1 == 0 && num2 < 0)
                            throw new ArithmeticException("0 hoch negativ ist nicht definiert");
                        if (num1 < 0 && num2 % 1 != 0)
                            throw new IllegalArgumentException("Negative Basis mit nicht-ganzzahligem Exponenten ergibt eine komplexe Zahl");
                        yield Math.pow(num1, num2);
                    }
                    case "√" -> {
                        if (num1 == 0)
                            throw new ArithmeticException("0-te Wurzel ist nicht definiert");
                        if (num1 % 1 != 0)
                            throw new IllegalArgumentException("Der Wurzelexponent muss eine ganze Zahl sein");
                        if (num2 < 0 && ((int) num1) % 2 == 0)
                            throw new IllegalArgumentException("Gerade Wurzel aus negativer Zahl ist nicht reell");
                        yield Math.pow(num2, 1.0 / num1);
                    }
                    default -> 0;
                };

                calculation = num1 + " " + operator + " " + num2 + " = " + result;
            }

            // Ergebnis anzeigen
            resultLabel.setText("Ergebnis: " + calculation);
            errorLabel.setText("");
            historyList.add(calculation);


        } catch (NumberFormatException e) {
            showError("Ungültige Zahl eingegeben! Vielleicht Komma statt Punkt benutzt?");
        } catch (ArithmeticException e) {
            showError(e.getMessage());
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }

    }

    private void clear() {
        operand1Field.clear();
        operand2Field.clear();
        operatorCombo.setValue("+");
        resultLabel.setText("Ergebnis: ");
        errorLabel.setText("");
    }

    private void clearHistory() {
        historyList.clear();
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
        resultLabel.setText("");
        errorLabel.setText("Fehler: " + message);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
