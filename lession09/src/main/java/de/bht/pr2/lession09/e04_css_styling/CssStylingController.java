package de.bht.pr2.lession09.e04_css_styling;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

/**
 * Controller fuer die CSS-Demo.
 * Ermoeglicht dynamischen Theme-Wechsel zur Laufzeit.
 *
 * Demonstriert:
 * - Dynamisches Aendern von Stylesheets
 * - Entfernen und Hinzufuegen von CSS-Dateien
 */
public class CssStylingController {

    private Scene scene;
    private boolean isDarkTheme = false;

    @FXML
    private Label themeLabel;

    /**
     * Setzt die Scene-Referenz fuer Theme-Wechsel.
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Wechselt zum Light Theme.
     */
    @FXML
    private void handleLightTheme() {
        if (scene != null && isDarkTheme) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(
                getClass().getResource("styles-light.css").toExternalForm()
            );
            isDarkTheme = false;
            themeLabel.setText("Aktuelles Theme: Light");
        }
    }

    /**
     * Wechselt zum Dark Theme.
     */
    @FXML
    private void handleDarkTheme() {
        if (scene != null && !isDarkTheme) {
            scene.getStylesheets().clear();
            scene.getStylesheets().add(
                getClass().getResource("styles-dark.css").toExternalForm()
            );
            isDarkTheme = true;
            themeLabel.setText("Aktuelles Theme: Dark");
        }
    }

    /**
     * Demo-Handler fuer Primary Button.
     */
    @FXML
    private void handlePrimaryAction() {
        System.out.println("Primary Button geklickt!");
    }

    /**
     * Demo-Handler fuer Secondary Button.
     */
    @FXML
    private void handleSecondaryAction() {
        System.out.println("Secondary Button geklickt!");
    }

    /**
     * Demo-Handler fuer Danger Button.
     */
    @FXML
    private void handleDangerAction() {
        System.out.println("Danger Button geklickt!");
    }
}
