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

public class LevelScreen {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;

    private final Group root;
    private final double screenWidth;
    private final HeartDisplay heartDisplay;
    private final StringProperty killCountString = new SimpleStringProperty("");
    private final Group uiLayer;
    private Text killCountText;

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

    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

    public void addHitboxesToScene(Group root, ActiveActor... actors) {
        for (ActiveActor actor : actors) {
            Rectangle hitbox = actor.getHitbox();
            if (!root.getChildren().contains(hitbox)) {
                root.getChildren().add(hitbox);
            }
        }
    }

    public void removeHitboxesFromScene(ActiveActor... actors) {
        for (ActiveActor actor : actors) {
            actor.removeHitboxFromScene(root);
        }
    }

    public void updateKillCount(int currentKills, int killTarget) {
        int remainingKills = killTarget - currentKills;
        killCountString.set("Remaining kills to advance: " + remainingKills);
    }

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


    public void showKillCountText() {
        if (!uiLayer.getChildren().contains(killCountText)) {
            uiLayer.getChildren().add(killCountText);
        }
    }

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

    private Text createInstructionText(String text, double x, double y) {
        Text instructionText = new Text(text);
        instructionText.setStyle("-fx-font-size: 30px; -fx-fill: white;");
        instructionText.setOpacity(0);
        instructionText.setLayoutX(x);
        instructionText.setLayoutY(y);
        return instructionText;
    }

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
