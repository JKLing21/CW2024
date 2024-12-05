package com.example.demo;

import com.example.demo.controller.Controller;

public class LevelOne extends LevelParent {
	
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 5;
	private static final long ENEMY_SPAWN_COOLDOWN = 1500;
	private long lastEnemySpawnTime = 0;

	private final ActorFactory actorFactory;

	public LevelOne(double screenHeight, double screenWidth, Controller controller,
	        ComponentsFactory componentsFactory, AssetFactory assetFactory) {
	    super("background1", screenHeight, screenWidth, 5, controller, componentsFactory, assetFactory);
	    this.actorFactory = new ActorImplement();
	    lastEnemySpawnTime = System.currentTimeMillis();
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget())
			goToNextLevel(NEXT_LEVEL);
	}

	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		if (currentNumberOfEnemies >= TOTAL_ENEMIES) {
			return;
		}
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastEnemySpawnTime >= ENEMY_SPAWN_COOLDOWN) {
			double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
			ActiveActorDestructible newEnemy = actorFactory.createEnemyPlane(getScreenWidth(),
					newEnemyInitialYPosition);
			addEnemyUnit(newEnemy);
			lastEnemySpawnTime = currentTime;
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), getInitialHealth(), getComponentsFactory());
	}

	@Override
	protected int getKillTarget() {
		return KILLS_TO_ADVANCE;
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
