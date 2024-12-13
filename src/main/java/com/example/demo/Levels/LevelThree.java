package com.example.demo.Levels;

import com.example.demo.Controller;
import com.example.demo.Actors.Planes.Boss;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.ActorImplement;
import com.example.demo.Factories.Interfaces.ActorFactory;
import com.example.demo.Factories.Interfaces.AssetFactory;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Screens.LevelScreen;
import com.example.demo.Screens.TransitionScene;
import com.example.demo.Screens.WinScreen;

import javafx.scene.Group;
import javafx.stage.Stage;
/**
 * LevelThree class represents third level of the game, which features a boss battle.
 * LevelThree class extends LevelParent class and implements specific behaviors for LevelThree,
 * such as initialising the boss, checking win conditions, and handling the boss's health bar.
 */
public class LevelThree extends LevelParent {

	public static final String BACKGROUND_IMAGE = "background3";
	private ActorFactory actorFactory;
	private final ImgAssetLoader imgAssetLoader;
	private final Stage stage;
	private final Boss boss;
	/**
	 * Constructs new LevelThree instance.
	 * Initialises the level with its background image, screen dimensions, player health and other dependencies.
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
	public LevelThree(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
			Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory, AudioManager audioManager, Stage stage) {
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth, controller, componentsFactory,
				assetFactory, audioManager);
		this.imgAssetLoader = new ImgAssetLoader() {};
		this.actorFactory = new ActorImplement();
		this.boss = actorFactory.createBoss();
		this.levelView = instantiateLevelView();
		this.stage = stage;
	}
	/**
	 * Initialises the player's plane for the level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}
	/**
	 * Checks if game is over based on player's status and boss's status.
	 * If player is destroyed, the game is lost. If boss is destroyed, win condition is checked.
	 */
	@Override
	protected void checkIfGameOver() {
	    if (userIsDestroyed()) {
	        loseGame();
	    } else if (boss.isDestroyed()) {
	        checkWinCondition();
	    }
	}
	/**
	 * Checks if win condition is met.
	 * If boss is destroyed, game transitions to win screen.
	 */
	private void checkWinCondition() {
		 if (boss.isDestroyed()) {
		        timeline.stop();
		        audioManager.stopBackgroundMusic();
		        TransitionScene.fadeOutCurrentScene(stage, () -> {
		            WinScreen winScreen = new WinScreen(stage, imgAssetLoader, componentsFactory, this, controller);
		            winScreen.showWinScreen();
		        });
		    }
	}

	/**
	 * Spawns boss unit if it hasn't been spawned yet.
	 * Adds boss's health bar and boss related UI elements to the scene.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			if (!getRoot().getChildren().contains(boss.getHealthBarBackground())) {
				getRoot().getChildren().addAll(boss.getHealthBarBackground(), boss.getHealthBar(),
						boss.getBossNameText(), boss.getBossHealthBar().getShieldIcon());
			}
		}
	}
	/**
	 * Instantiates level view, which includes game screen and UI elements.
	 *
	 * @return LevelScreen instance representing the level view.
	 */
	@Override
	protected LevelScreen instantiateLevelView() {
		Group uiLayer = new Group();
		return new LevelScreen(getRoot(), getInitialHealth(), getComponentsFactory(), getScreenWidth(), uiLayer);
	}
	/**
	 * Gets kill target required to advance to next level.
	 * For this level, kill target is not applicable since it's a boss level.
	 *
	 * @return 0, as kill target is not applicable.
	 */
	@Override
	protected int getKillTarget() {
		return 0;
	}
	/**
	 * Checks if current level is LevelOne.
	 *
	 * @return False, since this is LevelThree.
	 */
	@Override
	protected boolean isLevelOne() {
	    return false;
	}
	/**
	 * Checks if kill count should be shown.
	 *
	 * @return False, since kill count is not applicable in this level.
	 */
	@Override
	protected boolean shouldShowKillCount() {
	    return false;
	}

}
