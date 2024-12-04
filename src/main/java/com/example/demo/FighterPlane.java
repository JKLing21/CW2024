package com.example.demo;

public abstract class FighterPlane extends ActiveActorDestructible {

	protected int health;
	private int maxHealth;

	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health,
			ComponentsFactory componentsFactory) {
		super(imageName, imageHeight, initialXPos, initialYPos, componentsFactory);
		this.health = health;
		this.maxHealth = health;
	}

	public abstract ActiveActorDestructible fireProjectile();

	@Override
	public void takeDamage() {
		if (health > 0) {
			health = Math.max(health - 1, 0);
			if (health == 0) {
				this.destroy();
			}
		}
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

}
