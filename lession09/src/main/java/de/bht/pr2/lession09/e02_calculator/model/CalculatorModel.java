package de.bht.pr2.lession09.e02_calculator.model;

import de.bht.pr2.lession09.e02_calculator.presenter.Operator;

/**
 * Model-Klasse für den Ganzzahlen-Calculator.
 * Enthält die Geschäftslogik und den Zustand des Calculators.
 *
 * ÜBUNG 2: Diese Klasse ist unvollständig!
 * Studierende sollen die fehlende Logik implementieren.
 *
 * Hinweis: Der Calculator arbeitet mit Ganzzahlen (long).
 * Das Komma-Feature ist für eine mögliche Erweiterung vorbereitet,
 * aber die Kernlogik soll zunächst nur mit ganzen Zahlen arbeiten.
 */
public class CalculatorModel {
    public static final String INITIAL_VALUE = "0";
    private long number1;
    private long number2;
    private String display;

    public CalculatorModel() {
        this.number1 = 0;
        this.number2 = 0;
        this.display = INITIAL_VALUE;
    }

    public String getCurrentValue() {
        return display;
    }

    public void appendNumber(int number) {
        if (display.equals(INITIAL_VALUE)) {
            display = Integer.toString(number);
        } else {
            display += Integer.toString(number);
        }
    }

    /**
     * Fügt ein Komma hinzu (für zukünftige Dezimalzahl-Erweiterung).
     * In der Ganzzahl-Version hat diese Methode keine Funktion.
     */
    public void appendComma() {
        // Für Ganzzahlen: Komma wird ignoriert
        // TODO (Optional): Für Dezimalzahlen implementieren
    }

    /**
     * TODO: Implementieren Sie diese Methode in Übung 2!
     * Fügt einen Operator hinzu und führt die Berechnung durch.
     */
    public void addOperator(Operator operator) {
        if (display.equals(INITIAL_VALUE)) {
            return;
        }
        // TODO: Implementieren Sie die Operator-Logik
        switch (operator) {
            // Fügen Sie hier die Implementierung hinzu
        }
    }

    public void clear() {
        display = INITIAL_VALUE;
        number1 = 0;
        number2 = 0;
    }

    public void deleteLastDigit() {
        display = display.substring(0, display.length() - 1);
        if (display.isEmpty()) {
            display = INITIAL_VALUE;
        }
    }

    /**
     * TODO: Implementieren Sie diese Methode in Übung 2!
     */
    public void add() {
        /*
        Beispiel-Ablauf:
        0
        2 | 2
        3 | 23
        + | 23+
        4 | 23+4
        = | 27
         */
    }
}
