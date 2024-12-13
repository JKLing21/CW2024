package com.example.demo.Actors.Planes;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Factories.Interfaces.ComponentsFactory;

import javafx.scene.image.ImageView;
/**
 * FighterPlane class represents fighter plane in game.
 * It's an abstract class which extends ActiveActorDestructible and provides common functionality
 * for fighter planes, such as health management and projectile firing.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	protected int health;
	private int maxHealth;
	private final ImageView imageView;
	/**
	 * Constructs new FighterPlane instance.
	 * Initialises fighter plane with its image, health and position.
	 *
	 * @param imageHeight: height of fighter plane's image.
	 * @param initialXPos: initial X position of the fighter plane.
	 * @param initialYPos: initial Y position of the fighter plane.
	 * @param health: initial health of the fighter plane.
	 * @param componentsFactory: ComponentsFactory used for components creation.
	 */
	public FighterPlane(int imageHeight, double initialXPos, double initialYPos, int health,
			ComponentsFactory componentsFactory) {
		super(imageHeight, initialXPos, initialYPos, componentsFactory);
		this.health = health;
		this.maxHealth = health;
		this.imageView = new ImageView();
        this.imageView.setFitHeight(imageHeight);
        this.imageView.setPreserveRatio(true);
        setLayoutX(initialXPos);
        setLayoutY(initialYPos);
	}
	/**
	 * Fires projectile from fighter plane.
	 * Method that must be implemented by its subclasses to define specific firing behavior.
	 *
	 * @return fired projectile as ActiveActorDestructible.
	 */
	public abstract ActiveActorDestructible fireProjectile();
	/**
	 * Reduces fighter plane's health when it takes damage.
	 * If health reaches zero, plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		if (health > 0) {
			health = Math.max(health - 1, 0);
			if (health == 0) {
				this.destroy();
			}
		}
	}
	/**
	 * Calculates X position for projectile based on fighter plane's current position and offset.
	 *
	 * @param xPositionOffset: offset to add to fighter plane's X position.
	 * @return calculated X position for projectile.
	 */
	public double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}
	/**
	 * Calculates Y position for projectile based on fighter plane's current position and offset.
	 *
	 * @param xPositionOffset: offset to add to fighter plane's Y position.
	 * @return calculated Y position for projectile.
	 */
	public double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}
	/**
	 * Gets current health of fighter plane.
	 *
	 * @return current health.
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Gets maximum health of fighter plane.
	 *
	 * @return maximum health.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

}