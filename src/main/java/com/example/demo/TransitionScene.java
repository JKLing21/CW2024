package com.example.demo;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionScene {

    private final Stage stage;
    private final Scene transitionScene;
    private final Text transitionText;
    private final ImageView planeImageView;

    public TransitionScene(Stage stage, String message, double width, double height) {
        this.stage = stage;

        transitionText = new Text(message);
        transitionText.setFont(Font.font("Arial", 55));
        transitionText.setFill(Color.RED);

        Image planeImage = new Image(getClass().getResource("/com/example/demo/images/userplane.png").toExternalForm());
        planeImageView = new ImageView(planeImage);
        planeImageView.setFitWidth(120); 
        planeImageView.setFitHeight(30);

        Pane transitionPane = new Pane(transitionText, planeImageView);
        transitionPane.setStyle("-fx-background-color: white;");
        this.transitionScene = new Scene(transitionPane, width, height);

        transitionText.setLayoutX(width / 2 - transitionText.getLayoutBounds().getWidth() / 2);
        transitionText.setLayoutY(height / 2); 
        planeImageView.setLayoutY(height - planeImageView.getFitHeight() - 100); 
        planeImageView.setLayoutX(-planeImageView.getFitWidth());
    }

    public void showTransition(Runnable onTransitionEnd) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), transitionText);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(4);
        fadeIn.setAutoReverse(true);

        TranslateTransition planeAnimation = new TranslateTransition(Duration.seconds(3), planeImageView);
        planeAnimation.setFromX(-planeImageView.getFitWidth());
        
        planeAnimation.setToX(transitionScene.getWidth() + planeImageView.getFitWidth());

        planeAnimation.setCycleCount(1);
        planeAnimation.setAutoReverse(false);

        stage.setScene(transitionScene);

        fadeIn.play();
        planeAnimation.play();
        planeAnimation.setOnFinished(event -> onTransitionEnd.run());
    }

    public Scene getTransitionScene() {
        return this.transitionScene;
    }

    public static void fadeOutCurrentScene(Stage stage, Runnable callback) {
        Scene currentScene = stage.getScene();
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), currentScene.getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(event -> callback.run());
        fadeOut.play();
    }
}
