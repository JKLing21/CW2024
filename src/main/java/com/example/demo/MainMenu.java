package com.example.demo;

import com.example.demo.controller.Controller;

import factories.interfaces.ComponentsFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenu {

    private StackPane root;
    private Controller controller;
    private final Image MainMenuImage;

    public MainMenu(Controller controller, ComponentsFactory componentsFactory, ImgAssetLoader assetLoader) {
        this.controller = controller;
        this.MainMenuImage = assetLoader.loadImage("MainMenu");
        if (MainMenuImage == null) {
            System.out.println("Background image not found.");
            return;
        }

		Button startButton = componentsFactory.createMenuButton("Start Game", e -> {
            try {
                this.controller.launchGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

		Button settingsButton = componentsFactory.createMenuButton("Settings", e -> this.controller.showSettings());
		Button exitButton = componentsFactory.createMenuButton("Quit", e -> showQuitConfirmation());

        VBox menuPane = new VBox(20);
        menuPane.setAlignment(Pos.BOTTOM_CENTER);
        menuPane.getChildren().addAll(startButton, settingsButton, exitButton);
        menuPane.getStyleClass().add("menu-pane"); 
        VBox.setVgrow(menuPane, Priority.ALWAYS);
        menuPane.setTranslateY(-15);

        root = new StackPane();

        ImageView background = new ImageView(MainMenuImage);
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