package com.example.demo;

import com.example.demo.controller.Controller;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;

public class MainMenu {

    private StackPane root;
    private Controller controller;

    public MainMenu(Controller controller) {
        this.controller = controller;

        Button startButton = new Button("Start Game");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Quit");

        startButton.setOnAction(e -> {
            try {
                this.controller.launchGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        settingsButton.setOnAction(e -> this.controller.showSettings());
        exitButton.setOnAction(e -> showQuitConfirmation());

        VBox menuPane = new VBox(20);
        menuPane.setAlignment(Pos.BOTTOM_CENTER);
        menuPane.getChildren().addAll(startButton, settingsButton, exitButton);
        menuPane.getStyleClass().add("menu-pane"); 
        VBox.setVgrow(menuPane, Priority.ALWAYS);
        menuPane.setTranslateY(-15);

        root = new StackPane();

        ImageView background = new ImageView(new Image(getClass().getResource("/com/example/demo/images/MainMenu.jpeg").toString()));
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());
        background.setPreserveRatio(false);

        root.getChildren().addAll(background, menuPane);
    }
    
    private void showQuitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Are you sure you want to quit the game?");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/com/example/demo/css/MainMenu.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-dialog-pane");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        dialogPane.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                System.exit(0);
            }
        });
    }

    public Pane getMenuPane() {
        return root;
    }
}