package com.example.demo;

import factories.interfaces.ComponentsFactory;
import javafx.scene.image.ImageView;

public abstract class FighterPlane extends ActiveActorDestructible {

	protected int health;
	private int maxHealth;
	private final ImageView imageView;

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

	public double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	public double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

}