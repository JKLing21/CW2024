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

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelScreen instantiateLevelView();

	protected abstract int getKillTarget();

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

	public void startGame() {
		root.requestFocus();
		timeline.play();
	}

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

	public int getInitialHealth() {
		return initialHealth;
	}

	public ComponentsFactory getComponentsFactory() {
		return componentsFactory;
	}

	public PauseManager getPauseManager() {
		return pauseManager;
	}

	public void goToNextLevel(String levelName) {
		if (!isGameOver && !transitioningToNextLevel && user.getHealth() > 0) {
			transitioningToNextLevel = true;
			nextLevelProperty.set(levelName);
			timeline.stop();
			audioManager.fadeOut(Duration.seconds(2));
		}
	}

	public StringProperty nextLevelProperty() {
		return nextLevelProperty;
	}

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

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	public Timeline getTimeline() {
		return timeline;
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		root.getChildren().add(background);
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> {
			if (enemy instanceof FighterPlane) {
				ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
				spawnEnemyProjectile(projectile);
			}
		});
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
			levelView.addHitboxesToScene(root, (ActiveActor) projectile);
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());

		root.getChildren().removeAll(destroyedActors.toArray(new Node[0]));
		destroyedActors.forEach(actor -> {
			actor.destroy(root);
		});
		actors.removeAll(destroyedActors);
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		levelView.updateKillCount(user.getNumberOfKills(), getKillTarget());
	}

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

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
		levelView.addHitboxesToScene(root, (ActiveActor) enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected double getScreenHeight() {
		return screenHeight;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	protected LevelScreen getLevelView() {
		return levelView;
	}

	public void togglePause() {
		pauseManager.togglePause();
	}

	protected boolean isLevelOne() {
		return false;
	}

	protected boolean shouldShowKillCount() {
		return true;
	}

	public Scene getScene() {
		return scene;
	}

}