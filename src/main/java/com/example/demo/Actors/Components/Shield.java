package com.example.demo.Actors.Components;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.utils.ImageProperties;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Shield class represents the shield icon which can be displayed or hidden in game scene.
 * It extends ImageView class and it's used to visually represent the shield icon.
 */
public class Shield extends ImageView {
	
	private static final int SHIELD_SIZE = 50;
	private final Image shieldImage;
	/**
	 * Constructs Shield object with specified position and ImgAssetLoader.
	 * The shield is initially hidden.
	 *
	 * @param xPosition: x-coordinate of shield icon's position.
	 * @param yPosition: y-coordinate of shield icon's position.
	 * @param assetLoader: ImgAssetLoader used for loading shield image.
	 */
	public Shield(double xPosition, double yPosition, ImgAssetLoader assetLoader) {
		this.shieldImage = assetLoader.loadAsset("Shield");
		ImageProperties.applyProperties(this, shieldImage, xPosition, yPosition, SHIELD_SIZE, (double) SHIELD_SIZE, false);
		this.setVisible(false);
	}
	/**
	 * Makes shield icon visible.
	 */
	public void showShield() {
		this.setVisible(true);
	}
	/**
	 * Hides shield icon by making it invisible.
	 */
	public void hideShield() {
		this.setVisible(false);
	}
}
