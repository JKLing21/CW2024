package com.example.demo;

import factories.ProjectilesImplement;
import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;

public class Boss extends FighterPlane {

	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 38.0;
	private static final int IMAGE_HEIGHT = 75;
	private static final int HEALTH = 100;
	private final Image bossImage;
	private ShieldImage shieldImage;
	private final BossHealthBar bossHealthBar;

	private ProjectilesFactory projectileFactory;
	private final MovementStrategy movementStrategy;
	private FiringStrategy firingStrategy;
	private final BossShielding bossShielding;

	public Boss(ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH, factory);
		this.bossImage = assetLoader.loadImage("bossplane");
		ImageProperties.applyProperties(this, bossImage, INITIAL_X_POSITION, INITIAL_Y_POSITION, IMAGE_HEIGHT, null, true);
		this.bossHealthBar = new BossHealthBar(500, factory);
		this.bossShielding = new BossShieldStrategy(factory, bossHealthBar, INITIAL_X_POSITION, INITIAL_Y_POSITION);
		this.projectileFactory = new ProjectilesImplement();
		this.movementStrategy = new BossMovementStrategy();
		this.firingStrategy = new BossFiringStrategy(projectileFactory);
		if (getScene() != null) {
			Group parentContainer = (Group) getScene().getRoot();
			parentContainer.getChildren().addAll(bossHealthBar.getHealthBarBackground(), bossHealthBar.getHealthBar(),
					bossHealthBar.getBossNameText(), shieldImage);
		}
	}

	@Override
	public void updatePosition() {
		movementStrategy.move(this);
	}

	@Override
	public void updateActor() {
		updatePosition();
		bossShielding.updateShield(this);
		bossShielding.applyShieldEffect(this);
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
	    return firingStrategy.fire(this);
	}

	@Override
	public void takeDamage() {
		if (bossShielding.isShielded()) {
		    setEffect(new Glow(0.8));
		} else {
		    super.takeDamage();
		    setEffect(null);
		    double healthPercentage = (double) getHealth() / getMaxHealth();
		    bossHealthBar.updateHealth(healthPercentage);
		}

	}

	public double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}
}