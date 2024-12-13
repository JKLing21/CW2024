package com.example.demo.Actors.Projectiles;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.utils.ImageProperties;

import javafx.scene.image.Image;
/**
 * BossProjectile class represents projectile fired by bossplane in game.
 * BossProjectile class extends Projectile class and implements specific behaviors for boss's projectile,
 * such as movement and image properties.
 */
public class BossProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 845;
	private final Image bossProjectileImg;
	/**
	 * Constructs new BossProjectile instance.
	 * Initialises boss's projectile with its image and initial position.
	 *
	 * @param initialYPos: initial Y position of projectile.
	 * @param factory: ComponentsFactory used for components creation.
	 * @param assetLoader: ImgAssetLoader used for loading images.
	 */
	public BossProjectile(double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos, factory);
		this.bossProjectileImg = assetLoader.loadAsset("fireball");
		ImageProperties.applyProperties(this, bossProjectileImg, (double) INITIAL_X_POSITION, initialYPos, IMAGE_HEIGHT, null, true);
	}
	/**
	 * Updates position of boss's projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	/**
	 * Updates boss's projectile actor by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
