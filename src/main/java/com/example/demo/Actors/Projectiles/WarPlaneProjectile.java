package com.example.demo.Actors.Projectiles;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Managers.ImageProperties;

import factories.interfaces.ComponentsFactory;
import javafx.scene.image.Image;

public class WarPlaneProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 30;
	private static final int HORIZONTAL_VELOCITY = -15;
	private final Image warProjectileImg;

	public WarPlaneProjectile(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, factory);
		this.warProjectileImg = assetLoader.loadAsset("warplaneFire");
		ImageProperties.applyProperties(this, warProjectileImg, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
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
