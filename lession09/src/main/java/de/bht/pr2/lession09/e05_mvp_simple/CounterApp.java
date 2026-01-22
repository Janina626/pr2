package de.bht.pr2.lession09.e05_mvp_simple;

import de.bht.pr2.lession09.e05_mvp_simple.model.CounterModel;
import de.bht.pr2.lession09.e05_mvp_simple.presenter.CounterPresenter;
import de.bht.pr2.lession09.e05_mvp_simple.view.CounterView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Einfache Counter-Anwendung mit MVP-Pattern.
 *
 * Demonstriert das Model-View-Presenter Pattern:
 * - Model: Enthaelt Daten und Geschaeftslogik (CounterModel)
 * - View: Zeigt UI und nimmt Eingaben entgegen (CounterView + FXML)
 * - Presenter: Koordiniert Model und View (CounterPresenter)
 *
 * Vorteile von MVP:
 * - Trennung der Verantwortlichkeiten (Separation of Concerns)
 * - Einfachere Testbarkeit (Model kann isoliert getestet werden)
 * - Austauschbare Views (durch Interface)
 * - Bessere Wartbarkeit bei groesseren Anwendungen
 */
public class CounterApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // 1. FXML laden und View erstellen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Counter.fxml"));
        Parent root = loader.load();

        // 2. View-Controller holen
        CounterView view = loader.getController();

        // 3. Model erstellen
        CounterModel model = new CounterModel();

        // 4. Presenter erstellen - verbindet Model und View
        new CounterPresenter(view, model);

        // 5. Scene und Stage konfigurieren
        Scene scene = new Scene(root, 350, 250);

        primaryStage.setTitle("MVP Counter Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
