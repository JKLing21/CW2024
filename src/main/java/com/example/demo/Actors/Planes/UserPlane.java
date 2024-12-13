package com.example.demo.Actors.Planes;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Factories.Interfaces.ProjectilesFactory;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Strategy.FiringStrategy;
import com.example.demo.Strategy.UserFiringStrategy;
import com.example.demo.Strategy.UserMovementStrategy;
import com.example.demo.utils.ImageProperties;

import javafx.scene.image.Image;
/**
 * UserPlane class represents user's plane in game.
 * UserPlane class extends FighterPlane class and implements specific behaviors for user's plane,
 * such as strategies for movement and firing.
 */
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
	/**
	 * Constructs new UserPlane instance.
	 * Initialises user's plane with its image, movement and firing strategies and the audio manager.
	 *
	 * @param initialHealth: initial health of user's plane.
	 * @param screenWidth: width of the screen.
	 * @param projectilesFactory:  ProjectilesFactory used for projectiles creation.
	 * @param factory: ComponentsFactory used for components creation.
	 * @param assetLoader: ImgAssetLoader used for loading images.
	 * @param audioManager: AudioManager used for playing audio.
	 */
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
	/**
	 * Updates position of user's plane based on userplane's movement strategy.
	 */
	@Override
	public void updatePosition() {
		movementStrategy.updatePosition();
	}
	/**
	 * Updates user's plane actor by updating its position and projectiles fired if firing is enabled.
	 */
	@Override
	public void updateActor() {
		movementStrategy.updatePosition();
		if (isFiring) {
            fireProjectile();
        }
	}
	/**
	 * Fires projectile based on user's plane firing strategy.
	 *
	 * @return fired projectile as ActiveActorDestructible.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return firingStrategy.fire(this);
	}
	/**
	 * Moves userplane upwards.
	 */
	public void moveUp() {
		movementStrategy.moveUp();
	}
	/**
	 * Moves userplane downwards.
	 */
	public void moveDown() {
		movementStrategy.moveDown();
	}
	/**
	 * Stops userplane movement.
	 */
	public void stop() {
		movementStrategy.stop();
	}
	/**
	 * Starts firing projectiles from userplane.
	 */
	public void startFiring() {
		isFiring = true;
	}
	/**
	 * Stops firing projectiles from userplane.
	 */
	public void stopFiring() {
		isFiring = false;
	}
	/**
	 * Gets number of kills achieved by userplane.
	 *
	 * @return number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}
	/**
	 * Increments kill count by one.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
	/**
	 * Sets number of kills achieved by userplane.
	 *
	 * @param numberOfKills: number of kills to be set.
	 */
	public void setNumberOfKills(int numberOfKills) {
		this.numberOfKills = numberOfKills;
	}
	/**
	 * Gets velocity multiplier for userplane movement.
	 *
	 * @return velocity multiplier.
	 */
	public int getVelocityMultiplier() {
		return movementStrategy.getVelocityMultiplier();
	}
	/**
	 * Gets current health of userplane.
	 *
	 * @return current health.
	 */
	public int getHealth() {
		return this.health;
	}
	/**
	 * Sets health of userplane.
	 *
	 * @param health: health to be set.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * Gets AudioManager used by userplane.
	 *
	 * @return audio manager.
	 */
	public AudioManager getAudioManager() {
        return audioManager;
    }
	/**
	 * Sets audio manager for userplane.
	 *
	 * @param audioManager: audio manager to be set.
	 */
    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

}