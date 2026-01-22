package de.bht.pr2.lession09.e03_fxml_controls;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Demonstriert ein komplexeres Formular mit FXML.
 * Zeigt die Verwendung verschiedener Controls in FXML:
 * - TextField fuer Texteingaben
 * - ComboBox fuer Auswahllisten
 * - CheckBox fuer Optionen
 * - ListView fuer Eintraege
 * - Button mit Event-Handlern
 */
public class FormDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // FXML laden
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Form.fxml"));
        Parent root = loader.load();

        // Scene erstellen
        Scene scene = new Scene(root, 500, 450);

        // Stage konfigurieren
        primaryStage.setTitle("Form Demo - FXML Controls");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
