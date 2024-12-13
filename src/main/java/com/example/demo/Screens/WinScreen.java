package com.example.demo.Screens;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Levels.LevelParent;
import com.example.demo.controller.Controller;

import factories.interfaces.ComponentsFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * WinScreen class displays win screen when player defeated the game.
 */
public class WinScreen {
	private final Stage stage;
	private final ImgAssetLoader assetLoader;
	private final ComponentsFactory componentsFactory;
	private final Controller controller;
	/**
	 * Constructs new WinScreen instance.
	 *
	 * @param stage: primary stage where win screen will be displayed.
	 * @param assetLoader: ImgAssetLoader which responsible for loading images.
	 * @param componentsFactory: ComponentsFactory used for UI components creation.
	 * @param levelParent: LevelParent object.
	 * @param controller: controller which manages the game flow.
	 */
	public WinScreen(Stage stage, ImgAssetLoader assetLoader, ComponentsFactory componentsFactory,
			LevelParent levelParent, Controller controller) {
		this.stage = stage;
		this.assetLoader = assetLoader;
		this.componentsFactory = componentsFactory;
		this.controller = controller;
	}
	/**
	 * Displays win screen with "You Win" image, restart icon and main menu icon.
	 */
	public void showWinScreen() {
		// Load "You Win" image
		Image winImage = assetLoader.loadAsset("youwin");
		if (winImage == null) {
			System.err.println("Win image not found!");
			return;
		}
		// Create ImageView for "You Win" image
		ImageView winImageView = new ImageView(winImage);
		winImageView.setFitWidth(500);
		winImageView.setPreserveRatio(true);
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
		layout.setStyle("-fx-alignment: center; -fx-background-color: lightblue;");
		layout.getChildren().addAll(winImageView, restartButton, mainMenuButton);
		// Create new scene with the layout
		Scene winScene = new Scene(layout, 800, 600);
		stage.setScene(winScene);
		stage.show();
	}
}
