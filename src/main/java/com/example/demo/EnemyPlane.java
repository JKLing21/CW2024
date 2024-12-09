package com.example.demo;

import factories.ProjectilesImplement;
import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;
import javafx.scene.image.Image;

public class EnemyPlane extends FighterPlane {

	private static final int IMAGE_HEIGHT = 60;
	private static final double PROJECTILE_X_POSITION_OFFSET = -76.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 22;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;
	private final Image enemyImage;

	private ProjectilesFactory projectileFactory;
	private MovementStrategy movementStrategy;

	public EnemyPlane(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader,
			MovementStrategy movementStrategy) {
		super(IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH, factory);
		this.enemyImage = assetLoader.loadImage("enemyplane");
		this.projectileFactory = new ProjectilesImplement();
		this.movementStrategy = movementStrategy;
		ImageProperties.applyProperties(this, enemyImage, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
	}

	@Override
	public void updatePosition() {
		movementStrategy.move(this);
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