package com.example.demo;

public class LevelOne extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private static final long ENEMY_SPAWN_COOLDOWN = 1500;
	private long lastEnemySpawnTime = 0;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		lastEnemySpawnTime = System.currentTimeMillis();
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget())
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
	        ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
	        addEnemyUnit(newEnemy);
	        lastEnemySpawnTime = currentTime;
	    }
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}
	
	@Override
    protected int getKillTarget() {
        return KILLS_TO_ADVANCE;
    }

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}
