package com.example.demo;

import factories.interfaces.ComponentsFactory;
/**
 * Implements the BossShielding interface which manages shielding mechanics for
 * boss plane.
 */
public class BossShieldStrategy implements BossShielding {
	private static final int MAX_FRAMES_WITH_SHIELD = 100;
	private static final int SHIELD_COOLDOWN = 300;
	private static final double BOSS_SHIELD_PROBABILITY = 0.008;

	private int framesWithShieldActivated = 0;
	private int framesSinceShieldDeactivated = SHIELD_COOLDOWN;
	private boolean isShielded = false;
	private ShieldImage shieldImage;
	private final BossHealthBar bossHealthBar;

	public BossShieldStrategy(ComponentsFactory factory, BossHealthBar bossHealthBar, double x, double y) {
		this.shieldImage = factory.createShieldImage(x, y);
		this.bossHealthBar = bossHealthBar;
	}
	/**
     * Updates shield status of bossplane.
     *
     * If shield is active, it increments the frame counter and deactivates shield if maximum duration is reached.
     * If shield is inactive, it increments cooldown counter and activates shield if cooldown period has passed
     * and activation probability is met.
     *
     * @param boss: Bossplane whose shield status is to be updated.
     */
	@Override
	public void updateShield(Boss boss) {
		if (isShielded) {
			framesWithShieldActivated++;
			if (shieldExhausted()) {
				deactivateShield();
			}
		} else {
			framesSinceShieldDeactivated++;
			if (framesSinceShieldDeactivated >= SHIELD_COOLDOWN && shouldActivateShield()) {
				activateShield();
				framesSinceShieldDeactivated = 0;
			}
		}
	}
	/**
     * Applies shield effect to bossplane.
     *
     * If shield is active, it shows the shield icon at the right side of boss's healthbar, applies glowing effect to bossplane.
     * If shield is inactive, it hides the shield icon, removes the glow effect on bossplane.
     *
     * @param boss: Bossplane which the shield effect is to be applied.
     */
	@Override
	public void applyShieldEffect(Boss boss) {
		if (isShielded) {
			shieldImage.showShield();
			boss.setEffect(new javafx.scene.effect.Glow(0.8));
			bossHealthBar.getShieldIcon().showShield();
		} else {
			shieldImage.hideShield();
			boss.setEffect(null);
			bossHealthBar.getShieldIcon().hideShield();
		}
	}
	/**
     * Checks if bossplane is shielded.
     *
     * @return true: if bossplane is shielded, false otherwise.
     */
	@Override
	public boolean isShielded() {
		return isShielded;
	}
	/**
     * Checks if bossplane shield's duration has been exhausted.
     *
     * @return true: if bossplane's shield has been activated for maximum number of frames, false otherwise.
     */
	private boolean shieldExhausted() {
		return framesWithShieldActivated >= MAX_FRAMES_WITH_SHIELD;
	}
	/**
     * Checks if bossplane shield should activate based on probabilistic check.
     *
     * @return true: if bossplane shield should activate, false otherwise.
     */
	private boolean shouldActivateShield() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}
	/**
     * Activates bossplane shield and resets frame counter.
     */
	private void activateShield() {
		isShielded = true;
		framesWithShieldActivated = 0;
	}
	/**
     * Deactivates bossplane shield and resets frame counters.
     */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		framesSinceShieldDeactivated = 0;
	}
}
