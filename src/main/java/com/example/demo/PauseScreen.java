package com.example.demo;

import com.example.demo.controller.Controller;

import factories.interfaces.ComponentsFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.Group;
/**
 * Manages the pause menu UI in game.
 * It creates and displays the pause menu
 */
public class PauseScreen {

    private final Group root;
    private final Scene scene;
    private final LevelParent levelParent;
    private final Controller controller;
    private final ComponentsFactory componentsFactory;
    private StackPane pauseMenu;
    /**
     * Constructs PauseScreen object with specified root node, scene, LevelParent, and Controller.
     *
     * @param root: Root node of game scene.
     * @param scene: Game scene.
     * @param levelParent: Manages the game state.
     * @param controller: Controller for handling game logic.
     */
    public PauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller) {
        this.root = root;
        this.scene = scene;
        this.levelParent = levelParent;
        this.controller = controller;
        this.componentsFactory = levelParent.getComponentsFactory();
        initializePauseScreen();
    }
    /**
     * Initializes pause screen by creating and positioning pause menu components.
     */
    private void initializePauseScreen() {
        pauseMenu = new StackPane();
        pauseMenu.getStylesheets().add(getClass().getResource("/com/example/demo/css/PauseScreen.css").toExternalForm());
        pauseMenu.getStyleClass().add("pause-menu");

        ImageView backgroundImageView = componentsFactory.getImgViewFactory().createPauseMenuBackground(430, 300, 5, -10);

        VBox menuContainer = createMenuContainer();
        pauseMenu.getChildren().addAll(backgroundImageView, menuContainer);

        root.getChildren().add(pauseMenu);
        pauseMenu.setVisible(false);

        pauseMenu.translateXProperty().bind(scene.widthProperty().divide(2).subtract(pauseMenu.widthProperty().divide(2)));
        pauseMenu.translateYProperty().bind(scene.heightProperty().divide(2).subtract(pauseMenu.heightProperty().divide(2)));
    }
    /**
     * Creates container for pause menu, including pause text and button containers.
     *
     * @return VBox container for pause menu.
     */
    private VBox createMenuContainer() {
        VBox menuContainer = new VBox(20);
        menuContainer.setAlignment(Pos.CENTER);
        menuContainer.getStyleClass().add("menu-items");

        Text pauseText = new Text("Game Paused");
        pauseText.getStyleClass().add("pause-text");

        HBox topButtonContainer = createTopButtonContainer();
        HBox bottomButtonContainer = createBottomButtonContainer();

        menuContainer.getChildren().addAll(pauseText, topButtonContainer, bottomButtonContainer);
        return menuContainer;
    }
    /**
     * Creates top button container, including resume, settings and restart buttons.
     *
     * @return HBox container for top buttons.
     */
    private HBox createTopButtonContainer() {
        HBox topButtonContainer = new HBox(20);
        topButtonContainer.setAlignment(Pos.CENTER);

        ImageView resumeButton = componentsFactory.getImgViewFactory().createResumeButton(78, 50, e -> levelParent.togglePause());
        ImageView settingsButton = componentsFactory.getImgViewFactory().createSettingsButton(90, 75, e -> showSettings());
        ImageView restartButton = componentsFactory.getImgViewFactory().createRestartButton(95, 75, e -> levelParent.restartGame());
        settingsButton.getStyleClass().add("settings-button");

        topButtonContainer.getChildren().addAll(resumeButton, settingsButton, restartButton);
        return topButtonContainer;
    }
    /**
     * Creates bottom button container, including main menu button.
     *
     * @return HBox container for bottom button.
     */
    private HBox createBottomButtonContainer() {
        HBox bottomButtonContainer = new HBox(20);
        bottomButtonContainer.setAlignment(Pos.CENTER);

        ImageView mainMenuButton = componentsFactory.getImgViewFactory().createMainMenuButton(85, 75, e -> controller.goToMainMenu());

        bottomButtonContainer.getChildren().add(mainMenuButton);
        return bottomButtonContainer;
    }
    /**
     * Shows pause menu by bringing it to the front and making it visible.
     */
    public void showPauseMenu() {
        pauseMenu.toFront();
        pauseMenu.setVisible(true);
    }
    /**
     * Hides pause menu by making it invisible.
     */
    public void hidePauseMenu() {
        pauseMenu.setVisible(false);
    }
    /**
     * Shows settings screen by delegating to controller.
     */
    private void showSettings() {
        controller.showSettings();
    }

}