package factories;

import com.example.demo.Assets.ImgAssetLoader;

import factories.interfaces.ImgViewFactory;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImgViewFactoryImpl implements ImgViewFactory {

	private final ImgAssetLoader assetLoader;

	public ImgViewFactoryImpl() {
		this.assetLoader = new ImgAssetLoader() {
		};
	}

	@Override
	public ImageView createImageView(String assetName, double width, double height) {
		Image image = assetLoader.loadAsset(assetName);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}

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

	@Override
	public ImageView createResumeButton(double width, double height, EventHandler<MouseEvent> action) {

		Image resumeImage = assetLoader.loadAsset("ResumeIcon");
		ImageView imageView = new ImageView(resumeImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}

	@Override
	public ImageView createSettingsButton(double width, double height, EventHandler<MouseEvent> action) {

		Image settingsImage = assetLoader.loadAsset("SettingsIcon");
		ImageView imageView = new ImageView(settingsImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}

	@Override
	public ImageView createMainMenuButton(double width, double height, EventHandler<MouseEvent> action) {

		Image mainMenuImage = assetLoader.loadAsset("MainMenuIcon");
		ImageView imageView = new ImageView(mainMenuImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}

	@Override
	public ImageView createRestartButton(double width, double height, EventHandler<MouseEvent> action) {
		Image mainMenuImage = assetLoader.loadAsset("RestartIcon");
		ImageView imageView = new ImageView(mainMenuImage);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setOnMouseClicked(action);
		return imageView;
	}

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

	@Override
	public ImageView createPlaneImageView(double width, double height) {

		Image planeImage = assetLoader.loadAsset("userplane");
		ImageView planeImageView = new ImageView(planeImage);
		planeImageView.setFitWidth(width);
		planeImageView.setFitHeight(height);
		return planeImageView;
	}

	@Override
	public ImageView createBackgroundImageView(double width, double height) {

		Image backgroundImage = assetLoader.loadAsset("stormy_sky");
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(width);
		backgroundImageView.setFitHeight(height);
		return backgroundImageView;
	}

}
