package com.example.demo.Actors.Projectiles;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Managers.ImageProperties;

import factories.interfaces.ComponentsFactory;
import javafx.scene.image.Image;

public class UserProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 6;
	private static final int HORIZONTAL_VELOCITY = 15;

	private double screenWidth;
	private final Image userProjectileImg;

	public UserProjectile(double initialXPos, double initialYPos, double screenWidth, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, factory);
		this.screenWidth = screenWidth;
		this.userProjectileImg = assetLoader.loadAsset("userfire");
		ImageProperties.applyProperties(this, userProjectileImg, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
		checkBounds();
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	private void checkBounds() {
		if (getX() > screenWidth) {
			destroy();
		}
	}

}
