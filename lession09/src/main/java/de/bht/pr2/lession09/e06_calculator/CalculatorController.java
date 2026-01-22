package de.bht.pr2.lession09.e06_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Monolithischer Controller fuer den Ganzzahlen-Calculator.
 * Enthaelt sowohl die UI-Logik als auch die Berechnungslogik.
 *
 * UEBUNG 2: Diese Klasse soll erweitert und zu MVP refaktoriert werden!
 *
 * Aufgaben:
 * 1. Weitere Operatoren implementieren (-, *, /)
 * 2. Division durch Null behandeln
 * 3. Zu MVP-Architektur refaktorieren:
 *    - Model: Berechnungslogik und Zustand
 *    - View: UI-Interaktionen (dieser Controller wird duenner)
 *    - Presenter: Koordination zwischen Model und View
 *
 * Clear und Delete sind bereits implementiert.
 */
public class CalculatorController {

    private static final String INITIAL_VALUE = "0";

    @FXML
    private Label display;

    // Zustandsvariablen fuer die Berechnung
    private String currentInput = INITIAL_VALUE;
    private long storedNumber = 0;
    private String pendingOperator = null;
    private boolean startNewInput = false;

    @FXML
    public void initialize() {
        updateDisplay();
    }

    // === Zahlen-Buttons ===

    @FXML
    void onNumberClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        String digit = button.getText();

        if (startNewInput || currentInput.equals(INITIAL_VALUE)) {
            currentInput = digit;
            startNewInput = false;
        } else {
            currentInput = currentInput + digit;
        }
        updateDisplay();
    }

    // === Operator-Buttons ===

    @FXML
    void onAddButtonClicked(ActionEvent event) {
        // Speichere aktuelle Zahl und setze Operator
        storedNumber = Long.parseLong(currentInput);
        pendingOperator = "+";
        startNewInput = true;
    }

    @FXML
    void onSubtractButton(ActionEvent event) {
        // TODO: Implementieren Sie die Subtraktion in Uebung 2
        // Hinweis: Aehnlich wie onAddButtonClicked, aber mit "-"
    }

    @FXML
    void onMultiplyButtonClicked(ActionEvent event) {
        // TODO: Implementieren Sie die Multiplikation in Uebung 2
    }

    @FXML
    void onDivideButtonClicked(ActionEvent event) {
        // TODO: Implementieren Sie die Division in Uebung 2
        // Hinweis: Vergessen Sie nicht die Division durch Null zu behandeln!
    }

    @FXML
    void onEqualButtonClicked(ActionEvent event) {
        if (pendingOperator == null) {
            return;
        }

        long secondNumber = Long.parseLong(currentInput);
        long result = 0;

        // Nur Addition ist implementiert
        if (pendingOperator.equals("+")) {
            result = storedNumber + secondNumber;
        }
        // TODO: Weitere Operatoren in Uebung 2 hinzufuegen

        currentInput = String.valueOf(result);
        pendingOperator = null;
        startNewInput = true;
        updateDisplay();
    }

    // === Funktions-Buttons ===

    @FXML
    void onCleanButtonClicked(ActionEvent event) {
        // Setzt alle Zustandsvariablen zurueck
        currentInput = INITIAL_VALUE;
        storedNumber = 0;
        pendingOperator = null;
        startNewInput = false;
        updateDisplay();
    }

    @FXML
    void onDeleteButtonClicked(ActionEvent event) {
        // Entfernt die letzte Ziffer von currentInput
        if (currentInput.length() > 1) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        } else {
            currentInput = INITIAL_VALUE;
        }
        updateDisplay();
    }

    @FXML
    void onPercentButtonClicked(ActionEvent event) {
        // Optional: Prozent-Funktion
        // Nicht Teil der Pflichtaufgabe
    }

    @FXML
    void onNegateButtonClicked(ActionEvent event) {
        // Optional: Vorzeichen-Wechsel
        // Nicht Teil der Pflichtaufgabe
        if (!currentInput.equals(INITIAL_VALUE)) {
            if (currentInput.startsWith("-")) {
                currentInput = currentInput.substring(1);
            } else {
                currentInput = "-" + currentInput;
            }
            updateDisplay();
        }
    }

    @FXML
    void onCommaButtonClicked(ActionEvent event) {
        // Fuer Ganzzahlen: Komma wird ignoriert
        // Optional: Fuer Dezimalzahlen erweitern
    }

    // === Hilfsmethoden ===

    private void updateDisplay() {
        display.setText(currentInput);
    }
}
