package com.example.demo.controller;

import com.example.demo.MainMenu;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;
import com.example.demo.TransitionScene;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class Controller {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";
	private final Stage stage;

	public Controller(Stage stage, double screenWidth) {
		this.stage = stage;
	}
	
	 public void showMainMenu() {
	        MainMenu mainMenu = new MainMenu(this);
	        Scene menuScene = new Scene(mainMenu.getMenuPane(), stage.getWidth(), stage.getHeight());
	        menuScene.getStylesheets().add(getClass().getResource("/com/example/demo/css/MainMenu.css").toExternalForm());
	        stage.setScene(menuScene);
	        stage.show();
	        applyFadeInTransition(menuScene);
	    }

	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
    InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Class<?> myClass = Class.forName(className);
    Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
    LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
    myLevel.nextLevelProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null && !newValue.isEmpty()) {
            TransitionScene.fadeOutCurrentScene(stage, () -> {
                TransitionScene transitionScene = new TransitionScene(stage, "A mighty foe stands before you...",
                        stage.getWidth(), stage.getHeight());
                transitionScene.showTransition(() -> {
                    goToNextLevel(newValue);
                });
            });
        }
    });
			
			Scene scene = myLevel.initializeScene();
	        scene.getStylesheets().add(getClass().getResource("/com/example/demo/css/Settings.css").toExternalForm());
			stage.setScene(scene);
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
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
				| IllegalAccessException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Error occurred while loading level: " + e.getMessage());
			alert.show();
		}
	}
	
	public void showSettings() {
        GameSettings gameSettings = new GameSettings(this);
        Scene settingsScene = new Scene(gameSettings.getSettingsPane(), stage.getWidth(), stage.getHeight());
        settingsScene.getStylesheets().add(getClass().getResource("/com/example/demo/css/Settings.css").toExternalForm());
        stage.setScene(settingsScene);
        applyFadeInTransition(settingsScene);
    }
}
