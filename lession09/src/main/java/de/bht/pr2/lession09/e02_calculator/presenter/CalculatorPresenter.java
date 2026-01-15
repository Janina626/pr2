package de.bht.pr2.lession09.e02_calculator.presenter;

import de.bht.pr2.lession09.e02_calculator.model.CalculatorModel;
import de.bht.pr2.lession09.e02_calculator.view.CalculatorView;

/**
 * Presenter-Klasse für den Calculator (MVP-Pattern).
 * Vermittelt zwischen View und Model.
 *
 * ÜBUNG 2: Diese Klasse enthält einen Bug (fehlende break-Statements)!
 * Studierende sollen den Bug finden und beheben.
 */
public class CalculatorPresenter {

    private final CalculatorView view;
    private final CalculatorModel model;

    public CalculatorPresenter(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        view.setPresenter(this);
        updateDisplay();
    }

    private void updateDisplay() {
        view.setDisplayText(model.getCurrentValue());
    }

    public void onCommaButtonClicked() {
        model.appendComma();
        updateDisplay();
    }

    public void onNumberButtonClicked(int number) {
        model.appendNumber(number);
        updateDisplay();
    }

    public void onOperationClicked(Operator operator) {
        System.out.println(operator + " button clicked");
        model.addOperator(operator);
    }

    /**
     * HINWEIS: Diese Methode enthält einen Bug!
     * Die break-Statements fehlen, was zu unerwartetem Verhalten führt.
     */
    public void onFunctionClicked(Function function) {
        System.out.println(function + " button clicked");
        switch (function) {
            case CLEAR:
                model.clear();
            case DELETE:
                model.deleteLastDigit();
            default:
                updateDisplay();
                break;
        }
    }
}
