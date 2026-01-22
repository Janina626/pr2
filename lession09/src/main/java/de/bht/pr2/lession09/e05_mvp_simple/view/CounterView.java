package de.bht.pr2.lession09.e05_mvp_simple.view;

import de.bht.pr2.lession09.e05_mvp_simple.presenter.CounterPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * View-Klasse fuer den Counter (FXML-Controller).
 *
 * Die View ist "duenn" - sie enthaelt keine Logik!
 * - Nimmt Benutzereingaben entgegen (Button-Klicks)
 * - Delegiert alle Aktionen an den Presenter
 * - Zeigt Daten an, die vom Presenter geliefert werden
 *
 * Implementiert das ICounterView Interface fuer Entkopplung.
 */
public class CounterView implements ICounterView {

    private CounterPresenter presenter;

    @FXML
    private Label counterLabel;

    // === Interface-Methoden ===

    @Override
    public void setCounterText(String text) {
        counterLabel.setText(text);
    }

    @Override
    public void setPresenter(CounterPresenter presenter) {
        this.presenter = presenter;
    }

    // === Event-Handler (delegieren an Presenter) ===

    @FXML
    private void handleIncrement() {
        // Nicht selbst zaehlen! An Presenter delegieren.
        presenter.onIncrementClicked();
    }

    @FXML
    private void handleDecrement() {
        presenter.onDecrementClicked();
    }

    @FXML
    private void handleReset() {
        presenter.onResetClicked();
    }
}
