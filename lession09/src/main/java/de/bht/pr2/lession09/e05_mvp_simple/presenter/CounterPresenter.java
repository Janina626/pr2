package de.bht.pr2.lession09.e05_mvp_simple.presenter;

import de.bht.pr2.lession09.e05_mvp_simple.model.CounterModel;
import de.bht.pr2.lession09.e05_mvp_simple.view.ICounterView;

/**
 * Presenter-Klasse fuer den Counter.
 *
 * Der Presenter ist der "Vermittler" zwischen Model und View:
 * - Reagiert auf Benutzeraktionen (von der View)
 * - Ruft Methoden im Model auf (Geschaeftslogik)
 * - Aktualisiert die View mit neuen Daten
 *
 * Der Presenter kennt:
 * - Das Model (direkte Referenz)
 * - Die View (ueber das Interface - nicht die konkrete Klasse!)
 *
 * Kommunikationsfluss:
 * User -> View -> Presenter -> Model
 *                 Presenter -> View (Update)
 */
public class CounterPresenter {

    private final ICounterView view;
    private final CounterModel model;

    public CounterPresenter(ICounterView view, CounterModel model) {
        this.view = view;
        this.model = model;

        // Presenter bei View registrieren
        view.setPresenter(this);

        // Initiale Anzeige aktualisieren
        updateView();
    }

    /**
     * Wird aufgerufen wenn "+" Button geklickt wird.
     */
    public void onIncrementClicked() {
        model.increment();      // Model aktualisieren
        updateView();           // View aktualisieren
    }

    /**
     * Wird aufgerufen wenn "-" Button geklickt wird.
     */
    public void onDecrementClicked() {
        model.decrement();
        updateView();
    }

    /**
     * Wird aufgerufen wenn "Reset" Button geklickt wird.
     */
    public void onResetClicked() {
        model.reset();
        updateView();
    }

    /**
     * Aktualisiert die View mit dem aktuellen Model-Zustand.
     */
    private void updateView() {
        int count = model.getCount();
        view.setCounterText(String.valueOf(count));
    }
}
