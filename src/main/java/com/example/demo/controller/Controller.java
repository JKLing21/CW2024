package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Stack;
import com.example.demo.ComponentsFactory;
import com.example.demo.ComponentsImplement;
import com.example.demo.ImgAssetLoader;
import com.example.demo.LevelParent;
import com.example.demo.MainMenu;
import com.example.demo.TransitionScene;
import com.example.demo.AssetFactory;
import com.example.demo.AssetsImplement;
import com.example.demo.ImageManager;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;
	private Stack<Scene> sceneStack = new Stack<>();
	private ImageManager imageManager;
	private AssetFactory assetFactory;

	public Controller(Stage stage, double screenWidth) {
		this.stage = stage;
		 this.imageManager = ImageManager.getInstance();
	     this.assetFactory = new AssetsImplement(imageManager);
	}

	public void showMainMenu() {
		ComponentsFactory componentsFactory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {};
		MainMenu mainMenu = new MainMenu(this, componentsFactory, assetLoader);
		Scene menuScene = new Scene(mainMenu.getMenuPane(), stage.getWidth(), stage.getHeight());
		menuScene.getStylesheets().add(getClass().getResource("/com/example/demo/css/MainMenu.css").toExternalForm());
		pushScene(menuScene);
	}

	public void launchGame() throws Exception {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	private void goToLevel(String className) throws Exception {
		Class<?> myClass = Class.forName(className);
		ComponentsFactory componentsFactory = new ComponentsImplement();
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class, Controller.class,
				ComponentsFactory.class,  AssetFactory.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth(), this,
				componentsFactory, assetFactory);
		myLevel.nextLevelProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && !newValue.isEmpty()) {
				TransitionScene.fadeOutCurrentScene(stage, () -> {
					TransitionScene transitionScene = new TransitionScene(stage, "A mighty foe stands before you...",
							stage.getWidth(), stage.getHeight(), componentsFactory);
					transitionScene.showTransition(() -> {
						goToNextLevel(newValue);
					});
				});
			}
		});

		Scene scene = myLevel.initializeScene();
		scene.getStylesheets().add(getClass().getResource("/com/example/demo/css/Settings.css").toExternalForm());
		pushScene(scene);
		myLevel.startGame();
		applyFadeInTransition(scene);
	}

	private void applyFadeInTransition(Scene scene) {
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), scene.getRoot());
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);
		fadeIn.setAutoReverse(false);
		fadeIn.play();
	}

	private void goToNextLevel(String levelName) {
		try {
			goToLevel(levelName);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Error occurred while loading level: " + e.getMessage());
			alert.show();
		}
	}

	public void showSettings() {
		ComponentsFactory componentsFactory = new ComponentsImplement();
		GameSettings gameSettings = new GameSettings(this, componentsFactory);
		Scene settingsScene = new Scene(gameSettings.getSettingsPane(), stage.getWidth(), stage.getHeight());
		settingsScene.getStylesheets()
				.add(getClass().getResource("/com/example/demo/css/Settings.css").toExternalForm());
		pushScene(settingsScene);
	}

	public void goToMainMenu() {
		showMainMenu();
	}

	private void pushScene(Scene scene) {
		sceneStack.push(scene);
		stage.setScene(scene);
		stage.show();
		applyFadeInTransition(scene);
	}

	public void goBack() {
		if (!sceneStack.isEmpty()) {
			sceneStack.pop();
			if (!sceneStack.isEmpty()) {
				Scene previousScene = sceneStack.peek();
				stage.setScene(previousScene);
				stage.show();
				applyFadeInTransition(previousScene);
			}
		}
	}
}