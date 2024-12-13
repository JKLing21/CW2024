package com.example.demo.Levels;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Screens.LevelScreen;
import com.example.demo.controller.Controller;

import factories.ActorImplement;
import factories.interfaces.ActorFactory;
import factories.interfaces.AssetFactory;
import factories.interfaces.ComponentsFactory;
import javafx.stage.Stage;
/**
 * LevelTwo class represents second level of the game.
 * LevelTwo class extends LevelParent class and implements specific behaviors for Level Two,
 * such as enemy spawning, checking game over conditions and level transitions.
 */
public class LevelTwo extends LevelParent {

	public static final String BACKGROUND_IMAGE = "background2";
	private static final String NEXT_LEVEL = "com.example.demo.Levels.LevelThree";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 5;
	private static final long ENEMY_SPAWN_COOLDOWN = 1500;
	private long lastEnemySpawnTime = 0;
	private ActorFactory actorFactory;
	@SuppressWarnings("unused")
	private final Stage stage;
	/**
	 * Constructs new LevelTwo instance.
	 * Initialises the level with its background image, screen dimensions, player health, and other dependencies.
	 *
	 * @param backgroundImageName: name of background image for the level.
	 * @param screenHeight: height of screen.
	 * @param screenWidth: width of screen.
	 * @param playerInitialHealth: initial health of the player.
	 * @param controller: controller which manages the game flow.
	 * @param componentsFactory: ComponentsFactory used for components creation.
	 * @param assetFactory: AssetsFactory used for assets creation.
	 * @param audioManager: AudioManager for playing audio.
	 * @param stage: primary stage of application.
	 */
	public LevelTwo(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
			Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory,
			AudioManager audioManager, Stage stage) {
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth, controller, componentsFactory,
				assetFactory, audioManager);
		this.stage = stage;
		this.actorFactory = new ActorImplement();
	}
	/**
	 * Initialises the player's plane for the level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}
	/**
	 * Checks if game is over based on player's status and number of kills.
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
	 * Spawns enemy units at regular intervals.
	 * Ensures that total number of enemies does not exceed maximum number allowed.
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
			ActiveActorDestructible newEnemy = actorFactory.createWarPlane(getScreenWidth(), newEnemyInitialYPosition);
			addEnemyUnit(newEnemy);
			lastEnemySpawnTime = currentTime;
		}
	}
	/**
	 * Instantiates level view, which includes game screen and UI elements.
	 *
	 * @return LevelScreen: instance representing level view.
	 */
	@Override
	protected LevelScreen instantiateLevelView() {
		return new LevelScreen(getRoot(), getInitialHealth(), getComponentsFactory(), getScreenWidth(), uiLayer);
	}
	/**
	 * Gets kill target required to advance to next level.
	 *
	 * @return kill target.
	 */
	@Override
	protected int getKillTarget() {
		return KILLS_TO_ADVANCE;
	}
	/**
	 * Checks if player has reached kill target.
	 *
	 * @return True ,if player has reached kill target, false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
