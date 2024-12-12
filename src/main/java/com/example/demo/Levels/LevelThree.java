package com.example.demo.Levels;

import com.example.demo.Actors.Planes.Boss;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Screens.LevelScreen;
import com.example.demo.Screens.TransitionScene;
import com.example.demo.Screens.WinScreen;
import com.example.demo.controller.Controller;
import factories.ActorImplement;
import factories.interfaces.ActorFactory;
import factories.interfaces.AssetFactory;
import factories.interfaces.ComponentsFactory;
import javafx.scene.Group;
import javafx.stage.Stage;

public class LevelThree extends LevelParent {

	public static final String BACKGROUND_IMAGE = "background3";
	private ActorFactory actorFactory;
	private final ImgAssetLoader imgAssetLoader;
	private final Stage stage;
	private final Boss boss;

	public LevelThree(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,
			Controller controller, ComponentsFactory componentsFactory, AssetFactory assetFactory, AudioManager audioManager, Stage stage) {
		super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth, controller, componentsFactory,
				assetFactory, audioManager);
		this.imgAssetLoader = new ImgAssetLoader() {};
		this.actorFactory = new ActorImplement();
		this.boss = actorFactory.createBoss();
		this.levelView = instantiateLevelView();
		this.stage = stage;
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
	        checkWinCondition();
	    }
	}
	
	private void checkWinCondition() {
		 if (boss.isDestroyed()) {
		        timeline.stop();
		        audioManager.stopBackgroundMusic();
		        TransitionScene.fadeOutCurrentScene(stage, () -> {
		            WinScreen winScreen = new WinScreen(stage, imgAssetLoader, componentsFactory, this, controller);
		            winScreen.showWinScreen();
		        });
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
	protected LevelScreen instantiateLevelView() {
		Group uiLayer = new Group();
		return new LevelScreen(getRoot(), getInitialHealth(), getComponentsFactory(), getScreenWidth(), uiLayer);
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
