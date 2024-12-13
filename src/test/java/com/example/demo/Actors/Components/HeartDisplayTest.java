package com.example.demo.Actors.Components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Factories.Interfaces.ComponentsFactory;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HeartDisplayTest {

	private ComponentsFactory componentsFactoryMock;
	private HeartDisplay heartDisplay;
	private static final double X_POSITION = 100.0;
	private static final double Y_POSITION = 50.0;
	private static final int INITIAL_HEARTS = 5;

	@BeforeEach
	void setUp() {
		componentsFactoryMock = mock(ComponentsFactory.class);
		when(componentsFactoryMock.createHeartImage()).thenAnswer(invocation -> new ImageView());
		heartDisplay = new HeartDisplay(X_POSITION, Y_POSITION, INITIAL_HEARTS, componentsFactoryMock);
	}

	@Test
	void testContainerInitialization() {
		HBox container = heartDisplay.getContainer();
		assertNotNull(container, "Container should not be null.");
		assertEquals(X_POSITION, container.getLayoutX(), "Container X position should match.");
		assertEquals(Y_POSITION, container.getLayoutY(), "Container Y position should match.");
	}

	@Test
	void testInitialHeartsAdded() {
		HBox container = heartDisplay.getContainer();
		assertEquals(INITIAL_HEARTS, container.getChildren().size(),
				"Container should contain the correct number of hearts.");
	}

	@Test
	void testRemoveHeart() {
		HBox container = heartDisplay.getContainer();
		heartDisplay.removeHeart();
		assertEquals(INITIAL_HEARTS - 1, container.getChildren().size(),
				"One heart should be removed from the container.");
	}

	@Test
	void testRemoveHeartWhenEmpty() {
		HBox container = heartDisplay.getContainer();

		for (int i = 0; i < INITIAL_HEARTS; i++) {
			heartDisplay.removeHeart();
		}

		heartDisplay.removeHeart();
		assertEquals(0, container.getChildren().size(),
				"Container should remain empty when removing from an empty container.");
	}

	@Test
	void testHeartsPreserveRatio() {
		HBox container = heartDisplay.getContainer();

		for (int i = 0; i < INITIAL_HEARTS; i++) {
			ImageView heart = (ImageView) container.getChildren().get(i);
			assertEquals(50.0, heart.getFitHeight(), "Heart height should be fixed to 50.");
			assertTrue(heart.isPreserveRatio(), "Heart should preserve ratio.");
		}
	}
}
