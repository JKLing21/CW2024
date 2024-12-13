package com.example.demo.Actors.Projectiles;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.utils.ImageProperties;

import javafx.scene.image.Image;
/**
 * EnemyProjectile class represents projectile fired by enemy plane in game.
 * EnemyProjectile class extends Projectile class and implements specific behaviors for enemy plane's projectiles,
 * such as movement and image properties.
 */
public class EnemyProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 30;
	private static final int HORIZONTAL_VELOCITY = -10;
	private final Image userProjectileImg;
	/**
	 * Constructs new EnemyProjectile instance.
	 * Initialises enemy plane's projectile with its image and initial position.
	 *
	 * @param initialXPos: initial X position of the projectile.
	 * @param initialYPos: initial Y position of the projectile.
	 * @param factory: ComponentsFactory used for components creation.
	 * @param assetLoader: ImgAssetLoader used for loading images.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, factory);
		this.userProjectileImg = assetLoader.loadAsset("enemyFire");
		ImageProperties.applyProperties(this, userProjectileImg, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
	}
	/**
	 * Updates position of enemy plane's projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	/**
	 * Updates enemy plane's projectile actor by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
