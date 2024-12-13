package com.example.demo.Screens;

import com.example.demo.Controller;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
/**
 * MainMenu class displays the main menu of the game.
 */
public class MainMenu {

    private StackPane root;
    private Controller controller;
    private final Image MainMenuImage;
    /**
     * Constructs new MainMenu instance.
     *
     * @param controller: controller which manages the game flow.
     * @param componentsFactory: ComponentsFactory used for UI components creation.
     * @param assetLoader: ImgAssetLoader which responsible for loading images.
     */
    public MainMenu(Controller controller, ComponentsFactory componentsFactory, ImgAssetLoader assetLoader) {
        this.controller = controller;
        this.MainMenuImage = assetLoader.loadAsset("MainMenu");
        if (MainMenuImage == null) {
            System.out.println("Background image not found.");
            return;
        }
        // Create Start Game button
		Button startButton = componentsFactory.createMenuButton("Start Game", e -> {
            try {
                this.controller.launchGame();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
		// Create Settings button
		Button settingsButton = componentsFactory.createMenuButton("Settings", e -> this.controller.showSettings());
		Button exitButton = componentsFactory.createMenuButton("Quit", e -> showQuitConfirmation());
		// Create VBox to hold menu buttons
        VBox menuPane = new VBox(20);
        menuPane.setAlignment(Pos.BOTTOM_CENTER);
        menuPane.getChildren().addAll(startButton, settingsButton, exitButton);
        menuPane.getStyleClass().add("menu-pane"); 
        VBox.setVgrow(menuPane, Priority.ALWAYS);
        menuPane.setTranslateY(-15);
        // Create root StackPane to hold background image and menu buttons
        root = new StackPane();
        // Create ImageView for main menu background image
        ImageView background = new ImageView(MainMenuImage);
        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());
        background.setPreserveRatio(false);
        // Add background image and menu buttons to root
        root.getChildren().addAll(background, menuPane);
    }
    /**
     * Displays confirmation dialog to confirm if player is quitting the game.
     */
    private void showQuitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
        alert.setHeaderText("Are you sure you want to quit the game?");

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/com/example/demo/css/MainMenu.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-dialog-pane");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        dialogPane.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                System.exit(0);
            }
        });
    }
    /**
     * Returns root pane of main menu.
     *
     * @return root pane containing main menu UI.
     */
    public Pane getMenuPane() {
        return root;
    }
}