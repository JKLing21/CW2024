package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Stack;

import com.example.demo.AudioAssetLoader;
import com.example.demo.AudioManager;
import com.example.demo.ImageManager;
import com.example.demo.ImgAssetLoader;
import com.example.demo.LevelParent;
import com.example.demo.MainMenu;
import com.example.demo.TransitionScene;

import factories.AssetsImplement;
import factories.ComponentsImplement;
import factories.interfaces.AssetFactory;
import factories.interfaces.ComponentsFactory;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;
	private final AudioManager audioManager;
	private final AudioAssetLoader audioAssetLoader;
	private Stack<Scene> sceneStack = new Stack<>();
	private ImageManager imageManager;
	private AssetFactory assetFactory;
	private ComponentsFactory componentsFactory;

	public Controller(Stage stage, double screenWidth) {
		this.stage = stage;
		this.audioAssetLoader = new AudioAssetLoader() {};
		this.audioManager = new AudioManager(audioAssetLoader);
		this.imageManager = ImageManager.getInstance();
		this.assetFactory = new AssetsImplement(imageManager);
		this.componentsFactory = new ComponentsImplement();
	}

	public void showMainMenu() {
		ComponentsFactory componentsFactory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {
		};
		MainMenu mainMenu = new MainMenu(this, componentsFactory, assetLoader);
		Scene menuScene = new Scene(mainMenu.getMenuPane(), stage.getWidth(), stage.getHeight());
		menuScene.getStylesheets().add(getClass().getResource("/com/example/demo/css/MainMenu.css").toExternalForm());
		pushScene(menuScene);
		audioManager.playBackgroundMusic("MainMenuBGM");
		audioManager.setBackgroundMusicVolume(audioManager.getBackgroundMusicVolume());
	}

	public void launchGame() throws Exception {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}
	
	public void resetAndRelaunchGame() {
	    try {
	        sceneStack.clear();
	        showMainMenu();
	        launchGame();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private void goToLevel(String className) throws Exception {
		audioManager.stopBackgroundMusic();
		Class<?> myClass = Class.forName(className);
		String backgroundImage = (String) myClass.getField("BACKGROUND_IMAGE").get(null);
		Constructor<?> constructor = myClass.getConstructor(String.class, double.class, double.class, int.class,
				Controller.class, ComponentsFactory.class, AssetFactory.class, AudioManager.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(backgroundImage, stage.getHeight(),
				stage.getWidth(), 5, this, componentsFactory, assetFactory, audioManager);
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
		
		if (className.equals(LEVEL_ONE_CLASS_NAME)) {
            audioManager.playBackgroundMusic("LevelOneBGM");
            audioManager.setBackgroundMusicVolume(audioManager.getBackgroundMusicVolume());
        } else if (className.equals("com.example.demo.LevelTwo")) {
            audioManager.playBackgroundMusic("BossBGM");
            audioManager.setBackgroundMusicVolume(audioManager.getBackgroundMusicVolume());
        }
	}
	
	public void updateBackgroundMusicVolume(double volume) {
	    audioManager.setBackgroundMusicVolume(volume);
	    System.out.println("Background music volume updated to: " + volume);
	}

	public void updateSoundEffectsVolume(double volume) {
	    audioManager.setSoundEffectsVolume(volume);
	    System.out.println("Sound effects volume updated to: " + volume);
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
		GameSettings gameSettings = new GameSettings(this, componentsFactory, audioManager);
		Scene settingsScene = new Scene(gameSettings.getSettingsPane(), stage.getWidth(), stage.getHeight());
		settingsScene.getStylesheets()
				.add(getClass().getResource("/com/example/demo/css/Settings.css").toExternalForm());
		pushScene(settingsScene);
	}

	public void goToMainMenu() {
		showMainMenu();
	}

	private void pushScene(Scene scene) {
	    if (!sceneStack.isEmpty() && sceneStack.peek() == scene) {
	        return;
	    }
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
	
	public AudioManager getAudioManager() {
        return audioManager;
    }
}