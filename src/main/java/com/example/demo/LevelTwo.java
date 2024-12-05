package com.example.demo;

import com.example.demo.controller.Controller;

public class LevelTwo extends LevelParent {
	
	private ActorFactory actorFactory;
	private final Boss boss;

	public LevelTwo(double screenHeight, double screenWidth, Controller controller,
			ComponentsFactory componentsFactory, AssetFactory assetFactory) {
		 super("background2", screenHeight, screenWidth, 5, controller, componentsFactory, assetFactory);
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
		return new LevelView(getRoot(), getInitialHealth(), getComponentsFactory());
	}

	@Override
	protected int getKillTarget() {
		return 0;
	}
}
