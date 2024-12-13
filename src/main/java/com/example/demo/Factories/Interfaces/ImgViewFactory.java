package com.example.demo.Factories.Interfaces;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * The ImgViewFactory interface defines methods for creating various ImageView components.
 */
public interface ImgViewFactory {
	/**
     * Creates a new ImageView with the specified asset name, width, and height.
     *
     * @param assetName The name of the asset to be loaded.
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return A new ImageView containing the specified asset.
     */
	ImageView createImageView(String assetName, double width, double height);
	/**
     * Creates a new ImageView for a pause button with the specified position, size, and action.
     *
     * @param xPosition The x-coordinate of the pause button.
     * @param yPosition The y-coordinate of the pause button.
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the pause button.
     */
	ImageView createPauseButton(double xPosition, double yPosition, double width, double height,
			EventHandler<MouseEvent> action);
	/**
     * Creates a new ImageView for a resume button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the resume button.
     */
	ImageView createResumeButton(double width, double height, EventHandler<MouseEvent> action);
	/**
     * Creates a new ImageView for a settings button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the settings button.
     */
	ImageView createSettingsButton(double width, double height, EventHandler<MouseEvent> action);
	/**
     * Creates a new ImageView for a restart button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the restart button.
     */
	ImageView createRestartButton(double width, double height, EventHandler<MouseEvent> action);
	/**
     * Creates a new ImageView for a main menu button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the main menu button.
     */
	ImageView createMainMenuButton(double width, double height, EventHandler<MouseEvent> action);
	/**
     * Creates a new ImageView for a pause menu background with the specified size and translation.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param translateX The x-translation of the image.
     * @param translateY The y-translation of the image.
     * @return A new ImageView representing the pause menu background.
     */
	ImageView createPauseMenuBackground(double width, double height, double translateX, double translateY);
	/**
     * Creates a new ImageView for a plane with the specified size.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return A new ImageView representing the plane.
     */
	ImageView createPlaneImageView(double width, double height);
	/**
     * Creates a new ImageView for a background with the specified size.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return A new ImageView representing the background.
     */
	ImageView createBackgroundImageView(double width, double height);
}
