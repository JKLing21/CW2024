package com.example.demo.Actors.Components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.example.demo.Factories.Interfaces.ComponentsFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

class BossHealthBarTest {

	private BossHealthBar bossHealthBar;
	@Mock
	private ComponentsFactory factory;

	private final double healthBarWidth = 200;

	@BeforeEach
	void setUp() {
		factory = mock(ComponentsFactory.class);

		Rectangle mockedHealthBar = new Rectangle();
		Rectangle mockedHealthBarBackground = new Rectangle();
		Text mockedBossNameText = new Text();
		Shield mockedShieldIcon = mock(Shield.class);

		when(factory.createHealthBar(anyDouble(), anyDouble(), any(Color.class))).thenReturn(mockedHealthBar);
		when(factory.createHealthBarBackground(anyDouble(), anyDouble(), any(Color.class)))
				.thenReturn(mockedHealthBarBackground);
		when(factory.createBossNameText(anyString(), any(), any(Color.class))).thenReturn(mockedBossNameText);
		when(factory.createShieldIcon(anyDouble(), anyDouble(), anyDouble())).thenReturn(mockedShieldIcon);

		bossHealthBar = new BossHealthBar(healthBarWidth, factory);
	}

	@Test
	void testGetHealthBar() {
		assertNotNull(bossHealthBar.getHealthBar());
	}

	@Test
	void testGetHealthBarBackground() {
		assertNotNull(bossHealthBar.getHealthBarBackground());
	}

	@Test
	void testGetBossNameText() {
		assertNotNull(bossHealthBar.getBossNameText());
	}

	@Test
	void testGetShieldIcon() {
		assertNotNull(bossHealthBar.getShieldIcon());
	}

	@Test
	void testUpdatePosition() {
		double x = 100;
		double y = 50;
		double width = 200;

		bossHealthBar.updatePosition(x, y);

		Rectangle healthBarBackground = bossHealthBar.getHealthBarBackground();
		assertEquals(x, healthBarBackground.getX());
		assertEquals(y, healthBarBackground.getY());

		Text bossNameText = bossHealthBar.getBossNameText();
		bossNameText.applyCss();

		System.out.println("Width of health bar: " + width);
		System.out.println("Width of boss name text: " + bossNameText.getLayoutBounds().getWidth());

		double calculatedTextX = x + (width - bossNameText.getLayoutBounds().getWidth()) / 2;
		System.out.println("Calculated Text X: " + calculatedTextX);

		assertEquals(calculatedTextX, bossNameText.getX(), 0.1, "Boss Name Text X-coordinate mismatch");
	}

	@Test
	void testUpdateHealth() {
		double healthPercentage = 0.75;

		bossHealthBar.updateHealth(healthPercentage);

		Rectangle healthBar = bossHealthBar.getHealthBar();
		assertEquals(healthBarWidth * healthPercentage, healthBar.getWidth());
	}
}
