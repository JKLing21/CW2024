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

public class WinScreen {
	private final Stage stage;
	private final ImgAssetLoader assetLoader;
	private final ComponentsFactory componentsFactory;
	private final Controller controller;

	public WinScreen(Stage stage, ImgAssetLoader assetLoader, ComponentsFactory componentsFactory,
			LevelParent levelParent, Controller controller) {
		this.stage = stage;
		this.assetLoader = assetLoader;
		this.componentsFactory = componentsFactory;
		this.controller = controller;
	}

	public void showWinScreen() {
		Image winImage = assetLoader.loadAsset("youwin");
		if (winImage == null) {
			System.err.println("Win image not found!");
			return;
		}
		ImageView winImageView = new ImageView(winImage);
		winImageView.setFitWidth(500);
		winImageView.setPreserveRatio(true);

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
		layout.setStyle("-fx-alignment: center; -fx-background-color: lightblue;");
		layout.getChildren().addAll(winImageView, restartButton, mainMenuButton);

		Scene winScene = new Scene(layout, 800, 600);
		stage.setScene(winScene);
		stage.show();
	}
}
