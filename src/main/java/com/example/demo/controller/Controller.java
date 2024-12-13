package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.util.Stack;

import com.example.demo.Assets.AudioAssetLoader;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Levels.LevelParent;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Managers.ImageManager;
import com.example.demo.Screens.MainMenu;
import com.example.demo.Screens.TransitionScene;

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
import java.lang.reflect.InvocationTargetException;
/**
 * Controller class manages flow of the game, including scene transitions,
 * level loading, and audio management. It acts as the central hub for controlling
 * the game's main menu, levels and settings.
 */
public class Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.Levels.LevelOne";
	private final Stage stage;
	private final AudioManager audioManager;
	private final AudioAssetLoader audioAssetLoader;
	private Stack<Scene> sceneStack = new Stack<>();
	private ImageManager imageManager;
	private AssetFactory assetFactory;
	private ComponentsFactory componentsFactory;
	/**
	 * Constructs new Controller instance.
	 * Initialises stage, audio manager, and other necessary components.
	 *
	 * @param stage: primary stage of the application.
	 * @param screenWidth: width of screen.
	 */
	public Controller(Stage stage, double screenWidth) {
		this.stage = stage;
		this.audioAssetLoader = new AudioAssetLoader() {};
		this.audioManager = new AudioManager(audioAssetLoader);
		this.imageManager = ImageManager.getInstance();
		this.assetFactory = new AssetsImplement(imageManager);
		this.componentsFactory = new ComponentsImplement();
	}
	/**
	 * Displays main menu of the game.
	 * Initialises main menu scene and sets it as current scene.
	 */
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
	/**
	 * Launches the game by showing the stage and transitioning to first level.
	 *
	 * @throws Exception If an error occurs while loading first level.
	 */
	public void launchGame() throws Exception {
		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}
	/**
	 * Resets game and relaunches it by clearing the scene stack, showing main menu and launching the game.
	 */
	public void resetAndRelaunchGame() {
	    try {
	        sceneStack.clear();
	        showMainMenu();
	        launchGame();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Loads and transitions to specified level.
	 *
	 * @param className: fully qualified class name of level to load.
	 * @throws Exception if an error occurs while loading the level.
	 */
	private void goToLevel(String className) throws Exception {
	    audioManager.stopBackgroundMusic();
	    try {
	        Class<?> myClass = Class.forName(className);
	        String backgroundImage = (String) myClass.getField("BACKGROUND_IMAGE").get(null);
	        Constructor<?> constructor = myClass.getConstructor(String.class, double.class, double.class, int.class,
	                Controller.class, ComponentsFactory.class, AssetFactory.class, AudioManager.class, Stage.class);
	        LevelParent myLevel = (LevelParent) constructor.newInstance(backgroundImage, stage.getHeight(),
	                stage.getWidth(), 5, this, componentsFactory, assetFactory, audioManager, stage);
	        myLevel.nextLevelProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null && !newValue.isEmpty()) {
	                if (newValue.equals("com.example.demo.Levels.LevelThree")) {
	                    TransitionScene.fadeOutCurrentScene(stage, () -> {
	                        TransitionScene transitionScene = new TransitionScene(stage, "A mighty foe stands before you...",
	                                stage.getWidth(), stage.getHeight(), componentsFactory);
	                        transitionScene.showTransition(() -> goToNextLevel(newValue));
	                    });
	                } else if (newValue.equals("com.example.demo.Levels.LevelTwo")) {
	                    TransitionScene.fadeOutCurrentScene(stage, () -> {
	                        TransitionScene transitionScene = new TransitionScene(stage, null,
	                                stage.getWidth(), stage.getHeight(), componentsFactory);
	                        transitionScene.showTransition(() -> goToNextLevel(newValue));
	                    });
	                } else {
	                    goToNextLevel(newValue);
	                }
	            }
	        });

	        Scene scene = myLevel.initializeScene();
	        scene.getStylesheets().add(getClass().getResource("/com/example/demo/css/Settings.css").toExternalForm());
	        pushScene(scene);
	        myLevel.startGame();
	        applyFadeInTransition(scene);

	        if (className.equals(LEVEL_ONE_CLASS_NAME)) {
	            audioManager.playBackgroundMusic("LevelOneBGM");
	        } else if (className.equals("com.example.demo.Levels.LevelTwo")) {
	            audioManager.playBackgroundMusic("LevelTwoBGM");
	        } else if (className.equals("com.example.demo.Levels.LevelThree")) {
	            audioManager.playBackgroundMusic("BossBGM");
	        }
	        audioManager.setBackgroundMusicVolume(audioManager.getBackgroundMusicVolume());
	    } catch (InvocationTargetException e) {
	        e.printStackTrace();
	        System.err.println("Root cause: " + e.getCause());
	        e.getCause().printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.err.println("Error occurred while loading level: " + e.getMessage());
	    }
	}
	/**
	 * Updates background music volume.
	 *
	 * @param volume: new volume level for background music.
	 */
	public void updateBackgroundMusicVolume(double volume) {
	    audioManager.setBackgroundMusicVolume(volume);
	    System.out.println("Background music volume updated to: " + volume);
	}
	/**
	 * Updates sound effects volume.
	 *
	 * @param volume: new volume level for sound effects.
	 */
	public void updateSoundEffectsVolume(double volume) {
	    audioManager.setSoundEffectsVolume(volume);
	    System.out.println("Sound effects volume updated to: " + volume);
	}
	/**
	 * Applies fade-in transition to given scene.
	 *
	 * @param scene: scene to which fade-in transition is applied.
	 */
	private void applyFadeInTransition(Scene scene) {
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), scene.getRoot());
		fadeIn.setFromValue(0);
		fadeIn.setToValue(1);
		fadeIn.setCycleCount(1);
		fadeIn.setAutoReverse(false);
		fadeIn.play();
	}
	/**
	 * Transitions to next level specified by level name.
	 *
	 * @param levelName: fully qualified class name of next level.
	 */
	private void goToNextLevel(String levelName) {
	    try {
	        goToLevel(levelName);
	    } catch (Exception e) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setContentText("Error occurred while loading level: " + e.getMessage());
	        alert.show();
	    }
	}
	/**
	 * Displays game settings screen.
	 */
	public void showSettings() {
		ComponentsFactory componentsFactory = new ComponentsImplement();
		GameSettings gameSettings = new GameSettings(this, componentsFactory, audioManager);
		Scene settingsScene = new Scene(gameSettings.getSettingsPane(), stage.getWidth(), stage.getHeight());
		settingsScene.getStylesheets()
				.add(getClass().getResource("/com/example/demo/css/Settings.css").toExternalForm());
		pushScene(settingsScene);
	}
	/**
	 * Transitions back to main menu.
	 */
	public void goToMainMenu() {
		showMainMenu();
	}
	/**
	 * Pushes new scene onto scene stack and sets it as current scene.
	 *
	 * @param scene: scene to push onto the stack.
	 */
	private void pushScene(Scene scene) {
	    if (!sceneStack.isEmpty() && sceneStack.peek() == scene) {
	        return;
	    }
	    sceneStack.push(scene);
	    stage.setScene(scene);
	    stage.show();
	    applyFadeInTransition(scene);
	}
	/**
	 * Goes back to previous scene in scene stack.
	 */
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
	/**
	 * Gets audio manager instance.
	 *
	 * @return audio manager.
	 */
	public AudioManager getAudioManager() {
        return audioManager;
    }
	/**
	 * Gets primary stage of application.
	 *
	 * @return primary stage.
	 */
	public Stage getStage() {
	    return stage;
	}

}