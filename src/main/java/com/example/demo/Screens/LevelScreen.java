package com.example.demo.Screens;

import factories.interfaces.ComponentsFactory;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Actors.ActiveActor;
import com.example.demo.Actors.Components.HeartDisplay;
/**
 * LevelScreen class manages UI elements and interactions across game levels.
 */
public class LevelScreen {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;

    private final Group root;
    private final double screenWidth;
    private final HeartDisplay heartDisplay;
    private final StringProperty killCountString = new SimpleStringProperty("");
    private final Group uiLayer;
    private Text killCountText;
    /**
     * Constructs new LevelScreen instance.
     *
     * @param root: root group where level screen elements will be added.
     * @param heartsToDisplay: number of hearts to display at the start of game.
     * @param componentsFactory: ComponentsFactory used for components creation.
     * @param screenWidth: width of screen.
     * @param uiLayer: the group representing UI layer.
     * @throws IllegalArgumentException if UI Layer is null.
     */
    public LevelScreen(Group root, int heartsToDisplay, ComponentsFactory componentsFactory, double screenWidth, Group uiLayer) {
        if (uiLayer == null) {
            throw new IllegalArgumentException("UI Layer cannot be null");
        }
        this.root = root;
        this.screenWidth = screenWidth;
        this.uiLayer = uiLayer;
        this.heartDisplay = componentsFactory.createHeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.killCountText = componentsFactory.createKillCountText();
        this.killCountText.textProperty().bind(killCountString);

        positionKillCountText();
    }
    /**
     * Adds heart display to root group.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }
    /**
     * Removes hearts from display based on remaining hearts.
     *
     * @param heartsRemaining: number of hearts remaining to be displayed.
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }
    /**
     * Adds hitbox of given actors to root group.
     *
     * @param root: root group where hitboxes will be added.
     * @param actors: actors whose hitbox need to be added.
     */
    public void addHitboxesToScene(Group root, ActiveActor... actors) {
        for (ActiveActor actor : actors) {
            Rectangle hitbox = actor.getHitbox();
            if (!root.getChildren().contains(hitbox)) {
                root.getChildren().add(hitbox);
            }
        }
    }
    /**
     * Removes hitbox of given actors from root group.
     *
     * @param actors: actors whose hitbox need to be removed.
     */
    public void removeHitboxesFromScene(ActiveActor... actors) {
        for (ActiveActor actor : actors) {
            actor.removeHitboxFromScene(root);
        }
    }
    /**
     * Updates kill count text based on player's current kills and target kills.
     *
     * @param currentKills: player's current number of kills.
     * @param killTarget: target number of kills to reach.
     */
    public void updateKillCount(int currentKills, int killTarget) {
        int remainingKills = killTarget - currentKills;
        killCountString.set("Remaining kills to advance: " + remainingKills);
    }
    /**
     * Positions kill count text on screen.
     */
    private void positionKillCountText() {
        if (killCountText != null) {
            double centerX = (screenWidth - 830);
            double topY = 10;

            killCountText.setStyle("-fx-font-size: 28px; -fx-fill: white;");
            killCountText.setLayoutX(centerX);
            killCountText.setLayoutY(topY);

            if (!uiLayer.getChildren().contains(killCountText)) {
                uiLayer.getChildren().add(killCountText);
            }
        }
    }
    /**
     * Adds kill count text to UI layer if it's not already present.
     */
    public void showKillCountText() {
        if (!uiLayer.getChildren().contains(killCountText)) {
            uiLayer.getChildren().add(killCountText);
        }
    }
    /**
     * Displays instructions on screen with fade in and fade out effects.
     */
    public void showInstructions() {
        List<Text> instructionTexts = new ArrayList<>();
        instructionTexts.add(createInstructionText("Press UP and DOWN arrow keys to move", 400, 110));
        instructionTexts.add(createInstructionText("Press SPACE to fire", 530, 150));

        instructionTexts.forEach(text -> {
            uiLayer.getChildren().add(text);
            text.toFront();
            applyFadeTransition(text);
        });
    }
    /**
     * Creates new instruction text with given content and position.
     *
     * @param text: text content of instruction.
     * @param x: x-coordinate of text.
     * @param y: y-coordinate of text.
     * @return created Text object.
     */
    private Text createInstructionText(String text, double x, double y) {
        Text instructionText = new Text(text);
        instructionText.setStyle("-fx-font-size: 30px; -fx-fill: white;");
        instructionText.setOpacity(0);
        instructionText.setLayoutX(x);
        instructionText.setLayoutY(y);
        return instructionText;
    }
    /**
     * Applies fade in and fade out transition to given text.
     *
     * @param text: text to which the transition will be applied.
     */
    private void applyFadeTransition(Text text) {
        if (text == null) {
            System.err.println("Error: Text object is null in applyFadeTransition.");
            return;
        }

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), text);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), text);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> fadeOut.play());

        fadeIn.setOnFinished(e -> pause.play());
        fadeIn.play();
    }
}
