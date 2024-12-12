package com.example.demo.Screens;

import factories.interfaces.ComponentsFactory;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionScene {

    private final Stage stage;
    private final Scene transitionScene;
    private final Text transitionText;
    private final ImageView planeImageView;

    public TransitionScene(Stage stage, String message, double width, double height,  ComponentsFactory componentsFactory) {
        this.stage = stage;

        transitionText = new Text(message);
        transitionText.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        transitionText.setFill(Color.RED);

        planeImageView = componentsFactory.getImgViewFactory().createPlaneImageView(120, 30);

        ImageView backgroundImageView = componentsFactory.getImgViewFactory().createBackgroundImageView(width, height);

        Pane transitionPane = new Pane(backgroundImageView, transitionText, planeImageView);
        this.transitionScene = new Scene(transitionPane, width, height);

        transitionText.setLayoutX(width / 2 - transitionText.getLayoutBounds().getWidth() / 2);
        transitionText.setLayoutY(height / 2); 
        planeImageView.setLayoutY(height - planeImageView.getFitHeight() - 100); 
        planeImageView.setLayoutX(-planeImageView.getFitWidth());
    }

    public void showTransition(Runnable onTransitionEnd) {

        FadeTransition fadeInText = new FadeTransition(Duration.seconds(2.5), transitionText);
        fadeInText.setFromValue(0);
        fadeInText.setToValue(1);
        fadeInText.setCycleCount(1);
        fadeInText.setAutoReverse(true);

        TranslateTransition planeAnimation = new TranslateTransition(Duration.seconds(2.5), planeImageView);
        planeAnimation.setFromX(-planeImageView.getFitWidth());
        planeAnimation.setToX(transitionScene.getWidth() + planeImageView.getFitWidth());
        planeAnimation.setCycleCount(1);
        planeAnimation.setAutoReverse(false);

        ParallelTransition parallelTransition = new ParallelTransition(fadeInText, planeAnimation);
        parallelTransition.setOnFinished(event -> onTransitionEnd.run());

        stage.setScene(transitionScene);
        parallelTransition.play();
    }

    public Scene getTransitionScene() {
        return this.transitionScene;
    }

    public static void fadeOutCurrentScene(Stage stage, Runnable callback) {
        Scene currentScene = stage.getScene();
        if (currentScene != null) {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), currentScene.getRoot());
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(event -> {
                stage.setScene(null);
                callback.run();
            });
            fadeOut.play();
        } else {
            callback.run();
        }
    }
}
