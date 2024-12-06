package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {

	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;
	private final Image winImage;
	
	public WinImage(double xPosition, double yPosition, ImgAssetLoader assetLoader) {
		this.winImage = assetLoader.loadImage("youwin");
		setImage(winImage);
		this.setVisible(false);
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}
	
	public void showWinImage() {
		this.setVisible(true);
	}

}
