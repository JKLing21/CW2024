package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {
	
	private static final int SHIELD_SIZE = 50;
	private final Image shieldImage;
	
	public ShieldImage(double xPosition, double yPosition, ImgAssetLoader assetLoader) {
		this.shieldImage = assetLoader.loadAsset("Shield");
		ImageProperties.applyProperties(this, shieldImage, xPosition, yPosition, SHIELD_SIZE, (double) SHIELD_SIZE, false);
		this.setVisible(false);
	}

	public void showShield() {
		this.setVisible(true);
	}
	
	public void hideShield() {
		this.setVisible(false);
	}

}
