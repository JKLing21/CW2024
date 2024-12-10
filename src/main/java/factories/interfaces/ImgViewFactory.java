package factories.interfaces;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public interface ImgViewFactory {
	ImageView createImageView(String assetName, double width, double height);

	ImageView createPauseButton(double xPosition, double yPosition, double width, double height,
			EventHandler<MouseEvent> action);

	ImageView createResumeButton(double width, double height, EventHandler<MouseEvent> action);

	ImageView createSettingsButton(double width, double height, EventHandler<MouseEvent> action);

	ImageView createRestartButton(double width, double height, EventHandler<MouseEvent> action);

	ImageView createMainMenuButton(double width, double height, EventHandler<MouseEvent> action);

	ImageView createPauseMenuBackground(double width, double height, double translateX, double translateY);

	ImageView createPlaneImageView(double width, double height);

	ImageView createBackgroundImageView(double width, double height);
}
