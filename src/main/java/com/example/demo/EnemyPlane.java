package com.example.demo;

import javafx.scene.image.Image;

public class EnemyPlane extends FighterPlane {

	private static final int IMAGE_HEIGHT = 60;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -76.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 22;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;
	private final Image enemyImage;

	private ProjectilesFactory projectileFactory;

	public EnemyPlane(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH, factory);
		this.enemyImage = assetLoader.loadImage("enemyplane");
		this.projectileFactory = new ProjectilesImplement();
		ImageProperties.applyProperties(this, enemyImage, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return projectileFactory.createEnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
