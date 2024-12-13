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
/**
 * ComponentsImplement class is responsible for creating various UI components and game elements by implementing the ComponentsFactory interface.
 */
public class ComponentsImplement implements ComponentsFactory {

	private final ImgViewFactory imgViewFactory;
	private final UIControlFactory uiControlFactory;
	private final ImgAssetLoader assetLoader;
	/**
     * Constructs new ComponentsImplement instance, initialising the ImgViewfactory, UIControLFactory, and AssetLoader.
     */
	public ComponentsImplement() {
		this.imgViewFactory = new ImgViewFactoryImpl();
		this.uiControlFactory = new UIControlFactoryImpl();
		this.assetLoader = new ImgAssetLoader() {
		};
	}
	/**
     * Creates a new health bar rectangle with the specified width, height, and fill color.
     *
     * @param width: The width of the health bar.
     * @param height: The height of the health bar.
     * @param fill: The fill color of the health bar.
     * @return new Rectangle representing the health bar.
     */
	@Override
	public Rectangle createHealthBar(double width, double height, Color fill) {
		return new Rectangle(width, height, fill);
	}
	/**
     * Creates a new health bar background rectangle with the specified width, height, and fill color.
     *
     * @param width: The width of the health bar background.
     * @param height: The height of the health bar background.
     * @param fill: The fill color of the health bar background.
     * @return new Rectangle representing the health bar background.
     */
	@Override
	public Rectangle createHealthBarBackground(double width, double height, Color fill) {
		return new Rectangle(width, height, fill);
	}
	/**
     * Creates a new Text object for displaying the boss's name with the specified font and color.
     *
     * @param name: The name of the boss.
     * @param font: The font to be applied to the text.
     * @param color: The color of the text.
     * @return new Text object representing the boss's name.
     */
	@Override
	public Text createBossNameText(String name, Font font, Color color) {
		Text bossNameText = new Text(name);
		bossNameText.setFill(color);
		bossNameText.setFont(font);
		return bossNameText;
	}
	/**
     * Creates a new Shield icon with the specified position and size.
     *
     * @param xPosition: The x-coordinate of the shield icon.
     * @param yPosition: The y-coordinate of the shield icon.
     * @param size: The size of the shield icon.
     * @return new Shield object representing the shield icon.
     */
	@Override
	public Shield createShieldIcon(double xPosition, double yPosition, double size) {
		Shield shieldIcon = new Shield(xPosition, yPosition, assetLoader);
		shieldIcon.setFitHeight(size);
		shieldIcon.setFitWidth(size);
		return shieldIcon;
	}
	/**
     * Creates a new HeartDisplay with the specified position and number of hearts to display.
     *
     * @param xPosition: The x-coordinate of the heart display.
     * @param yPosition: The y-coordinate of the heart display.
     * @param heartsToDisplay: The number of hearts to display.
     * @return A new HeartDisplay object.
     */
	@Override
	public HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		HeartDisplay heartDisplay = new HeartDisplay(xPosition, yPosition, heartsToDisplay, this);
		return heartDisplay;
	}
	/**
     * Creates a new ImageView for a heart image.
     *
     * @return A new ImageView representing the heart image.
     */
	@Override
	public ImageView createHeartImage() {
		return imgViewFactory.createImageView("heart", 40, 40);
	}
	/**
     * Creates a new LevelScreen with the specified root group, number of hearts to display, screen width, and UI layer.
     *
     * @param root: The root group where the level screen elements will be added.
     * @param heartsToDisplay: The number of hearts to display.
     * @param screenWidth: The width of the screen.
     * @param uiLayer: The group representing the UI layer.
     * @return A new LevelScreen object.
     */
	@Override
	public LevelScreen createLevelView(Group root, int heartsToDisplay, double screenWidth, Group uiLayer) {
		return new LevelScreen(root, heartsToDisplay, this, screenWidth, uiLayer);
	}
	/**
     * Creates a new MainMenu with the specified controller.
     *
     * @param controller: The controller responsible for managing game flow.
     * @return A new MainMenu object.
     */
	@Override
	public MainMenu createMainMenu(Controller controller) {
		ComponentsFactory componentsFactory = new ComponentsImplement();
		return new MainMenu(controller, componentsFactory, assetLoader);
	}
	 /**
     * Creates a new PauseScreen with the specified root group, scene, level parent, and controller.
     *
     * @param root: The root group where the pause screen elements will be added.
     * @param scene: The scene where the pause screen will be displayed.
     * @param levelParent: The parent level object.
     * @param controller: The controller responsible for managing game flow.
     * @return A new PauseScreen object.
     */
	@Override
	public PauseScreen createPauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller) {
		return new PauseScreen(root, scene, levelParent, controller);
	}
	/**
     * Creates a new Shield image with the specified position.
     *
     * @param xPosition: The x-coordinate of the shield image.
     * @param yPosition: The y-coordinate of the shield image.
     * @return A new Shield object representing the shield image.
     */
	@Override
	public Shield createShieldImage(double xPosition, double yPosition) {
		return new Shield(xPosition, yPosition, assetLoader);
	}
	/**
     * Creates a new hitbox rectangle with the specified width, height, fill color, and stroke color.
     *
     * @param width: The width of the hitbox.
     * @param height: The height of the hitbox.
     * @param color: The fill color of the hitbox.
     * @param stroke: The stroke color of the hitbox.
     * @return A new Rectangle representing the hitbox.
     */
	@Override
	public Rectangle createHitbox(double width, double height, Color color, Color stroke) {
		Rectangle hitbox = new Rectangle();
		hitbox.setWidth(width);
		hitbox.setHeight(height);
		hitbox.setFill(color);
		hitbox.setStroke(stroke);
		return hitbox;
	}
	 /**
     * Creates a new Label with the specified text and style classes.
     *
     * @param text: The text to be displayed on the label.
     * @param styleClasses: The style classes to be applied to the label.
     * @return A new Label object.
     */
	@Override
	public Label createLabel(String text, String... styleClasses) {
		return uiControlFactory.createLabel(text, styleClasses);
	}
	/**
     * Creates a new Button with the specified text and action.
     *
     * @param text: The text to be displayed on the button.
     * @param action: The action to be performed when the button is clicked.
     * @return A new Button object.
     */
	@Override
	public Button createButton(String text, EventHandler<ActionEvent> action) {
		return uiControlFactory.createButton(text, action);
	}
	/**
     * Creates a new Slider with the specified minimum, maximum, value, and width.
     *
     * @param min: The minimum value of the slider.
     * @param max: The maximum value of the slider.
     * @param value: The initial value of the slider.
     * @param width: The width of the slider.
     * @return A new Slider object.
     */
	@Override
	public Slider createSlider(double min, double max, double value, double width) {
		return uiControlFactory.createSlider(min, max, value, width);
	}
	/**
     * Creates a new ToggleButton with the specified initial selection state.
     *
     * @param selected: The initial selection state of the toggle button.
     * @return A new ToggleButton object.
     */
	@Override
	public ToggleButton createToggleButton(boolean selected) {
		return uiControlFactory.createToggleButton(selected);
	}
	/**
     * Creates a new menu Button with the specified text and action.
     *
     * @param text: The text to be displayed on the button.
     * @param action: The action to be performed when the button is clicked.
     * @return A new Button object.
     */
	@Override
	public Button createMenuButton(String text, EventHandler<ActionEvent> action) {
		return uiControlFactory.createMenuButton(text, action);
	}
	/**
     * Creates a new Text object for displaying the kill count.
     *
     * @return A new Text object representing the kill count.
     */
	@Override
	public Text createKillCountText() {
		return new Text(10, 40, "Kills: 0");
	}
	/**
     * Creates a new BorderPane layout for the main settings screen with the specified title box, sound settings pane, and back button box.
     *
     * @param titleBox: The title box to be placed at the top of the layout.
     * @param soundSettingsPane: The sound settings pane to be placed in the center of the layout.
     * @param backButtonBox: The back button box to be placed at the bottom of the layout.
     * @return A new BorderPane layout.
     */
	@Override
	public BorderPane createMainSettingsLayout(HBox titleBox, VBox soundSettingsPane, HBox backButtonBox) {
		BorderPane mainPane = new BorderPane();
		mainPane.setTop(titleBox);
		mainPane.setCenter(soundSettingsPane);
		mainPane.setBottom(backButtonBox);
		return mainPane;
	}
	/**
     * Creates a new VBox layout for the sound settings screen with the specified sound effects box, background music box, and reset to default button.
     *
     * @param soundEffectsBox: The sound effects box to be included in the layout.
     * @param backgroundMusicBox: The background music box to be included in the layout.
     * @param resetToDefaultButton: The reset to default button to be included in the layout.
     * @return A new VBox layout.
     */
	@Override
	public VBox createSoundSettingsLayout(VBox soundEffectsBox, VBox backgroundMusicBox, Button resetToDefaultButton) {
		VBox soundSettingsPane = new VBox(20, soundEffectsBox, backgroundMusicBox, resetToDefaultButton);
		soundSettingsPane.setAlignment(Pos.CENTER);
		return soundSettingsPane;
	}
	/**
     * Creates a new HBox layout for a slider with a toggle button.
     *
     * @param slider: The slider to be included in the layout.
     * @param toggleButton: The toggle button to be included in the layout.
     * @return A new HBox layout.
     */
	@Override
	public HBox createSliderWithToggle(Slider slider, ToggleButton toggleButton) {
		HBox sliderWithToggleBox = new HBox(10, slider, toggleButton);
		sliderWithToggleBox.setAlignment(Pos.CENTER);
		return sliderWithToggleBox;
	}
	/**
     * Creates a new HBox layout for the title with the specified label.
     *
     * @param label: The label to be included in the layout.
     * @return A new HBox layout.
     */
	@Override
	public HBox createTitleBox(Label label) {
		HBox titleBox = new HBox(label);
		titleBox.setAlignment(Pos.CENTER);
		return titleBox;
	}
	/**
     * Creates a new HBox layout for the back button with the specified button.
     *
     * @param button: The button to be included in the layout.
     * @return A new HBox layout.
     */
	@Override
	public HBox createBackButtonBox(Button button) {
		HBox backButtonBox = new HBox(button);
		backButtonBox.setAlignment(Pos.CENTER);
		return backButtonBox;
	}
	/**
     * Creates a new VBox layout for the sound effects settings.
     *
     * @param soundEffectsBox: The VBox containing sound effects settings.
     * @return A new VBox layout.
     */
	@Override
    public VBox createSoundEffectsBox(VBox soundEffectsBox) {
        soundEffectsBox.setAlignment(Pos.CENTER);
        return soundEffectsBox;
    }
	/**
     * Creates a new VBox layout for the background music settings.
     *
     * @param backgroundMusicBox: The VBox containing background music settings.
     * @return A new VBox layout.
     */
    @Override
    public VBox createBackgroundMusicBox(VBox backgroundMusicBox) {
        backgroundMusicBox.setAlignment(Pos.CENTER);
        return backgroundMusicBox;
    }
    /**
     * Returns the image view factory.
     *
     * @return The image view factory.
     */
	@Override
	public ImgViewFactory getImgViewFactory() {
		return imgViewFactory;
	}
}
