package com.example.demo.Levels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Actors.ActiveActor;
import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Actors.Planes.FighterPlane;
import com.example.demo.Actors.Planes.UserPlane;
import com.example.demo.Assets.ImgAssetLoaderImpl;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Managers.CollisionManager;
import com.example.demo.Managers.KeyEventHandlers;
import com.example.demo.Managers.PauseManager;
import com.example.demo.Screens.LevelScreen;
import com.example.demo.Screens.LoseScreen;
import com.example.demo.Screens.PauseScreen;
import com.example.demo.Screens.TransitionScene;
import com.example.demo.Strategy.UserFiringStrategy;
import com.example.demo.controller.Controller;

import factories.ActorImplement;
import factories.ProjectilesImplement;
import factories.interfaces.ActorFactory;
import factories.interfaces.AssetFactory;
import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
/**
 * LevelParent class represents parent class for all levels in game.
 * LevelParent is an abstract class which provides common functionality for managing game levels,
 * including initialising game scene, handling game logic, and managing actors.
 */
public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 90;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	protected final Timeline timeline;
	protected final Controller controller;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	@SuppressWarnings("unused")
	private PauseScreen pauseScreen;
	private KeyEventHandlers keyEventHandlers;
	private int initialHealth;
	protected Group uiLayer;
	private ProjectilesFactory projectilesFactory = new ProjectilesImplement();
	protected final ComponentsFactory componentsFactory;
	@SuppressWarnings("unused")
	private final AssetFactory assetFactory;
	private CollisionManager collisionManager;
	private final PauseManager pauseManager;
	private final UserFiringStrategy userFiringStrategy;
	protected final AudioManager audioManager;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	protected LevelScreen levelView;
	private final StringProperty nextLevelProperty = new SimpleStringProperty();
	private boolean isGameOver = false;
	private boolean transitioningToNextLevel = false;
	/**
	 * Constructs new LevelParent instance.
	 * Initialises level with its background image, screen dimensions, player health and other dependencies.
	 *
	 * @param backgroundImageName: name of background image for the level.
	 * @param screenHeight: height of screen.
	 * @param screenWidth: width of screen.
	 * @param playerInitialHealth: initial health of the player.
	 * @param controller: controller which manages the game flow.
	 * @param componentsFactory: ComponentsFactory used for components creation.
	 * @param assetFactory: AssetsFactory used for assets creation.
	 * @param audioManager: AudioManager for playing audio.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
			Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory,
			AudioManager audioManager) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.controller = controller;
		this.audioManager = audioManager;
		this.audioManager.preloadSoundEffect("planefire");
		ActorFactory actorFactory = new ActorImplement();
		this.user = actorFactory.createUserPlane(playerInitialHealth, screenWidth, projectilesFactory, audioManager);
		this.componentsFactory = componentsFactory;
		this.pauseManager = new PauseManager(root, scene, this, controller, audioManager);
		this.pauseScreen = componentsFactory.createPauseScreen(root, scene, this, controller);
		this.initialHealth = playerInitialHealth;
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.projectilesFactory = new ProjectilesImplement();
		this.userFiringStrategy = new UserFiringStrategy(new ProjectilesImplement(), screenWidth);

		this.assetFactory = assetFactory;
		this.background = assetFactory.createBackgroundImage(backgroundImageName, screenWidth, screenHeight);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.collisionManager = new CollisionManager(root, screenWidth);
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.uiLayer = new Group();
		this.levelView = componentsFactory.createLevelView(root, playerInitialHealth, screenWidth, uiLayer);
		initializeTimeline();
		friendlyUnits.add(user);

		this.keyEventHandlers = new KeyEventHandlers(user, this);
		keyEventHandlers.attachHandlers(scene);
		keyEventHandlers.addPauseKeyBinding(scene, () -> this.togglePause());
	}
	/**
	 * Initialises the player's plane for the level.
	 * Method that must be implemented by subclasses.
	 */
	protected abstract void initializeFriendlyUnits();
	/**
	 * Checks if game is over based on the player's status and number of kills.
	 * Method that must be implemented by subclasses.
	 */
	protected abstract void checkIfGameOver();
	/**
	 * Spawns enemy units at regular intervals.
	 * Method that must be implemented by subclasses.
	 */
	protected abstract void spawnEnemyUnits();
	/**
	 * Instantiates level view, which includes the game screen and UI elements.
	 * Method that must be implemented by subclasses.
	 *
	 * @return LevelScreen instance representing level view.
	 */
	protected abstract LevelScreen instantiateLevelView();
	/**
	 * Gets kill target required to advance to next level.
	 * Method that must be implemented by subclasses.
	 *
	 * @return kill target.
	 */
	protected abstract int getKillTarget();
	/**
	 * Initialises game scene with background, friendly units, and UI elements.
	 *
	 * @return initialised Scene.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		if (shouldShowKillCount()) {
			levelView.showKillCountText();
		}

		ImageView pauseImageView = componentsFactory.getImgViewFactory().createPauseButton(screenWidth - 60, 10, 50, 50,
				e -> togglePause());
		uiLayer.getChildren().add(pauseImageView);
		if (isLevelOne()) {
			levelView.showInstructions();
		}
		Pane layeredPane = new Pane();
		layeredPane.getChildren().addAll(root, uiLayer);

		Scene scene = new Scene(layeredPane, screenWidth, screenHeight);
		keyEventHandlers.attachHandlers(scene);
		return scene;
	}
	/**
	 * Starts game by requesting focus and playing timeline.
	 */
	public void startGame() {
		root.requestFocus();
		timeline.play();
	}
	/**
	 * Restarts the game by stopping the timeline, clearing the root, resetting the player's health and kills,
	 * and reinitialising friendly units.
	 */
	public void restartGame() {
		try {
			timeline.stop();
			root.getChildren().clear();
			user.setHealth(initialHealth);
			user.setNumberOfKills(0);
			friendlyUnits.clear();
			enemyUnits.clear();
			userProjectiles.clear();
			enemyProjectiles.clear();
			initializeFriendlyUnits();
			levelView.showHeartDisplay();
			timeline.play();
			controller.resetAndRelaunchGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gets initial health of the player.
	 *
	 * @return initial health.
	 */
	public int getInitialHealth() {
		return initialHealth;
	}
	/**
	 * Gets ComponentsFactory.
	 *
	 * @return components factory.
	 */
	public ComponentsFactory getComponentsFactory() {
		return componentsFactory;
	}
	/**
	 * Gets PauseManager.
	 *
	 * @return pause manager.
	 */
	public PauseManager getPauseManager() {
		return pauseManager;
	}
	/**
	 * Transitions to next level specified by the level name.
	 *
	 * @param levelName: fully qualified class name of next level.
	 */
	public void goToNextLevel(String levelName) {
		if (!isGameOver && !transitioningToNextLevel && user.getHealth() > 0) {
			transitioningToNextLevel = true;
			nextLevelProperty.set(levelName);
			timeline.stop();
			audioManager.fadeOut(Duration.seconds(2));
		}
	}
	/**
	 * Gets property representing next level to transition to.
	 *
	 * @return next level property.
	 */
	public StringProperty nextLevelProperty() {
		return nextLevelProperty;
	}
	/**
	 * Updates game scene by checking for game over conditions, spawning enemies, updating actors,
	 * handling collisions, and removing destroyed actors.
	 */
	private void updateScene() {
		if (isGameOver || transitioningToNextLevel || pauseManager.isPaused) {
			return;
		}

		checkIfGameOver();
		if (!isGameOver) {
			spawnEnemyUnits();
			updateActors();
			generateEnemyFire();
			collisionManager.handleEnemyPenetration(enemyUnits, user);
			collisionManager.handleProjectileCollisions(userProjectiles, enemyUnits, user);
			collisionManager.handleProjectileCollisions(enemyProjectiles, friendlyUnits, user);
			collisionManager.handlePlaneCollisions(friendlyUnits, enemyUnits);
			removeAllDestroyedActors();
			updateLevelView();
		}

		if (keyEventHandlers.isSpaceBarHeld()) {
			ActiveActorDestructible projectile = userFiringStrategy.fireContinuous(user);
			if (projectile != null) {
				userProjectiles.add(projectile);
				root.getChildren().add(projectile);
			}
		}

		if (keyEventHandlers.isSpaceBarPressed()) {
			ActiveActorDestructible projectile = userFiringStrategy.fireRapid(user);
			if (projectile != null) {
				userProjectiles.add(projectile);
				root.getChildren().add(projectile);
				keyEventHandlers.setSpaceBarPressed(false);
			}
		}
	}
	/**
	 * Initialises timeline for game loop.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}
	/**
	 * Gets timeline for game loop.
	 *
	 * @return timeline.
	 */
	public Timeline getTimeline() {
		return timeline;
	}
	/**
	 * Initialises background image for the level.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		root.getChildren().add(background);
	}
	/**
	 * Generates enemy fire by having each enemy plane fire the projectile.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> {
			if (enemy instanceof FighterPlane) {
				ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
				spawnEnemyProjectile(projectile);
			}
		});
	}
	/**
	 * Spawns enemy projectile and adds it to the scene.
	 *
	 * @param projectile: enemy projectile to spawn.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
			levelView.addHitboxesToScene(root, (ActiveActor) projectile);
		}
	}
	/**
	 * Updates all actors in game.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}
	/**
	 * Removes all destroyed actors from scene and the respective lists.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}
	/**
	 * Removes destroyed actors from given list.
	 *
	 * @param actors: list of actors to remove destroyed actors from.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());

		root.getChildren().removeAll(destroyedActors.toArray(new Node[0]));
		destroyedActors.forEach(actor -> {
			actor.destroy(root);
		});
		actors.removeAll(destroyedActors);
	}
	/**
	 * Updates level view by removing hearts and updating kill count.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		levelView.updateKillCount(user.getNumberOfKills(), getKillTarget());
	}
	/**
	 * Handles game over condition by stopping the timeline, stopping background music,
	 * and showing lose screen.
	 */
	protected void loseGame() {
		isGameOver = true;
		timeline.stop();
		audioManager.stopBackgroundMusic();
		TransitionScene.fadeOutCurrentScene(controller.getStage(), () -> {
			LoseScreen loseScreen = new LoseScreen(controller.getStage(), new ImgAssetLoaderImpl(), componentsFactory,
					this, controller);
			loseScreen.showLoseScreen();
		});
	}
	/**
	 * Gets userplane.
	 *
	 * @return userplane.
	 */
	protected UserPlane getUser() {
		return user;
	}
	/**
	 * Gets root group of scene.
	 *
	 * @return root group.
	 */
	protected Group getRoot() {
		return root;
	}
	/**
	 * Gets current number of enemies.
	 *
	 * @return current number of enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}
	/**
	 * Adds enemy unit to scene and enemy units list.
	 *
	 * @param enemy: enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
		levelView.addHitboxesToScene(root, (ActiveActor) enemy);
	}
	/**
	 * Gets maximum Y position for enemies.
	 *
	 * @return maximum Y position for enemies.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}
	/**
	 * Gets the screen width.
	 *
	 * @return screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}
	/**
	 * Gets screen height.
	 *
	 * @return screen height.
	 */
	protected double getScreenHeight() {
		return screenHeight;
	}
	/**
	 * Checks if userplane is destroyed.
	 *
	 * @return True, if userplane is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}
	/**
	 * Gets level view.
	 *
	 * @return level view.
	 */
	protected LevelScreen getLevelView() {
		return levelView;
	}
	/**
	 * Toggles pause state of the game.
	 */
	public void togglePause() {
		pauseManager.togglePause();
	}
	/**
	 * Checks if current level is Level One.
	 *
	 * @return True, if current level is Level One, false otherwise.
	 */
	protected boolean isLevelOne() {
		return false;
	}
	/**
	 * Checks if kill count should be shown.
	 *
	 * @return True, if kill count should be shown, false otherwise.
	 */
	protected boolean shouldShowKillCount() {
		return true;
	}
	/**
	 * Gets scene of the level.
	 *
	 * @return scene of the level.
	 */
	public Scene getScene() {
		return scene;
	}

}