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
/**
 * ComponentsFactory interface defines methods for creating various UI components and game elements.
 */
public interface ComponentsFactory {
	/**
     * Returns the image view factory.
     *
     * @return The image view factory.
     */
	ImgViewFactory getImgViewFactory();
	/**
     * Creates a new health bar rectangle with the specified width, height, and fill color.
     *
     * @param width The width of the health bar.
     * @param height The height of the health bar.
     * @param fill The fill color of the health bar.
     * @return A new Rectangle representing the health bar.
     */
	Rectangle createHealthBar(double width, double height, Color fill);
	/**
     * Creates a new health bar background rectangle with the specified width, height, and fill color.
     *
     * @param width The width of the health bar background.
     * @param height The height of the health bar background.
     * @param fill The fill color of the health bar background.
     * @return A new Rectangle representing the health bar background.
     */
	Rectangle createHealthBarBackground(double width, double height, Color fill);
	/**
     * Creates a new Text object for displaying the boss's name with the specified font and color.
     *
     * @param name The name of the boss.
     * @param font The font to be applied to the text.
     * @param color The color of the text.
     * @return A new Text object representing the boss's name.
     */
	Text createBossNameText(String name, Font font, Color color);
	/**
     * Creates a new Shield icon with the specified position and size.
     *
     * @param xPosition The x-coordinate of the shield icon.
     * @param yPosition The y-coordinate of the shield icon.
     * @param size The size of the shield icon.
     * @return A new Shield object representing the shield icon.
     */
	Shield createShieldIcon(double xPosition, double yPosition, double size);
	/**
     * Creates a new HeartDisplay with the specified position and number of hearts to display.
     *
     * @param xPosition The x-coordinate of the heart display.
     * @param yPosition The y-coordinate of the heart display.
     * @param heartsToDisplay The number of hearts to display.
     * @return A new HeartDisplay object.
     */
	HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay);
	/**
     * Creates a new ImageView for a heart image.
     *
     * @return A new ImageView representing the heart image.
     */
	ImageView createHeartImage();
	/**
     * Creates a new LevelScreen with the specified root group, number of hearts to display, screen width, and UI layer.
     *
     * @param root The root group where the level screen elements will be added.
     * @param heartsToDisplay The number of hearts to display.
     * @param screenWidth The width of the screen.
     * @param uiLayer The group representing the UI layer.
     * @return A new LevelScreen object.
     */
	LevelScreen createLevelView(Group root, int heartsToDisplay, double screenWidth, Group uiLayer);
	/**
     * Creates a new MainMenu with the specified controller.
     *
     * @param controller The controller responsible for managing game flow.
     * @return A new MainMenu object.
     */
	MainMenu createMainMenu(Controller controller);
	/**
     * Creates a new PauseScreen with the specified root group, scene, level parent, and controller.
     *
     * @param root The root group where the pause screen elements will be added.
     * @param scene The scene where the pause screen will be displayed.
     * @param levelParent The parent level object.
     * @param controller The controller responsible for managing game flow.
     * @return A new PauseScreen object.
     */
	PauseScreen createPauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller);
	/**
     * Creates a new Shield image with the specified position.
     *
     * @param xPosition The x-coordinate of the shield image.
     * @param yPosition The y-coordinate of the shield image.
     * @return A new Shield object representing the shield image.
     */
	Shield createShieldImage(double xPosition, double yPosition);
	/**
     * Creates a new hitbox rectangle with the specified width, height, fill color, and stroke color.
     *
     * @param width The width of the hitbox.
     * @param height The height of the hitbox.
     * @param color The fill color of the hitbox.
     * @param stroke The stroke color of the hitbox.
     * @return A new Rectangle representing the hitbox.
     */
	Rectangle createHitbox(double width, double height, Color color, Color stroke);
	/**
     * Creates a new Label with the specified text and style classes.
     *
     * @param text The text to be displayed on the label.
     * @param styleClasses The style classes to be applied to the label.
     * @return A new Label object.
     */
	Label createLabel(String text, String... styleClasses);
	/**
     * Creates a new Button with the specified text and action.
     *
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A new Button object.
     */
	Button createButton(String text, EventHandler<ActionEvent> action);
	/**
     * Creates a new Slider with the specified minimum, maximum, value, and width.
     *
     * @param min The minimum value of the slider.
     * @param max The maximum value of the slider.
     * @param value The initial value of the slider.
     * @param width The width of the slider.
     * @return A new Slider object.
     */
	Slider createSlider(double min, double max, double value, double width);
	 /**
     * Creates a new ToggleButton with the specified initial selection state.
     *
     * @param selected The initial selection state of the toggle button.
     * @return A new ToggleButton object.
     */
	ToggleButton createToggleButton(boolean selected);
	/**
     * Creates a new menu Button with the specified text and action.
     *
     * @param text The text to be displayed on the button.
     * @param action The action to be performed when the button is clicked.
     * @return A new Button object.
     */
	Button createMenuButton(String text, EventHandler<ActionEvent> action);
	/**
     * Creates a new Text object for displaying the kill count.
     *
     * @return A new Text object representing the kill count.
     */
	Text createKillCountText();
	/**
     * Creates a new BorderPane layout for the main settings screen with the specified title box, sound settings pane, and back button box.
     *
     * @param titleBox The title box to be placed at the top of the layout.
     * @param soundSettingsPane The sound settings pane to be placed in the center of the layout.
     * @param backButtonBox The back button box to be placed at the bottom of the layout.
     * @return A new BorderPane layout.
     */
	BorderPane createMainSettingsLayout(HBox titleBox, VBox soundSettingsPane, HBox backButtonBox);
	/**
     * Creates a new VBox layout for the sound settings screen with the specified sound effects box, background music box, and reset to default button.
     *
     * @param soundEffectsBox The sound effects box to be included in the layout.
     * @param backgroundMusicBox The background music box to be included in the layout.
     * @param resetToDefaultButton The reset to default button to be included in the layout.
     * @return A new VBox layout.
     */
	VBox createSoundSettingsLayout(VBox soundEffectsBox, VBox backgroundMusicBox, Button resetToDefaultButton);
	/**
     * Creates a new HBox layout for a slider with a toggle button.
     *
     * @param slider The slider to be included in the layout.
     * @param toggleButton The toggle button to be included in the layout.
     * @return A new HBox layout.
     */
	HBox createSliderWithToggle(Slider slider, ToggleButton toggleButton);
	/**
     * Creates a new HBox layout for the title with the specified label.
     *
     * @param label The label to be included in the layout.
     * @return A new HBox layout.
     */
	HBox createTitleBox(Label label);
	/**
     * Creates a new VBox layout for the sound effects settings.
     *
     * @param soundEffectsBox The VBox containing sound effects settings.
     * @return A new VBox layout.
     */
	VBox createSoundEffectsBox(VBox soundEffectsBox);
	 /**
     * Creates a new VBox layout for the background music settings.
     *
     * @param backgroundMusicBox The VBox containing background music settings.
     * @return A new VBox layout.
     */
    VBox createBackgroundMusicBox(VBox backgroundMusicBox);
    /**
     * Creates a new HBox layout for the back button with the specified button.
     *
     * @param button The button to be included in the layout.
     * @return A new HBox layout.
     */
	HBox createBackButtonBox(Button button);
}
