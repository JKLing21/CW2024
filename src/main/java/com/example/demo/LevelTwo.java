package com.example.demo;

import com.example.demo.controller.Controller;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private final Boss boss;

	public LevelTwo(double screenHeight, double screenWidth, Controller controller) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, 5, controller);
	    boss = new Boss();
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
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
	    return new LevelView(getRoot(), getInitialHealth());
	}
	
	@Override
	protected int getKillTarget() {
	    return 0;
	}
}
