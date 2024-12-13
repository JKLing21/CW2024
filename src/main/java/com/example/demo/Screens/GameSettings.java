package com.example.demo.Screens;

import java.util.prefs.Preferences;

import com.example.demo.Controller;
import com.example.demo.Assets.AudioAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Managers.AudioManager;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
/**
 * The GameSettings class represents settings screen for the game, allowing player to configure sound effects and background music.
 * Providing controls for adjusting volume and toggling sound on/off. Settings are preserved in each game scenes using preferences.
 * 
 * <p>It's responsible for managing user interactions with the settings screen, synchronises settings with AudioManager.
 * There're also button provided to reset music settings to default and navigate back to previous scene.</p>
 * 
 * <p>Features:</p>
 * <ul>
 *   <li>Using sliders to adjust sound effects and background music volume.</li>
 *   <li>Using toggle buttons to toggle sound effects and background music on/off.</li>
 *   <li>Preserved music settings using preferences for future sessions.</li>
 *   <li>Reset music settings to default using button provided.</li>
 *   <li>Navigate back to previous scene using the provided return button.</li>
 * </ul>
 */
public class GameSettings {

	private BorderPane settingsPane;
	private Controller controller;
	private Slider soundEffectsSlider;
	private Slider backgroundMusicSlider;
	private Preferences prefs;
	private ToggleButton soundEffectsToggle;
	private ToggleButton backgroundMusicToggle;
	private AudioManager audioManager;
	private final AudioAssetLoader audioAssetLoader;
	/**
	 * Constructs GameSettings object.
	 * 
	 * <p>Constructor initialises settings screen, sets up UI components, binding user interactions to AudioManager.</p>
	 * 
	 * @param controller:          Controller responsible for managing scene transitions.
	 * @param componentsFactory:   ComponentsFactory used to create UI components.
	 * @param audioManager:        AudioManager responsible for handling game sound effects and background music.
	 */
	public GameSettings(Controller controller, ComponentsFactory componentsFactory, AudioManager audioManager) {
		prefs = Preferences.userNodeForPackage(GameSettings.class);
		this.controller = controller;
		this.audioAssetLoader = new AudioAssetLoader() {
		};
		this.audioManager = AudioManager.getInstance(audioAssetLoader);
		this.audioManager = controller.getAudioManager();

		Label settingsTitle = componentsFactory.createLabel("Settings", "settings-title");
		Button backButton = componentsFactory.createButton("Return", e -> goBackToPreviousScene());
		Button resetToDefaultButton = componentsFactory.createButton("Reset to Default", e -> resetToDefault());
		resetToDefaultButton.getStyleClass().add("button");
		resetToDefaultButton.getStyleClass().add("default-btn");

		Label soundEffectsLabel = componentsFactory.createLabel("Sound Effects", "music-label", "SE-btn");
		soundEffectsSlider = componentsFactory.createSlider(0, 100, prefs.getDouble("soundEffectsVolume", 0.5) * 100.0, 300.0);
		soundEffectsToggle = componentsFactory.createToggleButton(prefs.getBoolean("soundEffectsOn", true));
		soundEffectsToggle.getStyleClass().add("toggle-switch");

		Label backgroundMusicLabel = componentsFactory.createLabel("Background Music", "music-label");
		backgroundMusicSlider = componentsFactory.createSlider(0, 100,
				prefs.getDouble("backgroundMusicVolume", 0.5) * 100.0, 300.0);
		backgroundMusicToggle = componentsFactory.createToggleButton(prefs.getBoolean("backgroundMusicOn", true));
		backgroundMusicToggle.getStyleClass().add("toggle-switch");

		soundEffectsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (soundEffectsToggle.isSelected()) {
				double volume = newValue.doubleValue() / 100.0;
				prefs.putDouble("soundEffectsVolume", volume);
				audioManager.setSoundEffectsVolume(volume);
			}
		});

		soundEffectsToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
			prefs.putBoolean("soundEffectsOn", newValue);
			audioManager.toggleSoundEffects(newValue);
			if (!newValue) {
				soundEffectsSlider.setDisable(true);
				soundEffectsSlider.setValue(0.0);
			} else {
				soundEffectsSlider.setDisable(false);
				soundEffectsSlider.setValue(audioManager.getSoundEffectsVolume() * 100.0);
			}
		});

		backgroundMusicSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (backgroundMusicToggle.isSelected()) {
				double volume = newValue.doubleValue() / 100.0;
				prefs.putDouble("backgroundMusicVolume", volume);
				audioManager.setBackgroundMusicVolume(volume);
			}
		});

		backgroundMusicToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
			prefs.putBoolean("backgroundMusicOn", newValue);
			audioManager.toggleBackgroundMusic(newValue);
			if (!newValue) {
				backgroundMusicSlider.setDisable(true);
				backgroundMusicSlider.setValue(0.0);
			} else {
				backgroundMusicSlider.setDisable(false);
				backgroundMusicSlider.setValue(audioManager.getBackgroundMusicVolume() * 100.0);
			}
		});

		HBox titleBox = componentsFactory.createTitleBox(settingsTitle);
		HBox backButtonBox = componentsFactory.createBackButtonBox(backButton);

		HBox soundEffectsSliderBox = componentsFactory.createSliderWithToggle(soundEffectsSlider, soundEffectsToggle);
		VBox soundEffectsBox = componentsFactory.createSoundEffectsBox(new VBox(5, soundEffectsLabel, soundEffectsSliderBox));

		HBox backgroundMusicSliderBox = componentsFactory.createSliderWithToggle(backgroundMusicSlider, backgroundMusicToggle);
		VBox backgroundMusicBox = componentsFactory.createBackgroundMusicBox(new VBox(5, backgroundMusicLabel, backgroundMusicSliderBox));

		VBox soundSettingsPane = componentsFactory.createSoundSettingsLayout(soundEffectsBox, backgroundMusicBox,
				resetToDefaultButton);
		settingsPane = componentsFactory.createMainSettingsLayout(titleBox, soundSettingsPane, backButtonBox);
	}
	/**
	 * Returns settings pane that contains all the UI components for game settings.
	 * 
	 * @return Settings pane.
	 */
	public Pane getSettingsPane() {
		return settingsPane;
	}
	/**
	 * Resets sound effects and background music settings to default values.
	 * 
	 * <p>Method to reset the sliders, toggle buttons to default states.</p>
	 */
	private void resetToDefault() {
		soundEffectsSlider.setValue(50.0);
		soundEffectsToggle.setSelected(true);
		backgroundMusicSlider.setValue(50.0);
		backgroundMusicToggle.setSelected(true);

		prefs.putDouble("soundEffectsVolume", 0.5);
		prefs.putBoolean("soundEffectsOn", true);
		prefs.putDouble("backgroundMusicVolume", 0.5);
		prefs.putBoolean("backgroundMusicOn", true);

		audioManager.toggleSoundEffects(true);
		audioManager.toggleBackgroundMusic(true);
	}
	/**
	 * Navigates back to previous scene.
	 * 
	 * <p>Delegating scene navigation action to Controller class.</p>
	 */
	private void goBackToPreviousScene() {
		controller.goBack();
	}
}