package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import factories.ProjectilesImplement;
import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;

public class Boss extends FighterPlane {

	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 20.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = 0.008;
	private static final int IMAGE_HEIGHT = 75;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 100;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = 0;
	private static final int Y_POSITION_LOWER_BOUND = 655;
	private static final int MAX_FRAMES_WITH_SHIELD = 100;
	private static final int SHIELD_COOLDOWN = 300;
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private final Image bossImage;
	private ShieldImage shieldImage;
	private int framesWithShieldActivated;
	private int framesSinceShieldDeactivated;
	private final BossHealthBar bossHealthBar;

	private ProjectilesFactory projectileFactory;

	public Boss(ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH, factory);
		movePattern = new ArrayList<>();
		this.bossImage = assetLoader.loadImage("bossplane");
		ImageProperties.applyProperties(this, bossImage, INITIAL_X_POSITION, INITIAL_Y_POSITION, IMAGE_HEIGHT, null, true);
		this.bossHealthBar = new BossHealthBar(500, factory);
		this.shieldImage = factory.createShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION);
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		framesSinceShieldDeactivated = SHIELD_COOLDOWN;
		isShielded = false;
		initializeMovePattern();
		this.projectileFactory = new ProjectilesImplement();
		if (getScene() != null) {
			Group parentContainer = (Group) getScene().getRoot();
			parentContainer.getChildren().addAll(bossHealthBar.getHealthBarBackground(), bossHealthBar.getHealthBar(),
					bossHealthBar.getBossNameText(), shieldImage);
		}
	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
		updateShieldEffect();
		if (shieldImage.isVisible()) {
			shieldImage.setLayoutX(getLayoutX());
			shieldImage.setLayoutY(getLayoutY());
		}
		bossHealthBar.updatePosition(400, 20);
	}

	public BossHealthBar getBossHealthBar() {
		return bossHealthBar;
	}

	public javafx.scene.shape.Rectangle getHealthBar() {
		return bossHealthBar.getHealthBar();
	}

	public javafx.scene.shape.Rectangle getHealthBarBackground() {
		return bossHealthBar.getHealthBarBackground();
	}

	public javafx.scene.text.Text getBossNameText() {
		return bossHealthBar.getBossNameText();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? projectileFactory.createBossProjectile(getProjectileInitialPosition())
				: null;
	}

	@Override
	public void takeDamage() {
		if (isShielded) {
			setEffect(new Glow(0.8));
		} else {
			super.takeDamage();
			setEffect(null);
			double healthPercentage = (double) getHealth() / getMaxHealth();
			bossHealthBar.updateHealth(healthPercentage);
		}
	}

	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			if (shieldExhausted()) {
				deactivateShield();
			}
		} else {
			framesSinceShieldDeactivated++;
			if (shieldShouldBeActivated() && framesSinceShieldDeactivated >= SHIELD_COOLDOWN) {
				activateShield();
				framesSinceShieldDeactivated = 0;
			}
		}
	}

	public void updateShieldEffect() {
		if (isShielded) {
			shieldImage.showShield();
			setEffect(new Glow(0.8));
			bossHealthBar.getShieldIcon().showShield();
		} else {
			shieldImage.hideShield();
			setEffect(null);
			bossHealthBar.getShieldIcon().hideShield();
		}
	}

	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	private boolean shieldExhausted() {
		return framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD;
	}

	private void activateShield() {
		isShielded = true;
		framesWithShieldActivated = 0;
	}

	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		framesSinceShieldDeactivated = 0;
	}

}