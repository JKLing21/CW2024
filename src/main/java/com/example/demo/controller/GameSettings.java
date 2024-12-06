package com.example.demo.controller;

import java.util.prefs.Preferences;

import factories.interfaces.ComponentsFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameSettings {

    private BorderPane settingsPane;
    private Controller controller;
    private Slider soundEffectsSlider;
    private Slider backgroundMusicSlider;
    private Preferences prefs;
    private ToggleButton soundEffectsToggle;
    private ToggleButton backgroundMusicToggle;

    public GameSettings(Controller controller, ComponentsFactory componentsFactory) {
        this.controller = controller;
        settingsPane = new BorderPane();

        prefs = Preferences.userNodeForPackage(GameSettings.class);
        Label settingsTitle = componentsFactory.createLabel("Settings", "settings-title");
        Button backButton = componentsFactory.createButton("Return", e -> goBackToPreviousScene());
        Button resetToDefaultButton = componentsFactory.createButton("Reset to Default", e -> resetToDefault());
        resetToDefaultButton.getStyleClass().add("button");
        resetToDefaultButton.getStyleClass().add("default-btn");

        Label soundEffectsLabel = componentsFactory.createLabel("Sound Effects", "music-label", "SE-btn");
        soundEffectsSlider = componentsFactory.createSlider(0, 100, prefs.getDouble("soundEffectsVolume", 50.0), 300.0);
        soundEffectsToggle = componentsFactory.createToggleButton(prefs.getBoolean("soundEffectsOn", true));
		soundEffectsToggle.getStyleClass().add("toggle-switch");

        Label backgroundMusicLabel = componentsFactory.createLabel("Background Music", "music-label");
        backgroundMusicSlider = componentsFactory.createSlider(0, 100, prefs.getDouble("backgroundMusicVolume", 50.0), 300.0);
		backgroundMusicToggle = componentsFactory.createToggleButton(prefs.getBoolean("backgroundMusicOn", true));
		backgroundMusicToggle.getStyleClass().add("toggle-switch");

        soundEffectsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putDouble("soundEffectsVolume", newValue.doubleValue());
        });
        soundEffectsToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putBoolean("soundEffectsOn", newValue);
        });
        backgroundMusicSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putDouble("backgroundMusicVolume", newValue.doubleValue());
        });
        backgroundMusicToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            prefs.putBoolean("backgroundMusicOn", newValue);
        });

        HBox titleBox = new HBox(settingsTitle);
        titleBox.setAlignment(Pos.CENTER);

        HBox backButtonBox = new HBox(backButton);
        backButtonBox.setAlignment(Pos.CENTER);

        HBox soundEffectsSliderBox = new HBox(10, soundEffectsSlider, soundEffectsToggle);
        soundEffectsSliderBox.setAlignment(Pos.CENTER);

        VBox soundEffectsBox = new VBox(5, soundEffectsLabel, soundEffectsSliderBox);
        soundEffectsBox.setAlignment(Pos.CENTER);

        HBox backgroundMusicSliderBox = new HBox(10, backgroundMusicSlider, backgroundMusicToggle);
        backgroundMusicSliderBox.setAlignment(Pos.CENTER);

        VBox backgroundMusicBox = new VBox(5, backgroundMusicLabel, backgroundMusicSliderBox);
        backgroundMusicBox.setAlignment(Pos.CENTER);

        VBox soundSettingsPane = new VBox(20, soundEffectsBox, backgroundMusicBox, resetToDefaultButton);
        soundSettingsPane.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(titleBox);
        mainPane.setCenter(soundSettingsPane);
        mainPane.setBottom(backButtonBox);

        settingsPane = mainPane;
    }
    public Pane getSettingsPane() {
        return settingsPane;
    }
    
    private void resetToDefault() {
        soundEffectsSlider.setValue(50.0);
        soundEffectsToggle.setSelected(true);
        backgroundMusicSlider.setValue(50.0);
        backgroundMusicToggle.setSelected(true);
        prefs.putDouble("soundEffectsVolume", 50.0);
		prefs.putBoolean("soundEffectsOn", true);
        prefs.putDouble("backgroundMusicVolume", 50.0);
		prefs.putBoolean("backgroundMusicOn", true);
    }

    private void goBackToPreviousScene() {
        controller.goBack();
    }
}