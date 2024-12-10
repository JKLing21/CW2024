package com.example.demo;

import factories.interfaces.ComponentsFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 * Represents the bossplane health bar and related UI components
 * Including the health bar, health bar background, bossplane's name text label, 
 * and shield icon indicating the bossplane's shielding status.
 */
public class BossHealthBar {
	private final Rectangle healthBar;
	private final Rectangle healthBarBackground;
	private final double width;
	private final Text bossNameText;
	private final ShieldImage shieldIcon;
	/**
     * Constructs BossHealthBar with specified width and components factory.
     *
     * @param width: width of health bar.
     * @param factory: Componentsfactory used to create health bar components.
     */
	public BossHealthBar(double width, ComponentsFactory factory) {
		this.width = width;
		healthBarBackground = factory.createHealthBarBackground(width, 15, Color.MAROON);
		healthBar = factory.createHealthBar(width, 15, Color.RED);
		bossNameText = factory.createBossNameText("Skybound Tyrant", Font.font("Arial", FontWeight.BOLD, 18), Color.WHITE);
		shieldIcon = factory.createShieldIcon(0, 0, 35);
	}
	/**
     * Gets the rectangle representing bossplane's health bar.
     *
     * @return health bar rectangle.
     */
	public Rectangle getHealthBar() {
		return healthBar;
	}
	/**
     * Gets the background rectangle behind health bar.
     *
     * @return health bar background rectangle.
     */
	public Rectangle getHealthBarBackground() {
		return healthBarBackground;
	}
	/**
     * Gets the bossplane's name text label.
     *
     * @return bossplane's name text.
     */
	public Text getBossNameText() {
		return bossNameText;
	}
	/**
     * Gets the shield icon which indicates bossplane's shielding status.
     *
     * @return shield icon.
     */
	public ShieldImage getShieldIcon() {
		return shieldIcon;
	}
	/**
     * Updates position of health bar and its associated components.
     *
     * @param x: x-coordinate of health bar's position.
     * @param y: y-coordinate of health bar's position.
     */
	public void updatePosition(double x, double y) {
		healthBarBackground.setX(x);
		healthBarBackground.setY(y);
		healthBar.setX(x);
		healthBar.setY(y);

		double textX = x + (width - bossNameText.getLayoutBounds().getWidth()) / 2;
		double textY = y - 5;
		bossNameText.setX(textX);
		bossNameText.setY(textY);

		shieldIcon.setLayoutX(x + healthBarBackground.getWidth() + 25);
		shieldIcon.setLayoutY(y + healthBarBackground.getHeight() - 25);
	}
	/**
     * Updates width of health bar based on bossplane's health percentage.
     *
     * @param healthPercentage: bossplane's health percentage.
     */
	public void updateHealth(double healthPercentage) {
		healthBar.setWidth(width * healthPercentage);
	}
}
