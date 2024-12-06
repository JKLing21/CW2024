package factories.interfaces;

import com.example.demo.GameOverImage;
import com.example.demo.HeartDisplay;
import com.example.demo.LevelParent;
import com.example.demo.LevelView;
import com.example.demo.MainMenu;
import com.example.demo.PauseScreen;
import com.example.demo.ShieldImage;
import com.example.demo.WinImage;
import com.example.demo.controller.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public interface ComponentsFactory {

	ImgViewFactory getImgViewFactory();

	Rectangle createHealthBar(double width, double height, Color fill);

	Rectangle createHealthBarBackground(double width, double height, Color fill);

	Text createBossNameText(String name, Font font, Color color);

	ShieldImage createShieldIcon(double xPosition, double yPosition, double size);

	GameOverImage createGameOverImage(double xPosition, double yPosition);

	HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay);

	ImageView createHeartImage();

	LevelView createLevelView(Group root, int heartsToDisplay);

	MainMenu createMainMenu(Controller controller);

	PauseScreen createPauseScreen(Group root, Scene scene, LevelParent levelParent, Controller controller);

	ShieldImage createShieldImage(double xPosition, double yPosition);

	WinImage createWinImage(double xPosition, double yPosition);

	Rectangle createHitbox(double width, double height, Color color, Color stroke);

	Label createLabel(String text, String... styleClasses);

	Button createButton(String text, EventHandler<ActionEvent> action);

	Slider createSlider(double min, double max, double value, double width);

	ToggleButton createToggleButton(boolean selected);

	Button createMenuButton(String text, EventHandler<ActionEvent> action);

	Text createKillCountText();
}
