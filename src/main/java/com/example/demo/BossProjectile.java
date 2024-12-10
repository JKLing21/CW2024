package com.example.demo;

import factories.interfaces.ComponentsFactory;
import javafx.scene.image.Image;

public class BossProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 845;
	private final Image bossProjectileImg;

	public BossProjectile(double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, factory);
		this.bossProjectileImg = assetLoader.loadImage("fireball");
		ImageProperties.applyProperties(this, bossProjectileImg, (double) INITIAL_X_POSITION, initialYPos, IMAGE_HEIGHT, null, true);
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
