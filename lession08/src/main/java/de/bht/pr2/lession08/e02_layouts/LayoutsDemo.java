package de.bht.pr2.lession08.e02_layouts;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Demonstriert verschiedene JavaFX Layout-Container.
 */
public class LayoutsDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Haupt-BorderPane Layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // OBEN: HBox
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));
        topBox.setStyle("-fx-background-color: #e0e0e0;");
        topBox.getChildren().addAll(
            new Label("HBox:"),
            new Button("Button 1"),
            new Button("Button 2"),
            new Button("Button 3")
        );
        root.setTop(topBox);

        // LINKS: VBox
        VBox leftBox = new VBox(10);
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setPadding(new Insets(10));
        leftBox.setStyle("-fx-background-color: #f0f0f0;");
        leftBox.getChildren().addAll(
            new Label("VBox:"),
            new Button("Element 1"),
            new Button("Element 2"),
            new Button("Element 3")
        );
        root.setLeft(leftBox);

        // MITTE: GridPane
        GridPane centerGrid = new GridPane();
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(5);
        centerGrid.setVgap(5);
        centerGrid.setPadding(new Insets(10));
        centerGrid.setStyle("-fx-background-color: #ffffff;");

        Label gridLabel = new Label("GridPane (3x3):");
        centerGrid.add(gridLabel, 0, 0, 3, 1);

        for (int row = 1; row <= 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button(row + "," + col);
                btn.setPrefSize(60, 40);
                centerGrid.add(btn, col, row);
            }
        }
        root.setCenter(centerGrid);

        // RECHTS: StackPane
        StackPane rightStack = new StackPane();
        rightStack.setPadding(new Insets(10));
        rightStack.setStyle("-fx-background-color: #d0d0d0;");

        Label stackLabel = new Label("StackPane");
        Button stackButton = new Button("Gestapelt");
        stackButton.setTranslateX(10);
        stackButton.setTranslateY(10);
        rightStack.getChildren().addAll(stackLabel, stackButton);
        root.setRight(rightStack);

        // UNTEN: FlowPane
        FlowPane bottomFlow = new FlowPane();
        bottomFlow.setHgap(5);
        bottomFlow.setVgap(5);
        bottomFlow.setPadding(new Insets(10));
        bottomFlow.setStyle("-fx-background-color: #c0c0c0;");
        bottomFlow.getChildren().add(new Label("FlowPane:"));
        for (int i = 1; i <= 6; i++) {
            bottomFlow.getChildren().add(new Button("Flow " + i));
        }
        root.setBottom(bottomFlow);

        Scene scene = new Scene(root, 600, 450);
        primaryStage.setTitle("Layouts Demo - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
