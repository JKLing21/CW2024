package com.example.demo.Screens;

import com.example.demo.Controller;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Levels.LevelParent;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * LoseScreen class displays the game over screen when player loses.
 */
public class LoseScreen {
    private final Stage stage;
    private final ImgAssetLoader assetLoader;
    private final ComponentsFactory componentsFactory;
    private final Controller controller;
    /**
     * Constructs new LoseScreen instance.
     *
     * @param stage: primary stage where lose screen will be displayed.
     * @param assetLoader: ImgAssetLoader which responsible for loading images.
     * @param componentsFactory: ComponentsFactory used for UI components creation.
     * @param levelParent: LevelParent object.
     * @param controller: controller which manages the game flow.
     */
    public LoseScreen(Stage stage, ImgAssetLoader assetLoader, ComponentsFactory componentsFactory,
			LevelParent levelParent, Controller controller) {
        this.stage = stage;
        this.assetLoader = assetLoader;
        this.componentsFactory = componentsFactory;
        this.controller = controller;
    }
    /**
     * Displays lose screen with game over image, restart icon and main menu icon.
     */
    public void showLoseScreen() {
    	// Load game over image
        Image loseImage = assetLoader.loadAsset("gameover");
        if (loseImage == null) {
            System.err.println("Lose image not found!");
            return;
        }
        // Create ImageView for game over image
        ImageView loseImageView = new ImageView(loseImage);
        loseImageView.setFitWidth(500);
        loseImageView.setPreserveRatio(true);
        // Create restart button
        ImageView restartButton = componentsFactory.getImgViewFactory().createRestartButton(95, 75, e -> {
            try {
            	// Restart the game
                this.controller.launchGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // Create main menu button
        ImageView mainMenuButton = componentsFactory.getImgViewFactory().createMainMenuButton(85, 75,
                e -> controller.goToMainMenu());
        // Create VBox layout to hold UI elements
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-background-color: lightcoral;");
        layout.getChildren().addAll(loseImageView, restartButton, mainMenuButton);
        // Create new scene with the layout
        Scene loseScene = new Scene(layout, 800, 600);
        stage.setScene(loseScene);
        stage.show();
    }
}
