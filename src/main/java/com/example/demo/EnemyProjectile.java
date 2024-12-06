package com.example.demo;

import javafx.scene.image.Image;

public class EnemyProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 30;
	private static final int HORIZONTAL_VELOCITY = -10;
	private final Image userProjectileImg;

	public EnemyProjectile(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, factory);
		this.userProjectileImg = assetLoader.loadImage("enemyFire");
		ImageProperties.applyProperties(this, userProjectileImg, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
