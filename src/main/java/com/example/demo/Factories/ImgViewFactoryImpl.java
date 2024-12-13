package com.example.demo.Factories;

import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ImgViewFactory;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * ImgViewFactoryImpl class is responsible for creating various ImageView components by implementing the ImgViewFactory interface.
 */
public class ImgViewFactoryImpl implements ImgViewFactory {

	private final ImgAssetLoader assetLoader;
	/**
     * Constructs new ImgViewFactoryImpl instance, initialising the ImgAssetLoader.
     */
	public ImgViewFactoryImpl() {
		this.assetLoader = new ImgAssetLoader() {
		};
	}
	/**
     * Creates a new ImageView with the specified asset name, width, and height.
     *
     * @param assetName: The name of the asset to be loaded.
     * @param width: The width to which the image should be scaled.
     * @param height: The height to which the image should be scaled.
     * @return A new ImageView containing the specified asset.
     */
	@Override
	public ImageView createImageView(String assetName, double width, double height) {
		Image image = assetLoader.loadAsset(assetName);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}
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
	@Override
	public ImageView createPauseButton(double xPosition, double yPosition, double width, double height,
			EventHandler<MouseEvent> action) {

		Image pauseImage = assetLoader.loadAsset("PauseIcon");
		ImageView pauseButton = new ImageView(pauseImage);
		pauseButton.setFitWidth(width);
		pauseButton.setFitHeight(height);
		pauseButton.setLayoutX(xPosition);
		pauseButton.setLayoutY(yPosition);
		pauseButton.setOnMouseClicked(action);
		return pauseButton;
	}
	/**
     * Creates a new ImageView for a resume button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the resume button.
     */
	@Override
	public ImageView createResumeButton(double width, double height, EventHandler<MouseEvent> action) {

		Image resumeImage = assetLoader.loadAsset("ResumeIcon");
		ImageView imageView = new ImageView(resumeImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}
	/**
     * Creates a new ImageView for a settings button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the settings button.
     */
	@Override
	public ImageView createSettingsButton(double width, double height, EventHandler<MouseEvent> action) {

		Image settingsImage = assetLoader.loadAsset("SettingsIcon");
		ImageView imageView = new ImageView(settingsImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}
	/**
     * Creates a new ImageView for a main menu button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the main menu button.
     */
	@Override
	public ImageView createMainMenuButton(double width, double height, EventHandler<MouseEvent> action) {

		Image mainMenuImage = assetLoader.loadAsset("MainMenuIcon");
		ImageView imageView = new ImageView(mainMenuImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}
	/**
     * Creates a new ImageView for a restart button with the specified size and action.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param action The action to be performed when the button is clicked.
     * @return A new ImageView representing the restart button.
     */
	@Override
	public ImageView createRestartButton(double width, double height, EventHandler<MouseEvent> action) {
		Image mainMenuImage = assetLoader.loadAsset("RestartIcon");
		ImageView imageView = new ImageView(mainMenuImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}
	/**
     * Creates a new ImageView for a pause menu background with the specified size and translation.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @param translateX The x-translation of the image.
     * @param translateY The y-translation of the image.
     * @return A new ImageView representing the pause menu background.
     */
	@Override
	public ImageView createPauseMenuBackground(double width, double height, double translateX, double translateY) {

		Image backgroundImage = assetLoader.loadAsset("rectangle_container");
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(width);
		backgroundImageView.setFitHeight(height);
		backgroundImageView.setTranslateX(translateX);
		backgroundImageView.setTranslateY(translateY);
		return backgroundImageView;
	}
	/**
     * Creates a new ImageView for a plane with the specified size.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return A new ImageView representing the plane.
     */
	@Override
	public ImageView createPlaneImageView(double width, double height) {

		Image planeImage = assetLoader.loadAsset("userplane");
		ImageView planeImageView = new ImageView(planeImage);
		planeImageView.setFitWidth(width);
		planeImageView.setFitHeight(height);
		return planeImageView;
	}
	/**
     * Creates a new ImageView for a background with the specified size.
     *
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return A new ImageView representing the background.
     */
	@Override
	public ImageView createBackgroundImageView(double width, double height) {

		Image backgroundImage = assetLoader.loadAsset("stormy_sky");
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(width);
		backgroundImageView.setFitHeight(height);
		return backgroundImageView;
	}

}
