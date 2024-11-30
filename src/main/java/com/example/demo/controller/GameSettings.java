package com.example.demo.controller;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import java.util.prefs.Preferences;

public class GameSettings {

    private BorderPane settingsPane;
    private Controller controller;
    private Slider soundEffectsSlider;
    private Slider backgroundMusicSlider;
    private Preferences prefs;
    private ToggleButton soundEffectsToggle;
    private ToggleButton backgroundMusicToggle;

    public GameSettings(Controller controller) {
        this.controller = controller;
        settingsPane = new BorderPane();

        prefs = Preferences.userNodeForPackage(GameSettings.class);
        Label settingsTitle = new Label("Settings");
        Button backButton = new Button("Return");
        Button resetToDefaultButton = new Button("Reset to Default");
        settingsTitle.getStyleClass().add("settings-title");

        Label soundEffectsLabel = new Label("Sound Effects");
        soundEffectsLabel.getStyleClass().add("music-label");
        soundEffectsLabel.getStyleClass().add("SE-btn");
        soundEffectsSlider = new Slider(0, 100, prefs.getDouble("soundEffectsVolume", 50.0));
        soundEffectsSlider.setMaxWidth(300.0);
        soundEffectsSlider.getStyleClass().add("slider");

        soundEffectsToggle = new ToggleButton();
        soundEffectsToggle.getStyleClass().add("toggle-switch");
        soundEffectsToggle.setSelected(prefs.getBoolean("soundEffectsOn", true));

        Label backgroundMusicLabel = new Label("Background Music");
        backgroundMusicLabel.getStyleClass().add("music-label");
        backgroundMusicSlider = new Slider(0, 100, prefs.getDouble("backgroundMusicVolume", 50.0));
        backgroundMusicSlider.setMaxWidth(300.0); 
        backgroundMusicSlider.getStyleClass().add("slider");

        backgroundMusicToggle = new ToggleButton();
        backgroundMusicToggle.getStyleClass().add("toggle-switch");
        backgroundMusicToggle.setSelected(prefs.getBoolean("backgroundMusicOn", true));

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

        backButton.setOnAction(e -> goBackToPreviousScene());
        settingsPane.setBottom(backButton);
        
        resetToDefaultButton.setOnAction(e -> resetToDefault());
        resetToDefaultButton.getStyleClass().add("button"); 
        resetToDefaultButton.getStyleClass().add("default-btn"); 

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