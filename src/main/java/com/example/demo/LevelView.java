package com.example.demo;

import factories.interfaces.ComponentsFactory;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = 50;
	private static final int LOSS_SCREEN_Y_POSITION = 85;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private Text killCountText;

	public LevelView(Group root, int heartsToDisplay, ComponentsFactory componentsFactory) {
		this.root = root;
		this.heartDisplay = componentsFactory.createHeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION,
				heartsToDisplay);
		this.winImage = componentsFactory.createWinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = componentsFactory.createGameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
		this.killCountText = componentsFactory.createKillCountText();

		root.getChildren().add(killCountText);
	}

	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		removeWinImage();
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	public void removeWinImage() {
		root.getChildren().remove(winImage);
	}

	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public void addHitboxesToScene(Group root, ActiveActor... actors) {
		for (ActiveActor actor : actors) {
			Rectangle hitbox = actor.getHitbox();
			if (!root.getChildren().contains(hitbox)) {
				root.getChildren().add(hitbox);
			}
		}
	}

	public void removeHitboxesFromScene(ActiveActor... actors) {
		for (ActiveActor actor : actors) {
			actor.removeHitboxFromScene(root);
		}
	}

	public void updateKillCount(int killCount) {
		killCountText.setText("Kills: " + killCount);
	}
}
