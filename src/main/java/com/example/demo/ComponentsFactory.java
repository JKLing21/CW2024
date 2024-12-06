package com.example.demo;

import com.example.demo.controller.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public interface ComponentsFactory {
	Rectangle createHealthBar(double width, double height, Color fill);

	Rectangle createHealthBarBackground(double width, double height, Color fill);

	Text createBossNameText(String name, Font font, Color color);

	ShieldImage createShieldIcon(double xPosition, double yPosition, double size);

	GameOverImage createGameOverImage(double xPosition, double yPosition);

	HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay);

	ImageView createHeartImage();

	LevelView createLevelView(Group root, int heartsToDisplay);

	MainMenu createMainMenu(Controller controller);

	PauseScreen createPauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller);

	ShieldImage createShieldImage(double xPosition, double yPosition);

	WinImage createWinImage(double xPosition, double yPosition);

	ImageView createPauseButton(double xPosition, double yPosition, double width, double height,
            EventHandler<MouseEvent> action);

	Rectangle createHitbox(double width, double height, Color color, Color stroke);
	
	Label createLabel(String text, String... styleClasses);

    Button createButton(String text, EventHandler<ActionEvent> action);

    Slider createSlider(double min, double max, double value, double width);

    ToggleButton createToggleButton(boolean selected);

	Button createMenuButton(String text, EventHandler<ActionEvent> action);
	
	ImageView createResumeButton(double width, double height, EventHandler<MouseEvent> action);
	
    ImageView createSettingsButton(double width, double height, EventHandler<MouseEvent> action);
    
	ImageView createMainMenuButton(double width, double height, EventHandler<MouseEvent> action);
	
	ImageView createPauseMenuBackground(double width, double height, double translateX, double translateY);
	
	ImageView createPlaneImageView(double width, double height);
	
    ImageView createBackgroundImageView(double width, double height);

	Text createKillCountText();
}
