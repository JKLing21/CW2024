package com.example.demo.Factories.Interfaces;

import com.example.demo.Controller;
import com.example.demo.Actors.Components.HeartDisplay;
import com.example.demo.Actors.Components.Shield;
import com.example.demo.Levels.LevelParent;
import com.example.demo.Screens.LevelScreen;
import com.example.demo.Screens.MainMenu;
import com.example.demo.Screens.PauseScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public interface ComponentsFactory {

	ImgViewFactory getImgViewFactory();

	Rectangle createHealthBar(double width, double height, Color fill);

	Rectangle createHealthBarBackground(double width, double height, Color fill);

	Text createBossNameText(String name, Font font, Color color);

	Shield createShieldIcon(double xPosition, double yPosition, double size);

	HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay);

	ImageView createHeartImage();

	LevelScreen createLevelView(Group root, int heartsToDisplay, double screenWidth, Group uiLayer);

	MainMenu createMainMenu(Controller controller);

	PauseScreen createPauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller);

	Shield createShieldImage(double xPosition, double yPosition);

	Rectangle createHitbox(double width, double height, Color color, Color stroke);

	Label createLabel(String text, String... styleClasses);

	Button createButton(String text, EventHandler<ActionEvent> action);

	Slider createSlider(double min, double max, double value, double width);

	ToggleButton createToggleButton(boolean selected);

	Button createMenuButton(String text, EventHandler<ActionEvent> action);

	Text createKillCountText();

	BorderPane createMainSettingsLayout(HBox titleBox, VBox soundSettingsPane, HBox backButtonBox);

	VBox createSoundSettingsLayout(VBox soundEffectsBox, VBox backgroundMusicBox, Button resetToDefaultButton);

	HBox createSliderWithToggle(Slider slider, ToggleButton toggleButton);

	HBox createTitleBox(Label label);
	
	VBox createSoundEffectsBox(VBox soundEffectsBox);

    VBox createBackgroundMusicBox(VBox backgroundMusicBox);

	HBox createBackButtonBox(Button button);
}
