package com.example.demo.Actors.Projectiles;

import com.example.demo.Actors.ActiveActorDestructible;

import factories.interfaces.ComponentsFactory;
/**
 * Projectile class represents projectile in game.
 * Projectile is an abstract class that extends ActiveActorDestructible and provides common functionality
 * for projectiles, such as position updates and destruction logic.
 */
public abstract class Projectile extends ActiveActorDestructible {

	private double screenWidth;
	/**
	 * Constructs new Projectile instance.
	 * Initialises projectile with its image height, initial position and ComponentsFactory.
	 *
	 * @param imageHeight: height of the projectile's image.
	 * @param initialXPos: initial X position of the projectile.
	 * @param initialYPos: initial Y position of the projectile.
	 * @param componentsFactory: ComponentsFactory used for components creation.
	 */
	public Projectile(int imageHeight, double initialXPos, double initialYPos,
			ComponentsFactory componentsFactory) {
		super(imageHeight, initialXPos, initialYPos, componentsFactory);
	}
	/**
	 * Handles projectile taking damage, which results in its destruction.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}
	/**
	 * Updates position of projectile.
	 * If projectile moves off screen, it is destroyed.
	 */
	@Override
	public void updatePosition() {
		if (this.getX() < 0 || this.getX() > screenWidth) {
			this.destroy();
		}
	}

}
