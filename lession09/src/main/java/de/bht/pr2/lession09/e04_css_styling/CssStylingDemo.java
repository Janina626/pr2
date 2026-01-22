package de.bht.pr2.lession09.e04_css_styling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Demonstriert CSS-Styling in JavaFX.
 *
 * Wichtige CSS-Konzepte:
 * - Einbinden von Stylesheets mit scene.getStylesheets()
 * - Style-Klassen (.button, .label, etc.)
 * - IDs (#myButton)
 * - Pseudo-Klassen (:hover, :pressed, :focused)
 * - JavaFX-spezifische Properties (-fx-background-color, -fx-text-fill, etc.)
 */
public class CssStylingDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CssDemo.fxml"));
        Parent root = loader.load();

        // Controller holen und Scene uebergeben (fuer Theme-Wechsel)
        CssStylingController controller = loader.getController();

        Scene scene = new Scene(root, 550, 650);

        // Standard-Theme (Light) laden
        scene.getStylesheets().add(getClass().getResource("styles-light.css").toExternalForm());

        // Scene dem Controller uebergeben
        controller.setScene(scene);

        primaryStage.setTitle("CSS Styling Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
