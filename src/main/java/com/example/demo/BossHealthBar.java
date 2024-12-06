package com.example.demo;

import factories.interfaces.ComponentsFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BossHealthBar {
	private final Rectangle healthBar;
	private final Rectangle healthBarBackground;
	private final double width;
	private final Text bossNameText;
	private final ShieldImage shieldIcon;

	public BossHealthBar(double width, ComponentsFactory factory) {
		this.width = width;
		healthBarBackground = factory.createHealthBarBackground(width, 15, Color.MAROON);
		healthBar = factory.createHealthBar(width, 15, Color.RED);
		 bossNameText = factory.createBossNameText("Skybound Tyrant", Font.font("Arial", FontWeight.BOLD, 18), Color.WHITE);
		shieldIcon = factory.createShieldIcon(0, 0, 35);
	}

	public Rectangle getHealthBar() {
		return healthBar;
	}

	public Rectangle getHealthBarBackground() {
		return healthBarBackground;
	}

	public Text getBossNameText() {
		return bossNameText;
	}

	public ShieldImage getShieldIcon() {
		return shieldIcon;
	}

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

	public void updateHealth(double healthPercentage) {
		healthBar.setWidth(width * healthPercentage);
	}
}
