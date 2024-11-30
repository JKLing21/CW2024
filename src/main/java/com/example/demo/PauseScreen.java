package com.example.demo;

import com.example.demo.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.Group;

public class PauseScreen {

    private final Group root;
    private final Scene scene;
    private final LevelParent levelParent;
    private final Controller controller;
    private StackPane pauseMenu;

    public PauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller) {
        this.root = root;
        this.scene = scene;
        this.levelParent = levelParent;
        this.controller = controller;
        initializePauseScreen();
    }

    private void initializePauseScreen() {
        pauseMenu = new StackPane();
        pauseMenu.getStylesheets().add(getClass().getResource("/com/example/demo/css/PauseScreen.css").toExternalForm());
        pauseMenu.getStyleClass().add("pause-menu");

        Image backgroundImage = new Image(getClass().getResource("/com/example/demo/images/rectangle_container.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(430);
        backgroundImageView.setFitHeight(300); 
        backgroundImageView.setTranslateX(5);
        backgroundImageView.setTranslateY(-10);

        VBox menuContainer = createMenuContainer();
        pauseMenu.getChildren().addAll(backgroundImageView, menuContainer);

        root.getChildren().add(pauseMenu);
        pauseMenu.setVisible(false);

        pauseMenu.translateXProperty().bind(scene.widthProperty().divide(2).subtract(pauseMenu.widthProperty().divide(2)));
        pauseMenu.translateYProperty().bind(scene.heightProperty().divide(2).subtract(pauseMenu.heightProperty().divide(2)));
    }

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

    private HBox createTopButtonContainer() {
        HBox topButtonContainer = new HBox(20);
        topButtonContainer.setAlignment(Pos.CENTER);

        Image resumeImage = new Image(getClass().getResource("/com/example/demo/images/resume.png").toExternalForm());
        Image settingsImage = new Image(getClass().getResource("/com/example/demo/images/settings.png").toExternalForm());

        ImageView resumeButton = createImageButton(resumeImage, 85, 75, e -> levelParent.togglePause());
        ImageView settingsButton = createImageButton(settingsImage, 90, 75, e -> showSettings());
        
        settingsButton.getStyleClass().add("settings-button");
        
        topButtonContainer.getChildren().addAll(resumeButton, settingsButton);
        return topButtonContainer;
    }

    private HBox createBottomButtonContainer() {
        HBox bottomButtonContainer = new HBox(20);
        bottomButtonContainer.setAlignment(Pos.CENTER);

        Image mainMenuImage = new Image(getClass().getResource("/com/example/demo/images/main_menu.png").toExternalForm());
        ImageView mainMenuButton = createImageButton(mainMenuImage, 85, 75, e -> controller.goToMainMenu());

        bottomButtonContainer.getChildren().add(mainMenuButton);
        return bottomButtonContainer;
    }

    private ImageView createImageButton(Image image, double width, double height, javafx.event.EventHandler<javafx.scene.input.MouseEvent> eventHandler) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setOnMouseClicked(eventHandler);
        return imageView;
    }

    public void showPauseMenu() {
        if (pauseMenu != null) {
            pauseMenu.toFront();
            pauseMenu.setVisible(true);
        } else {
            System.out.println("pause menu is null");
        }
    }

    public void hidePauseMenu() {
        if (pauseMenu != null) {
            pauseMenu.setVisible(false);
        } else {
            System.out.println("pause menu is null");
        }
    }

    private void showSettings() {
        controller.showSettings();
    }

}