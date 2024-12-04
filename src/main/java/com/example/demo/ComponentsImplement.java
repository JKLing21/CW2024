package com.example.demo;

import java.util.Objects;

import com.example.demo.controller.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ComponentsImplement implements ComponentsFactory {
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	@Override
	public Rectangle createHealthBar(double width, double height, Color fill) {
		return new Rectangle(width, height, fill);
	}

	@Override
	public Rectangle createHealthBarBackground(double width, double height, Color fill) {
		return new Rectangle(width, height, fill);
	}

	@Override
	public Text createBossNameText(String name, Font font, Color color) {
	    Text bossNameText = new Text(name);
	    bossNameText.setFill(color);
	    bossNameText.setFont(font);
	    return bossNameText;
	}

	@Override
	public ShieldImage createShieldIcon(double xPosition, double yPosition, double size) {
		ShieldImage shieldIcon = new ShieldImage(xPosition, yPosition);
		shieldIcon.setFitHeight(size);
		shieldIcon.setFitWidth(size);
		return shieldIcon;
	}

	@Override
	public GameOverImage createGameOverImage(double xPosition, double yPosition) {
		return new GameOverImage(xPosition, yPosition);
	}

	@Override
	public HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		HeartDisplay heartDisplay = new HeartDisplay(xPosition, yPosition, heartsToDisplay, this);
		return heartDisplay;
	}

	@Override
	public ImageView createHeartImage() {
		return new ImageView(
				new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));
	}

	@Override
	public LevelView createLevelView(Group root, int heartsToDisplay) {
		return new LevelView(root, heartsToDisplay, this);
	}

	@Override
	public MainMenu createMainMenu(Controller controller) {
	    ComponentsFactory componentsFactory = new ComponentsImplement();
	    return new MainMenu(controller, componentsFactory);
	}

	@Override
	public PauseScreen createPauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller) {
		return new PauseScreen(root, scene, levelParent, controller);
	}

	@Override
	public ShieldImage createShieldImage(double xPosition, double yPosition) {
		return new ShieldImage(xPosition, yPosition);
	}

	@Override
	public WinImage createWinImage(double xPosition, double yPosition) {
		return new WinImage(xPosition, yPosition);
	}

	@Override
	public ImageView createPauseButton(double xPosition, double yPosition, double width, double height,
			String imagePath, EventHandler<MouseEvent> action) {
		ImageView pauseButton = new ImageView(
				new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
		pauseButton.setFitWidth(width);
		pauseButton.setFitHeight(height);
		pauseButton.setLayoutX(xPosition);
		pauseButton.setLayoutY(yPosition);
		pauseButton.setOnMouseClicked(action);
		return pauseButton;
	}

	@Override
	public Rectangle createHitbox(double width, double height, Color color, Color stroke) {
		Rectangle hitbox = new Rectangle();
		hitbox.setWidth(width);
		hitbox.setHeight(height);
		hitbox.setFill(color);
		hitbox.setStroke(stroke);
		return hitbox;
	}
	
	@Override
	public Label createLabel(String text, String... styleClasses) {
	    Label label = new Label(text);
	    label.getStyleClass().addAll(styleClasses);
	    return label;
	}

	@Override
	public Button createButton(String text, EventHandler<ActionEvent> action) {
	    Button button = new Button(text);
	    button.setOnAction(action);
	    return button;
	}

	@Override
	public Slider createSlider(double min, double max, double value, double width) {
	    Slider slider = new Slider(min, max, value);
	    slider.setPrefWidth(width);
	    return slider;
	}

	@Override
	public ToggleButton createToggleButton(boolean selected) {
	    ToggleButton toggleButton = new ToggleButton();
	    toggleButton.setSelected(selected);
	    return toggleButton;
	}

	@Override
	public Button createMenuButton(String text, EventHandler<ActionEvent> action) {
		Button button = new Button(text);
		button.setOnAction(action);
		button.getStyleClass().add("menu-button");
		return button;
	}

	@Override
	public Text createKillCountText() {
		return new Text(10, 40, "Kills: 0");
	}
}
