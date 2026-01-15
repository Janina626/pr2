package de.bht.pr2.lession09.e01_fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller-Klasse für die FXML-Demo.
 * Diese Klasse enthält die Logik für die in DemoController.fxml definierte UI.
 */
public class DemoController {

    @FXML
    private TextField nameField;

    @FXML
    private Label greetingLabel;

    private int clickCount = 0;

    @FXML
    private void handleGreetButton() {
        String name = nameField.getText();
        if (name.isEmpty()) {
            greetingLabel.setText("Bitte geben Sie Ihren Namen ein!");
        } else {
            greetingLabel.setText("Hallo, " + name + "!");
        }
    }

    @FXML
    private void handleCountButton() {
        clickCount++;
        greetingLabel.setText("Button " + clickCount + " mal geklickt");
    }

    @FXML
    private void handleClearButton() {
        nameField.clear();
        greetingLabel.setText("");
        clickCount = 0;
    }
}
