package com.example.demo.Actors.Planes;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Actors.Components.BossHealthBar;
import com.example.demo.Actors.Components.Shield;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.ProjectilesImplement;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Factories.Interfaces.ProjectilesFactory;
import com.example.demo.Strategy.BossFiringStrategy;
import com.example.demo.Strategy.BossMovementStrategy;
import com.example.demo.Strategy.BossShieldStrategy;
import com.example.demo.Strategy.BossShielding;
import com.example.demo.Strategy.FiringStrategy;
import com.example.demo.Strategy.MovementStrategy;
import com.example.demo.utils.ImageProperties;

import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
/**
 * Boss class represents boss fighter plane in game.
 * Boss class extends FighterPlane class and implements specific behaviors for bossplane,
 * such as strategies for movement, firing and shielding.
 */
public class Boss extends FighterPlane {

	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 38.0;
	private static final int IMAGE_HEIGHT = 75;
	private static final int HEALTH = 100;
	private final Image bossImage;
	private Shield shieldImage;
	private final BossHealthBar bossHealthBar;

	private ProjectilesFactory projectileFactory;
	private final MovementStrategy movementStrategy;
	private FiringStrategy firingStrategy;
	private final BossShielding bossShielding;
	/**
	 * Constructs new boss instance.
	 * Initialises bossplane with its image, health bar, shield and each different strategies 
	 * such as strategies for movement, firing and shielding.
	 *
	 * @param factory: ComponentsFactory used for components creation.
	 * @param assetLoader: ImgAssetLoader used for loading images.
	 */
	public Boss(ComponentsFactory factory, ImgAssetLoader assetLoader) {
		super(IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH, factory);
		this.bossImage = assetLoader.loadAsset("bossplane");
		ImageProperties.applyProperties(this, bossImage, INITIAL_X_POSITION, INITIAL_Y_POSITION, IMAGE_HEIGHT, null,
				true);
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
	/**
	 * Updates position of bossplane based on boss's movement strategy.
	 */
	@Override
	public void updatePosition() {
		movementStrategy.move(this);
	}
	/**
	 * Updates bossplane actor by updating its position, shield and the health bar.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		bossShielding.updateShield(this);
		bossShielding.applyShieldEffect(this);
		bossHealthBar.updatePosition(400, 20);
	}
	/**
	 * Gets bossplane's health bar.
	 *
	 * @return BossHealthBar instance.
	 */
	public BossHealthBar getBossHealthBar() {
		return bossHealthBar;
	}
	/**
	 * Gets bossplane's health bar rectangle.
	 *
	 * @return health bar rectangle.
	 */
	public javafx.scene.shape.Rectangle getHealthBar() {
		return bossHealthBar.getHealthBar();
	}
	/**
	 * Gets bossplane's health bar background rectangle.
	 *
	 * @return bossplane's health bar background rectangle.
	 */
	public javafx.scene.shape.Rectangle getHealthBarBackground() {
		return bossHealthBar.getHealthBarBackground();
	}
	/**
	 * Gets bossplane's name text.
	 *
	 * @return bossplane's name text.
	 */
	public javafx.scene.text.Text getBossNameText() {
		return bossHealthBar.getBossNameText();
	}
	/**
	 * Fires boss projectile based on boss's firing strategy.
	 *
	 * @return fired projectile as ActiveActorDestructible.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return firingStrategy.fire(this);
	}
	/**
	 * Handles damage taking of bossplane.
	 * If boss is shielded, applies the glowing effect; 
	 * otherwise, reduces boss's health and updates health bar.
	 */
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
	/**
	 * Gets initial Y position for boss's projectile.
	 *
	 * @return initial Y position of boss's projectile.
	 */
	public double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}
}