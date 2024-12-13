package com.example.demo.Factories;

import com.example.demo.Controller;
import com.example.demo.Actors.Components.HeartDisplay;
import com.example.demo.Actors.Components.Shield;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Factories.Interfaces.ImgViewFactory;
import com.example.demo.Factories.Interfaces.UIControlFactory;
import com.example.demo.Levels.LevelParent;
import com.example.demo.Screens.LevelScreen;
import com.example.demo.Screens.MainMenu;
import com.example.demo.Screens.PauseScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

public class ComponentsImplement implements ComponentsFactory {

	private final ImgViewFactory imgViewFactory;
	private final UIControlFactory uiControlFactory;
	private final ImgAssetLoader assetLoader;

	public ComponentsImplement() {
		this.imgViewFactory = new ImgViewFactoryImpl();
		this.uiControlFactory = new UIControlFactoryImpl();
		this.assetLoader = new ImgAssetLoader() {
		};
	}

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
	public Shield createShieldIcon(double xPosition, double yPosition, double size) {
		Shield shieldIcon = new Shield(xPosition, yPosition, assetLoader);
		shieldIcon.setFitHeight(size);
		shieldIcon.setFitWidth(size);
		return shieldIcon;
	}

	@Override
	public HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		HeartDisplay heartDisplay = new HeartDisplay(xPosition, yPosition, heartsToDisplay, this);
		return heartDisplay;
	}

	@Override
	public ImageView createHeartImage() {
		return imgViewFactory.createImageView("heart", 40, 40);
	}

	@Override
	public LevelScreen createLevelView(Group root, int heartsToDisplay, double screenWidth, Group uiLayer) {
		return new LevelScreen(root, heartsToDisplay, this, screenWidth, uiLayer);
	}

	@Override
	public MainMenu createMainMenu(Controller controller) {
		ComponentsFactory componentsFactory = new ComponentsImplement();
		return new MainMenu(controller, componentsFactory, assetLoader);
	}

	@Override
	public PauseScreen createPauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller) {
		return new PauseScreen(root, scene, levelParent, controller);
	}

	@Override
	public Shield createShieldImage(double xPosition, double yPosition) {
		return new Shield(xPosition, yPosition, assetLoader);
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
		return uiControlFactory.createLabel(text, styleClasses);
	}

	@Override
	public Button createButton(String text, EventHandler<ActionEvent> action) {
		return uiControlFactory.createButton(text, action);
	}

	@Override
	public Slider createSlider(double min, double max, double value, double width) {
		return uiControlFactory.createSlider(min, max, value, width);
	}

	@Override
	public ToggleButton createToggleButton(boolean selected) {
		return uiControlFactory.createToggleButton(selected);
	}

	@Override
	public Button createMenuButton(String text, EventHandler<ActionEvent> action) {
		return uiControlFactory.createMenuButton(text, action);
	}

	@Override
	public Text createKillCountText() {
		return new Text(10, 40, "Kills: 0");
	}

	@Override
	public BorderPane createMainSettingsLayout(HBox titleBox, VBox soundSettingsPane, HBox backButtonBox) {
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(titleBox);
		mainPane.setCenter(soundSettingsPane);
		mainPane.setBottom(backButtonBox);
		return mainPane;
	}

	@Override
	public VBox createSoundSettingsLayout(VBox soundEffectsBox, VBox backgroundMusicBox, Button resetToDefaultButton) {
		VBox soundSettingsPane = new VBox(20, soundEffectsBox, backgroundMusicBox, resetToDefaultButton);
		soundSettingsPane.setAlignment(Pos.CENTER);
		return soundSettingsPane;
	}

	@Override
	public HBox createSliderWithToggle(Slider slider, ToggleButton toggleButton) {
		HBox sliderWithToggleBox = new HBox(10, slider, toggleButton);
		sliderWithToggleBox.setAlignment(Pos.CENTER);
		return sliderWithToggleBox;
	}

	@Override
	public HBox createTitleBox(Label label) {
		HBox titleBox = new HBox(label);
		titleBox.setAlignment(Pos.CENTER);
		return titleBox;
	}

	@Override
	public HBox createBackButtonBox(Button button) {
		HBox backButtonBox = new HBox(button);
		backButtonBox.setAlignment(Pos.CENTER);
		return backButtonBox;
	}
	
	@Override
    public VBox createSoundEffectsBox(VBox soundEffectsBox) {
        soundEffectsBox.setAlignment(Pos.CENTER);
        return soundEffectsBox;
    }

    @Override
    public VBox createBackgroundMusicBox(VBox backgroundMusicBox) {
        backgroundMusicBox.setAlignment(Pos.CENTER);
        return backgroundMusicBox;
    }

	@Override
	public ImgViewFactory getImgViewFactory() {
		return imgViewFactory;
	}
}
