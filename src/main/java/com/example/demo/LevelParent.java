package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

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
    private ShieldImage shieldImage;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private LevelView levelView;
    private final StringProperty nextLevelProperty = new SimpleStringProperty();
    private boolean isSpaceBarPressed = false;
    private boolean isSpaceBarHeld = false;
    private static final int FIRE_RATE_DELAY = 200;
    private long lastFireTime = 0;
    private static final int RAPID_FIRE_DELAY = 150;
    private long lastPressTime = 0;
    private boolean isGameOver = false;
    private boolean transitioningToNextLevel = false;

    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.timeline = new Timeline();
        this.user = new UserPlane(playerInitialHealth, screenWidth);
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        initializeTimeline();
        friendlyUnits.add(user);
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
        shieldImage = new ShieldImage(100, 100);
        root.getChildren().add(shieldImage);
        if (!root.getChildren().contains(shieldImage)) {
            root.getChildren().add(shieldImage);
        }
        return scene;
    }

    public void startGame() {
        background.requestFocus();
        timeline.play();
        shieldImage.showShield();
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
        if (isGameOver || transitioningToNextLevel) {
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

        if (isSpaceBarHeld) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastFireTime >= FIRE_RATE_DELAY) {
                fireProjectile();
                lastFireTime = currentTime;
            }
        }

        if (isSpaceBarPressed) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastPressTime >= RAPID_FIRE_DELAY) {
                fireProjectile();
                lastPressTime = currentTime;
                isSpaceBarPressed = false;
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
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) user.moveUp();
                if (kc == KeyCode.DOWN) user.moveDown();
                if (kc == KeyCode.SPACE) {
                    if (!isSpaceBarHeld) {
                        isSpaceBarPressed = true;
                    }
                    isSpaceBarHeld = true;
                }
            }
        });

        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
                if (kc == KeyCode.SPACE) {
                    isSpaceBarHeld = false;
                }
            }
        });

        root.getChildren().add(background);
    }

    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
        levelView.addHitboxesToScene(projectile);
    }

    private void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
    }

    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
            levelView.addHitboxesToScene(projectile);
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
        List<ActiveActorDestructible> destroyedActors = actors.stream()
            .filter(actor -> actor.isDestroyed())
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
        levelView.addHitboxesToScene(enemy);
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
}