package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.controller.Controller;

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

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private PauseScreen pauseScreen;
	private KeyEventHandlers keyEventHandlers;
	private int initialHealth;
	private ProjectilesFactory projectilesFactory = new ProjectilesImplement();
	private final ComponentsFactory componentsFactory;
	@SuppressWarnings("unused")
	private final AssetFactory assetFactory;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	protected LevelView levelView;
	private final StringProperty nextLevelProperty = new SimpleStringProperty();
	private static final int FIRE_RATE_DELAY = 200;
	private long lastFireTime = 0;
	private static final int RAPID_FIRE_DELAY = 150;
	private long lastPressTime = 0;
	private boolean isGameOver = false;
	private boolean transitioningToNextLevel = false;
	private boolean isPaused = false;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
			Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		ActorFactory actorFactory = new ActorImplement();
		this.user = actorFactory.createUserPlane(playerInitialHealth, screenWidth, projectilesFactory);
		this.componentsFactory = componentsFactory;
		this.pauseScreen = componentsFactory.createPauseScreen(root, scene, this, controller);
		this.initialHealth = playerInitialHealth;
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();
		this.projectilesFactory = new ProjectilesImplement();
		
		this.assetFactory = assetFactory;
		this.background = assetFactory.createBackgroundImage(backgroundImageName, screenWidth, screenHeight);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = componentsFactory.createLevelView(root, playerInitialHealth);
		initializeTimeline();
		friendlyUnits.add(user);

		this.keyEventHandlers = new KeyEventHandlers(user, this);
		keyEventHandlers.attachHandlers(scene);
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	protected abstract int getKillTarget();

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();

		Group uiLayer = new Group();
		ImageView pauseImageView = componentsFactory.createPauseButton(
		        screenWidth - 60, 10, 50, 50, e -> togglePause()
		    );
		    uiLayer.getChildren().add(pauseImageView);

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

	public int getInitialHealth() {
		return initialHealth;
	}

	public ComponentsFactory getComponentsFactory() {
		return componentsFactory;
	}

	public void goToNextLevel(String levelName) {
		if (!isGameOver && !transitioningToNextLevel && user.getHealth() > 0) {
			transitioningToNextLevel = true;
			nextLevelProperty.set(levelName);
			timeline.stop();
		}
	}

	public StringProperty nextLevelProperty() {
		return nextLevelProperty;
	}

	private void updateScene() {
		if (isGameOver || transitioningToNextLevel || isPaused) {
			return;
		}

		checkIfGameOver();
		if (!isGameOver) {
			spawnEnemyUnits();
			updateActors();
			generateEnemyFire();
			handleEnemyPenetration();
			handleUserProjectileCollisions();
			handleEnemyProjectileCollisions();
			handlePlaneCollisions();
			removeAllDestroyedActors();
			updateLevelView();
		}

		if (keyEventHandlers.isSpaceBarHeld()) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastFireTime >= FIRE_RATE_DELAY) {
				fireProjectile();
				lastFireTime = currentTime;
			}
		}

		if (keyEventHandlers.isSpaceBarPressed()) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastPressTime >= RAPID_FIRE_DELAY) {
				fireProjectile();
				lastPressTime = currentTime;
				keyEventHandlers.setSpaceBarPressed(false);
			}
		}
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}
	
	 private void initializeBackground() {
	        background.setFocusTraversable(true);
	        root.getChildren().add(background);
	    }

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		if (projectile != null) {
			root.getChildren().add(projectile);
			userProjectiles.add(projectile);
			levelView.addHitboxesToScene(root, (ActiveActor) projectile);
		}
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

	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		Set<ActiveActorDestructible> enemiesToRemove = new HashSet<>();
		Set<ActiveActorDestructible> projectilesToRemove = new HashSet<>();

		for (ActiveActorDestructible projectile : userProjectiles) {
			for (ActiveActorDestructible enemy : enemyUnits) {
				if (projectile.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
					enemy.takeDamage();
					projectile.takeDamage();
					if (enemy.isDestroyed()) {
						if (!enemy.hasBeenCounted()) {
							user.incrementKillCount();
							enemy.setHasBeenCounted(true);
						}
						enemiesToRemove.add(enemy);
					}
					if (projectile.isDestroyed()) {
						projectilesToRemove.add(projectile);
					}
				}
			}
		}

		enemyUnits.removeAll(enemiesToRemove);
		userProjectiles.removeAll(projectilesToRemove);

		List<Node> nodesToRemove = new ArrayList<>();
		nodesToRemove.addAll(enemiesToRemove);
		nodesToRemove.addAll(projectilesToRemove);
		root.getChildren().removeAll(nodesToRemove.toArray(new Node[0]));
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		List<ActiveActorDestructible> enemiesToRemove = new ArrayList<>();
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy(root);
				enemiesToRemove.add(enemy);
			}
		}
		enemyUnits.removeAll(enemiesToRemove);
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		levelView.updateKillCount(user.getNumberOfKills());
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		isGameOver = true;
		timeline.stop();
		levelView.showGameOverImage();
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

	protected LevelView getLevelView() {
		return levelView;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void togglePause() {
		isPaused = !isPaused;
		if (isPaused) {
			timeline.pause();
			pauseScreen.showPauseMenu();
		} else {
			timeline.play();
			pauseScreen.hidePauseMenu();
			root.requestFocus();
		}
		root.requestFocus();
	}
} 