package com.example.demo;

import factories.ProjectilesImplement;
import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;
import javafx.scene.image.Image;

public class UserPlane extends FighterPlane {

	private double screenWidth;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 40;
	private static final int PROJECTILE_X_POSITION = 150;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 25;
	private int numberOfKills;
	private boolean isFiring;
	private long lastShotTime;
	private static final long FIRE_INTERVAL = 300;
	private final Image planeImage;
	
	private final UserMovementStrategy movementStrategy;

	public UserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory,
			ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth, factory);
		this.screenWidth = screenWidth;
		this.planeImage = assetLoader.loadImage("userplane");
		ImageProperties.applyProperties(this, planeImage, INITIAL_X_POSITION, INITIAL_Y_POSITION, IMAGE_HEIGHT, null, true);
		this.health = 5;
		lastShotTime = System.currentTimeMillis();
		
		this.movementStrategy = new UserMovementStrategy(this);
	}
	
	@Override
	public void updatePosition() {
	    movementStrategy.updatePosition();
	}

	@Override
    public void updateActor() {
        movementStrategy.updatePosition();

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

	public void moveUp() {
        movementStrategy.moveUp();
    }

    public void moveDown() {
        movementStrategy.moveDown();
    }

    public void stop() {
        movementStrategy.stop();
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
		return movementStrategy.getVelocityMultiplier();
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}