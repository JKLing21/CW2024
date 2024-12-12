package com.example.demo;

import com.example.demo.controller.Controller;

import factories.ActorImplement;
import factories.interfaces.ActorFactory;
import factories.interfaces.AssetFactory;
import factories.interfaces.ComponentsFactory;

public class LevelTwo extends LevelParent {

    public static final String BACKGROUND_IMAGE = "background2";
    private static final String NEXT_LEVEL = "com.example.demo.LevelThree";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 5;
    private static final long ENEMY_SPAWN_COOLDOWN = 1500;
    private long lastEnemySpawnTime = 0;
    private ActorFactory actorFactory;

    public LevelTwo(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
            Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory,
            AudioManager audioManager) {
        super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth, controller, componentsFactory,
                assetFactory, audioManager);
        this.actorFactory = new ActorImplement();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget())
			goToNextLevel(NEXT_LEVEL);
	}

    @Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		if (currentNumberOfEnemies >= TOTAL_ENEMIES) {
			return;
		}
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastEnemySpawnTime >= ENEMY_SPAWN_COOLDOWN) {
			double yUpperBound = 55;
	        double yLowerBound = getEnemyMaximumYPosition();
	        double newEnemyInitialYPosition = yUpperBound + Math.random() * (yLowerBound - yUpperBound);
			ActiveActorDestructible newEnemy = actorFactory.createEnemyPlane(getScreenWidth(),
					newEnemyInitialYPosition);
			addEnemyUnit(newEnemy);
			lastEnemySpawnTime = currentTime;
		}
	}

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), getInitialHealth(), getComponentsFactory(), getScreenWidth(), uiLayer);
    }

    @Override
	protected int getKillTarget() {
		return KILLS_TO_ADVANCE;
	}
    
    private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
