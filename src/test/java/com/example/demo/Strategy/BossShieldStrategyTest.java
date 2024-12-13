package com.example.demo.Strategy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Actors.Components.BossHealthBar;
import com.example.demo.Actors.Components.Shield;
import com.example.demo.Actors.Planes.Boss;
import com.example.demo.Factories.Interfaces.ComponentsFactory;

class BossShieldStrategyTest {

	@Mock
	private ComponentsFactory mockFactory;

	@Mock
	private BossHealthBar mockHealthBar;

	@Mock
	private Boss mockBoss;

	@Mock
	private Shield mockShield;

	@Mock
	private Shield mockShieldIcon;

	private BossShieldStrategy bossShieldStrategy;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(mockFactory.createShieldImage(anyDouble(), anyDouble())).thenReturn(mockShield);
		when(mockHealthBar.getShieldIcon()).thenReturn(mockShieldIcon);
		bossShieldStrategy = new BossShieldStrategy(mockFactory, mockHealthBar, 0.0, 0.0);
	}

	@Test
	void testUpdateShield_ShouldActivateShield() {
		doNothing().when(mockShield).showShield();
		doNothing().when(mockShieldIcon).showShield();

		bossShieldStrategy.activateShield();

		bossShieldStrategy.updateShield(mockBoss);

		bossShieldStrategy.applyShieldEffect(mockBoss);

		verify(mockShield, times(1)).showShield();
		verify(mockShieldIcon, times(1)).showShield();
		verify(mockBoss, times(1)).setEffect(any());
	}

	@Test
	void testUpdateShield_ShouldDeactivateShield() {
		bossShieldStrategy.activateShield();
		bossShieldStrategy.updateShield(mockBoss);

		for (int i = 0; i < BossShieldStrategy.MAX_FRAMES_WITH_SHIELD; i++) {
			bossShieldStrategy.updateShield(mockBoss);
		}

		bossShieldStrategy.applyShieldEffect(mockBoss);
		verify(mockShield, times(1)).hideShield();
	}

	@Test
	void testApplyShieldEffect_ShouldShowShield() {
		Shield mockShieldIcon = mock(Shield.class);
		when(mockHealthBar.getShieldIcon()).thenReturn(mockShieldIcon);

		bossShieldStrategy.activateShield();

		bossShieldStrategy.applyShieldEffect(mockBoss);

		verify(mockShieldIcon, times(1)).showShield();
		verify(mockBoss, times(1)).setEffect(any());
	}

	@Test
	void testApplyShieldEffect_ShouldHideShield() throws Exception {

		Shield mockShieldIcon = mock(Shield.class);
		when(mockHealthBar.getShieldIcon()).thenReturn(mockShieldIcon);

		Method deactivateShieldMethod = BossShieldStrategy.class.getDeclaredMethod("deactivateShield");
		deactivateShieldMethod.setAccessible(true);
		deactivateShieldMethod.invoke(bossShieldStrategy);

		bossShieldStrategy.applyShieldEffect(mockBoss);

		verify(mockShieldIcon, times(1)).hideShield();
		verify(mockBoss, times(1)).setEffect(null);
	}

	@Test
	void testIsShielded_ShouldReturnTrueWhenShieldActive() {
		bossShieldStrategy.activateShield();

		assertTrue(bossShieldStrategy.isShielded());
	}

	@Test
	void testIsShielded_ShouldReturnFalseWhenShieldInactive() {
		assertFalse(bossShieldStrategy.isShielded());
	}
}
