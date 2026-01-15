package de.bht.pr2.lession09.e02_calculator.view;

import de.bht.pr2.lession09.e02_calculator.presenter.CalculatorPresenter;
import de.bht.pr2.lession09.e02_calculator.presenter.Function;
import de.bht.pr2.lession09.e02_calculator.presenter.Operator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * View-Klasse für den Calculator.
 * Verarbeitet UI-Events und delegiert an den Presenter.
 */
public class CalculatorView implements ICalculatorView {
    private CalculatorPresenter presenter;

    @FXML
    private Label display;

    @FXML
    void onCleanButtonClicked(ActionEvent event) {
        presenter.onFunctionClicked(Function.CLEAR);
    }

    @FXML
    void onDeleteButtonClicked(ActionEvent event) {
        presenter.onFunctionClicked(Function.DELETE);
    }

    @FXML
    void onPercentButtonClicked(ActionEvent event) {
        presenter.onFunctionClicked(Function.PERCENT);
    }

    @FXML
    void onNegateButtonClicked(ActionEvent event) {
        presenter.onFunctionClicked(Function.NEGATE);
    }

    @FXML
    void onAddButtonClicked(ActionEvent event) {
        presenter.onOperationClicked(Operator.ADD);
    }

    @FXML
    void onSubtractButton(ActionEvent event) {
        presenter.onOperationClicked(Operator.SUBSTRACT);
    }

    @FXML
    void onDivideButtonClicked(ActionEvent event) {
        presenter.onOperationClicked(Operator.DIVIDE);
    }

    @FXML
    void onEqualButtonClicked(ActionEvent event) {
        presenter.onOperationClicked(Operator.EQUAL);
    }

    @FXML
    void onMultiplyButtonClicked(ActionEvent event) {
        presenter.onOperationClicked(Operator.MULTIPLY);
    }

    @FXML
    void onCommaButtonClicked(ActionEvent event) {
        presenter.onCommaButtonClicked();
    }

    @FXML
    void onNumberClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        final String text = button.getText();
        int number = Integer.parseInt(text);
        presenter.onNumberButtonClicked(number);
    }

    @Override
    public void setPresenter(CalculatorPresenter presenter) {
        this.presenter = presenter;
    }

    public void setDisplayText(String text) {
        display.setText(text);
    }
}
