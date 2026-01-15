package de.bht.pr2.lession09.e02_calculator;

import de.bht.pr2.lession09.e02_calculator.model.CalculatorModel;
import de.bht.pr2.lession09.e02_calculator.presenter.CalculatorPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Calculator-Anwendung mit MVP-Pattern.
 * Demonstriert FXML, CSS-Styling und MVP-Architektur.
 */
public class CalculatorApplication extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Calculator.fxml"));
        final Parent load = loader.load();
        new CalculatorPresenter(loader.getController(), new CalculatorModel());

        Scene scene = new Scene(load);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("view/Calculator.css").toExternalForm());

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();

        enableDrag(load, primaryStage);
    }

    private void enableDrag(Node node, Stage stage) {
        node.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        node.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
