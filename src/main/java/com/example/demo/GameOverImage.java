package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class GameOverImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";
	private static final double GAME_OVER_IMAGE_WIDTH = 1180;
    private static final double GAME_OVER_IMAGE_HEIGHT = 560;

	public GameOverImage(double xPosition, double yPosition) {
        setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        setFitWidth(GAME_OVER_IMAGE_WIDTH);
        setFitHeight(GAME_OVER_IMAGE_HEIGHT);
        setLayoutX(xPosition);
        setLayoutY(yPosition);
    }
	 
	 public void centerImage(double sceneWidth, double sceneHeight) {
	        double xPosition = (sceneWidth - GAME_OVER_IMAGE_WIDTH) / 2;
	        double yPosition = (sceneHeight - GAME_OVER_IMAGE_HEIGHT) / 2;
	        setLayoutX(xPosition);
	        setLayoutY(yPosition);
	    }

}
