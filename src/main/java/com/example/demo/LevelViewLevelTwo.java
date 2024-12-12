package com.example.demo;

import factories.interfaces.ComponentsFactory;
import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;

	public LevelViewLevelTwo(Group root, int heartsToDisplay, ComponentsFactory componentsFactory, double screenWidth, Group uiLayer) {
		super(root, heartsToDisplay, componentsFactory, screenWidth, uiLayer);
		this.root = root;
		this.shieldImage = componentsFactory.createShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	public void showShield() {
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

}
