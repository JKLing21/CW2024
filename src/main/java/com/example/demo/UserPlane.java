package com.example.demo;

import javafx.scene.image.Image;

public class UserPlane extends FighterPlane {

	private double screenWidth;
	private static final double Y_UPPER_BOUND = 0;
	private static final double Y_LOWER_BOUND = 680.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 40;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 150;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 25;
	private int velocityMultiplier;
	private int numberOfKills;
	private boolean isFiring;
	private long lastShotTime;
	private static final long FIRE_INTERVAL = 300;
	private final Image planeImage;

	public UserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory,
			ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth, factory);
		this.screenWidth = screenWidth;
		this.planeImage = assetLoader.loadImage("userplane");
		ImageProperties.applyProperties(this, planeImage, INITIAL_X_POSITION, INITIAL_Y_POSITION, IMAGE_HEIGHT, null, true);
		velocityMultiplier = 0;
		this.health = 5;
		lastShotTime = System.currentTimeMillis();
	}

	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	@Override
	public void updateActor() {
		updatePosition();
		if (isFiring && (System.currentTimeMillis() - lastShotTime >= FIRE_INTERVAL)) {
			fireProjectile();
			lastShotTime = System.currentTimeMillis();
		}
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		double projectileX = getLayoutX() + getTranslateX() + PROJECTILE_X_POSITION;
		double projectileY = getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
		ProjectilesFactory projectileFactory = new ProjectilesImplement();
		return projectileFactory.createUserProjectile(projectileX, projectileY, screenWidth);
	}

	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	public void moveUp() {
		velocityMultiplier = -1;
	}

	public void moveDown() {
		velocityMultiplier = 1;
	}

	public void stop() {
		velocityMultiplier = 0;
	}

	public void startFiring() {
		isFiring = true;
	}

	public void stopFiring() {
		isFiring = false;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

	public void setNumberOfKills(int numberOfKills) {
		this.numberOfKills = numberOfKills;
	}

	public int getVelocityMultiplier() {
		return velocityMultiplier;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}