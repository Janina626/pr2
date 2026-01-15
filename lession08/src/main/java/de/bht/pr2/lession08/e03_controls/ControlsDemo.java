package de.bht.pr2.lession08.e03_controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Demonstriert gängige JavaFX UI-Controls.
 */
public class ControlsDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        // Label
        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 0);

        // TextField
        TextField nameField = new TextField();
        nameField.setPromptText("Namen eingeben");
        grid.add(nameField, 1, 0);

        // PasswordField
        Label passwordLabel = new Label("Passwort:");
        grid.add(passwordLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Passwort eingeben");
        grid.add(passwordField, 1, 1);

        // CheckBox
        CheckBox rememberMe = new CheckBox("Angemeldet bleiben");
        grid.add(rememberMe, 1, 2);

        // RadioButtons
        Label genderLabel = new Label("Geschlecht:");
        grid.add(genderLabel, 0, 3);

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Männlich");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Weiblich");
        femaleRadio.setToggleGroup(genderGroup);

        grid.add(maleRadio, 1, 3);
        grid.add(femaleRadio, 1, 4);

        // ComboBox
        Label countryLabel = new Label("Land:");
        grid.add(countryLabel, 0, 5);

        ComboBox<String> countryCombo = new ComboBox<>(
            FXCollections.observableArrayList("Deutschland", "Frankreich", "UK", "USA")
        );
        countryCombo.setPromptText("Land auswählen");
        grid.add(countryCombo, 1, 5);

        // Button
        Button submitButton = new Button("Absenden");
        grid.add(submitButton, 1, 6);

        // Output Label
        Label outputLabel = new Label();
        grid.add(outputLabel, 0, 7, 2, 1);

        // Button-Aktion
        submitButton.setOnAction(e -> {
            String output = String.format("Name: %s, Angemeldet: %s, Land: %s",
                nameField.getText(),
                rememberMe.isSelected(),
                countryCombo.getValue());
            outputLabel.setText(output);
        });

        Scene scene = new Scene(grid, 400, 350);
        primaryStage.setTitle("Controls Demo - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
