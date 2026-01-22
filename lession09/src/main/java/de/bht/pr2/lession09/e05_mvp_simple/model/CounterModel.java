package de.bht.pr2.lession09.e05_mvp_simple.model;

/**
 * Model-Klasse fuer den Counter.
 *
 * Das Model enthaelt:
 * - Die Daten (hier: der Zaehlerstand)
 * - Die Geschaeftslogik (erhoehen, verringern, zuruecksetzen)
 *
 * Wichtig: Das Model kennt weder View noch Presenter!
 * Es ist voellig unabhaengig und kann isoliert getestet werden.
 */
public class CounterModel {

    private int count;

    public CounterModel() {
        this.count = 0;
    }

    /**
     * Gibt den aktuellen Zaehlerstand zurueck.
     */
    public int getCount() {
        return count;
    }

    /**
     * Erhoeht den Zaehler um 1.
     */
    public void increment() {
        count++;
    }

    /**
     * Verringert den Zaehler um 1.
     */
    public void decrement() {
        count--;
    }

    /**
     * Setzt den Zaehler auf 0 zurueck.
     */
    public void reset() {
        count = 0;
    }
}
