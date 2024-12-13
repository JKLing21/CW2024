package com.example.demo.Actors.Projectiles;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.utils.ImageProperties;

import javafx.scene.image.Image;
/**
 * UserProjectile class represents projectile fired by userplane in game.
 * UserProjectile class extends Projectile class and implements specific behaviors for userplane's projectiles,
 * such as movement and image properties.
 */
public class UserProjectile extends Projectile {

	private static final int IMAGE_HEIGHT = 6;
	private static final int HORIZONTAL_VELOCITY = 15;

	private double screenWidth;
	private final Image userProjectileImg;
	/**
	 * Constructs new UserProjectile instance.
	 * Initialises userplane's projectile with its image, initial position and screen width.
	 *
	 * @param initialXPos: initial X position of projectile.
	 * @param initialYPos: initial Y position of projectile.
	 * @param screenWidth: width of screen.
	 * @param factory: ComponentsFactory used for components creation.
	 * @param assetLoader: ImgAssetLoader used for loading images.
	 */
	public UserProjectile(double initialXPos, double initialYPos, double screenWidth, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, factory);
		this.screenWidth = screenWidth;
		this.userProjectileImg = assetLoader.loadAsset("userfire");
		ImageProperties.applyProperties(this, userProjectileImg, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
	}
	/**
	 * Updates position of userplane's projectile by moving it horizontally and checking if it's out of bounds.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
		checkBounds();
	}
	/**
	 * Updates userplane's projectile actor by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	/**
	 * Checks if userplane's projectile is out of bounds and destroys it if necessary.
	 */
	private void checkBounds() {
		if (getX() > screenWidth) {
			destroy();
		}
	}

}
