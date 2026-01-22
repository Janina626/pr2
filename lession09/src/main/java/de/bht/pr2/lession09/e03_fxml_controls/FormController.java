package de.bht.pr2.lession09.e03_fxml_controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller fuer das Formular-Beispiel.
 * Zeigt @FXML-Injection fuer verschiedene Control-Typen.
 *
 * Wichtige Konzepte:
 * - @FXML Annotation fuer UI-Element-Binding
 * - initialize() Methode fuer Initialisierung
 * - Event-Handler mit #methodenName in FXML
 * - ObservableList fuer ListView
 */
public class FormController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> countryCombo;

    @FXML
    private CheckBox termsCheckBox;

    @FXML
    private Label statusLabel;

    @FXML
    private ListView<String> entriesList;

    // Observable Liste fuer die ListView
    private ObservableList<String> entries = FXCollections.observableArrayList();

    /**
     * Wird automatisch nach dem Laden des FXML aufgerufen.
     * Hier werden Controls initialisiert (z.B. ComboBox befuellen).
     */
    @FXML
    public void initialize() {
        // ComboBox mit Laendern befuellen
        countryCombo.setItems(FXCollections.observableArrayList(
            "Deutschland",
            "Oesterreich",
            "Schweiz",
            "Andere"
        ));
        countryCombo.getSelectionModel().selectFirst();

        // ListView mit der Observable Liste verbinden
        entriesList.setItems(entries);
    }

    /**
     * Handler fuer den "Absenden" Button.
     * Validiert die Eingaben und fuegt sie zur Liste hinzu.
     */
    @FXML
    private void handleSubmit() {
        // Eingaben lesen
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String country = countryCombo.getValue();
        boolean termsAccepted = termsCheckBox.isSelected();

        // Validierung
        if (name.isEmpty()) {
            setStatus("Bitte Namen eingeben!", true);
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            setStatus("Bitte gueltige E-Mail eingeben!", true);
            return;
        }

        if (!termsAccepted) {
            setStatus("Bitte AGB akzeptieren!", true);
            return;
        }

        // Eintrag erstellen und zur Liste hinzufuegen
        String entry = String.format("%s (%s) - %s", name, email, country);
        entries.add(entry);

        // Formular zuruecksetzen
        handleClear();
        setStatus("Eintrag erfolgreich hinzugefuegt!", false);
    }

    /**
     * Handler fuer den "Loeschen" Button.
     * Setzt alle Formularfelder zurueck.
     */
    @FXML
    private void handleClear() {
        nameField.clear();
        emailField.clear();
        countryCombo.getSelectionModel().selectFirst();
        termsCheckBox.setSelected(false);
        statusLabel.setText("");
    }

    /**
     * Handler fuer den "Entfernen" Button.
     * Entfernt den ausgewaehlten Eintrag aus der Liste.
     */
    @FXML
    private void handleRemove() {
        String selected = entriesList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            entries.remove(selected);
            setStatus("Eintrag entfernt.", false);
        } else {
            setStatus("Bitte einen Eintrag auswaehlen!", true);
        }
    }

    /**
     * Setzt die Statusmeldung.
     */
    private void setStatus(String message, boolean isError) {
        statusLabel.setText(message);
        if (isError) {
            statusLabel.setStyle("-fx-text-fill: red;");
        } else {
            statusLabel.setStyle("-fx-text-fill: green;");
        }
    }
}
