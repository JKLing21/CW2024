package com.example.demo;

import com.example.demo.controller.Controller;

import factories.ActorImplement;
import factories.interfaces.ActorFactory;
import factories.interfaces.AssetFactory;
import factories.interfaces.ComponentsFactory;
import javafx.scene.Group;

public class LevelThree extends LevelParent {

	public static final String BACKGROUND_IMAGE = "background2";
	private ActorFactory actorFactory;
	private final Boss boss;

	public LevelThree(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
			Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory, AudioManager audioManager) {
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth, controller, componentsFactory,
				assetFactory, audioManager);
		this.actorFactory = new ActorImplement();
		this.boss = actorFactory.createBoss();
		this.levelView = instantiateLevelView();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			if (!getRoot().getChildren().contains(boss.getHealthBarBackground())) {
				getRoot().getChildren().addAll(boss.getHealthBarBackground(), boss.getHealthBar(),
						boss.getBossNameText(), boss.getBossHealthBar().getShieldIcon());
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		Group uiLayer = new Group();
		return new LevelView(getRoot(), getInitialHealth(), getComponentsFactory(), getScreenWidth(), uiLayer);
	}

	@Override
	protected int getKillTarget() {
		return 0;
	}
	
	@Override
	protected boolean isLevelOne() {
	    return false;
	}
	
	@Override
	protected boolean shouldShowKillCount() {
	    return false;
	}

}
