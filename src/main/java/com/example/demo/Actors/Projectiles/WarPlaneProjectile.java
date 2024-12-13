package com.example.demo.Actors.Projectiles;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Managers.ImageProperties;

import factories.interfaces.ComponentsFactory;
import javafx.scene.image.Image;
/**
 * WarPlaneProjectile class represents projectile fired by warplane in game.
 * WarPlaneProjectile class extends Projectile class and implements specific behaviors for warplane's projectiles,
 * such as movement and image properties.
 */
public class WarPlaneProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 30;
	private static final int HORIZONTAL_VELOCITY = -15;
	private final Image warProjectileImg;
	/**
	 * Constructs new WarPlaneProjectile instance.
	 * Initialises warplane's projectile with its image and initial position.
	 *
	 * @param initialXPos: initial X position of projectile.
	 * @param initialYPos: initial Y position of projectile.
	 * @param factory: ComponentsFactory used for components creation.
	 * @param assetLoader: ImgAssetLoader used for loading images.
	 */
	public WarPlaneProjectile(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, factory);
		this.warProjectileImg = assetLoader.loadAsset("warplaneFire");
		ImageProperties.applyProperties(this, warProjectileImg, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
	}
	/**
	 * Updates position of warplane projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	/**
	 * Updates warplane projectile actor by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
