package com.example.demo.Levels;

import com.example.demo.Controller;
import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Factories.ActorImplement;
import com.example.demo.Factories.Interfaces.ActorFactory;
import com.example.demo.Factories.Interfaces.AssetFactory;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Screens.LevelScreen;

import javafx.scene.Group;
import javafx.stage.Stage;
/**
 * LevelOne class represents first level of the game.
 * LevelOne class extends the LevelParent class and implements specific behaviors for Level One,
 * such as enemy spawning, checking game over conditions and level transitions.
 */
public class LevelOne extends LevelParent {

	public static final String BACKGROUND_IMAGE = "background1";
	private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 5;
	private static final long ENEMY_SPAWN_COOLDOWN = 1500;
	private long lastEnemySpawnTime = 300;

	private final ActorFactory actorFactory;
	@SuppressWarnings("unused")
	private final Stage stage;
	/**
	 * Constructs new LevelOne instance.
	 * Initialises level with its background image, screen dimensions, player health and other dependencies.
	 *
	 * @param backgroundImageName: name of the background image for the level.
	 * @param screenHeight: height of screen.
	 * @param screenWidth: width of screen.
	 * @param playerInitialHealth: initial health of the player.
	 * @param controller: controller which manages the game flow.
	 * @param componentsFactory: ComponentsFactory used for components creation.
	 * @param assetFactory: AssetsFactory used for assets creation.
	 * @param audioManager: AudioManager for playing audio.
	 * @param stage: primary stage of the application.
	 */
	public LevelOne(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
			Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory,
			AudioManager audioManager, Stage stage) {
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth, controller, componentsFactory,
				assetFactory, audioManager);
		this.actorFactory = new ActorImplement();
		this.stage = stage;
		lastEnemySpawnTime = System.currentTimeMillis();
	}
	/**
	 * Checks if current level is Level One.
	 *
	 * @return True, since this is Level One.
	 */
	@Override
	protected boolean isLevelOne() {
		return true;
	}
	/**
	 * Checks if game is over based on the player's status and number of kills.
	 * If player is destroyed, the game is lost. If player reaches kill target, then next level is loaded.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget())
			goToNextLevel(NEXT_LEVEL);
	}
	/**
	 * Initialises the player's plane for the level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}
	/**
	 * Spawns enemy units at regular intervals.
	 * Ensures that total number of enemies does not exceed the maximum number allowed.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		if (currentNumberOfEnemies >= TOTAL_ENEMIES) {
			return;
		}
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastEnemySpawnTime >= ENEMY_SPAWN_COOLDOWN) {
			double yUpperBound = 55;
			double yLowerBound = getEnemyMaximumYPosition();
			double newEnemyInitialYPosition = yUpperBound + Math.random() * (yLowerBound - yUpperBound);
			ActiveActorDestructible newEnemy = actorFactory.createEnemyPlane(getScreenWidth(),
					newEnemyInitialYPosition);
			addEnemyUnit(newEnemy);
			lastEnemySpawnTime = currentTime;
		}
	}
	/**
	 * Instantiates level view, which includes game screen and UI elements.
	 *
	 * @return LevelScreen instance representing level view.
	 */
	@Override
	protected LevelScreen instantiateLevelView() {
		Group uiLayer = new Group();
		return new LevelScreen(getRoot(), getInitialHealth(), getComponentsFactory(), getScreenWidth(), uiLayer);
	}
	/**
	 * Gets kill target required to advance to next level.
	 *
	 * @return The kill target.
	 */
	@Override
	protected int getKillTarget() {
		return KILLS_TO_ADVANCE;
	}
	/**
	 * Checks if player has reached kill target set.
	 *
	 * @return True, if player has reached kill target, false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}