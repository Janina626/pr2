package de.bht.pr2.lession09.e05_mvp_simple.view;

import de.bht.pr2.lession09.e05_mvp_simple.presenter.CounterPresenter;

/**
 * Interface fuer die Counter-View.
 *
 * Warum ein Interface?
 * - Entkopplung: Der Presenter arbeitet mit dem Interface, nicht der konkreten Klasse
 * - Testbarkeit: Fuer Tests kann eine Mock-Implementierung verwendet werden
 * - Austauschbarkeit: Verschiedene UI-Implementierungen moeglich (JavaFX, Swing, Web...)
 *
 * Das Interface definiert:
 * - Wie die View Daten anzeigt (setCounterText)
 * - Wie der Presenter mit der View verbunden wird (setPresenter)
 */
public interface ICounterView {

    /**
     * Setzt den anzuzeigenden Zaehlertext.
     */
    void setCounterText(String text);

    /**
     * Setzt den Presenter fuer diese View.
     */
    void setPresenter(CounterPresenter presenter);
}
