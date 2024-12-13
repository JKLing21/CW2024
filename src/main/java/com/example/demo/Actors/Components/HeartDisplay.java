package com.example.demo.Actors.Components;

import com.example.demo.Factories.Interfaces.ComponentsFactory;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
/**
 * HeartDisplay class is responsible for managing and displaying heart images in a defined container
 * in horizontal layout. The container allows for replenishment and removal of heart images based on
 * number of hearts to be displayed.
 */
public class HeartDisplay {

	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;
	/**
	 * Constructs HeartDisplay object with specified position, number of hearts and ComponentsFactory.
	 *
	 * @param xPosition: x-coordinate of heart images container's position.
	 * @param yPosition: y-coordinate of heart images container's position.
	 * @param heartsToDisplay: Number of heart images to be displayed at the start of game.
	 * @param componentsFactory: ComponentsFactory used for heart images creation.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay, ComponentsFactory componentsFactory) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts(componentsFactory);
	}
	/**
	 * Initialises container which contains the heart images.
	 * It's a HBox container which is positioned at specified x and y coordinates.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}
	/**
	 * Initialises and replenish the specified number of heart images into container.
	 * Each and every heart images is created using ComponentsFactory and set to a fixed height.
	 *
	 * @param componentsFactory: ComponentsFactory used for heart images creation.
	 */
	private void initializeHearts(ComponentsFactory componentsFactory) {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = componentsFactory.createHeartImage();
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}
	/**
	 * Removes first heart image from container.
	 * If container is not empty, first heart image is then removed.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}
	/**
	 * Returns container which contains heart images.
	 *
	 * @return HBox container containing heart images.
	 */
	public HBox getContainer() {
		return container;
	}

}
