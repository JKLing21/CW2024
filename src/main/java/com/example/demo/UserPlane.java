package com.example.demo;

import com.example.demo.Strategy.FiringStrategy;
import com.example.demo.Strategy.UserFiringStrategy;
import com.example.demo.Strategy.UserMovementStrategy;

import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;
import javafx.scene.image.Image;

public class UserPlane extends FighterPlane {

	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 40;
	private int numberOfKills;
	private boolean isFiring;
	private final Image planeImage;
	private AudioManager audioManager;

	private final UserMovementStrategy movementStrategy;
	private final FiringStrategy firingStrategy;

	public UserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory,
			ComponentsFactory factory, ImgAssetLoader assetLoader, AudioManager audioManager) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth, factory);
		this.planeImage = assetLoader.loadAsset("userplane");
		ImageProperties.applyProperties(this, planeImage, INITIAL_X_POSITION, INITIAL_Y_POSITION, IMAGE_HEIGHT, null, true);
		this.health = 5;
		this.firingStrategy = new UserFiringStrategy(projectilesFactory, screenWidth);
		this.movementStrategy = new UserMovementStrategy(this);
		this.audioManager = audioManager;
	}

	@Override
	public void updatePosition() {
		movementStrategy.updatePosition();
	}

	@Override
	public void updateActor() {
		movementStrategy.updatePosition();
		if (isFiring) {
            fireProjectile();
        }
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return firingStrategy.fire(this);
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
	
	public AudioManager getAudioManager() {
        return audioManager;
    }

    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

}