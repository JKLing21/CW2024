package com.example.demo.Factories;

import com.example.demo.Actors.Planes.Boss;
import com.example.demo.Actors.Planes.EnemyPlane;
import com.example.demo.Actors.Planes.UserPlane;
import com.example.demo.Actors.Planes.WarPlane;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ActorFactory;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Factories.Interfaces.ProjectilesFactory;
import com.example.demo.Managers.AudioManager;
import com.example.demo.Strategy.EnemyFiringStrategy;
import com.example.demo.Strategy.EnemyMovementStrategy;
import com.example.demo.Strategy.WarPlaneFiringStrategy;
import com.example.demo.Strategy.WarPlaneMovementStrategy;
import com.example.demo.Strategy.FiringStrategy;
import com.example.demo.Strategy.MovementStrategy;

public class ActorImplement implements ActorFactory {
	
	private final ComponentsFactory componentsFactory;
	private final ImgAssetLoader assetLoader;
	
	public ActorImplement() {
		this.componentsFactory = new ComponentsImplement();
		this.assetLoader = new ImgAssetLoader() {};
	}

	@Override
	public UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory, AudioManager audioManager) {
		return new UserPlane(initialHealth, screenWidth, projectilesFactory, componentsFactory, assetLoader, audioManager);
	}

	@Override
	public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
		MovementStrategy movementStrategy = new EnemyMovementStrategy();
		FiringStrategy firingStrategy = new EnemyFiringStrategy(new ProjectilesImplement()); 
		return new EnemyPlane(initialXPos, initialYPos, componentsFactory, assetLoader, movementStrategy, firingStrategy);
	}
	
	@Override
	public WarPlane createWarPlane(double initialXPos, double initialYPos) {
		MovementStrategy movementStrategy = new WarPlaneMovementStrategy();
		FiringStrategy firingStrategy = new WarPlaneFiringStrategy(new ProjectilesImplement()); 
		return new WarPlane(initialXPos, initialYPos, componentsFactory, assetLoader, movementStrategy, firingStrategy);
	}

	@Override
	public Boss createBoss() {
		return new Boss(componentsFactory, assetLoader);
	}
}