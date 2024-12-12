package com.example.demo;

import com.example.demo.controller.Controller;
import factories.interfaces.ComponentsFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoseScreen {
    private final Stage stage;
    private final ImgAssetLoader assetLoader;
    private final ComponentsFactory componentsFactory;
    private final Controller controller;

    public LoseScreen(Stage stage, ImgAssetLoader assetLoader, ComponentsFactory componentsFactory,
			LevelParent levelParent, Controller controller) {
        this.stage = stage;
        this.assetLoader = assetLoader;
        this.componentsFactory = componentsFactory;
        this.controller = controller;
    }

    public void showLoseScreen() {
        Image loseImage = assetLoader.loadAsset("gameover");
        if (loseImage == null) {
            System.err.println("Lose image not found!");
            return;
        }

        ImageView loseImageView = new ImageView(loseImage);
        loseImageView.setFitWidth(500);
        loseImageView.setPreserveRatio(true);

        ImageView restartButton = componentsFactory.getImgViewFactory().createRestartButton(95, 75, e -> {
            try {
                this.controller.launchGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ImageView mainMenuButton = componentsFactory.getImgViewFactory().createMainMenuButton(85, 75,
                e -> controller.goToMainMenu());

        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-background-color: lightcoral;");
        layout.getChildren().addAll(loseImageView, restartButton, mainMenuButton);

        Scene loseScene = new Scene(layout, 800, 600);
        stage.setScene(loseScene);
        stage.show();
    }
}
