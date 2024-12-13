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
/**
 * ActorImplement class is responsible for creating various game actors, 
 * by implementing the ActorFactory interface.
 */
public class ActorImplement implements ActorFactory {
	
	private final ComponentsFactory componentsFactory;
	private final ImgAssetLoader assetLoader;
	/**
	 * Constructs new ActorImplement instance, initialising ComponentsFactory and ImgAssetLoader.
	 */
	public ActorImplement() {
		this.componentsFactory = new ComponentsImplement();
		this.assetLoader = new ImgAssetLoader() {};
	}
	/**
     * Creates new UserPlane with specified initial health, screen width, ProjectilesFactory, and AudioManager.
     *
     * @param initialHealth: initial health of user's plane.
     * @param screenWidth: width of the screen.
     * @param projectilesFactory: ProjectilesFactory used for projectiles creation.
     * @param audioManager: AudioManager used for playing audio.
     * @return new UserPlane instance.
     */
	@Override
	public UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory, AudioManager audioManager) {
		return new UserPlane(initialHealth, screenWidth, projectilesFactory, componentsFactory, assetLoader, audioManager);
	}
	/**
     * Creates new EnemyPlane at specified screen width and initial Y position.
     *
     * @param screenWidth: width of screen.
     * @param initialYPos: initial Y position of the enemy plane.
     * @return new EnemyPlane instance.
     */
	@Override
	public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
		MovementStrategy movementStrategy = new EnemyMovementStrategy();
		FiringStrategy firingStrategy = new EnemyFiringStrategy(new ProjectilesImplement()); 
		return new EnemyPlane(initialXPos, initialYPos, componentsFactory, assetLoader, movementStrategy, firingStrategy);
	}
	/**
     * Creates new WarPlane at specified screen width and initial Y position.
     *
     * @param screenWidth: width of screen.
     * @param initialYPos: initial Y position of the warplane.
     * @return new WarPlane instance.
     */
	@Override
	public WarPlane createWarPlane(double initialXPos, double initialYPos) {
		MovementStrategy movementStrategy = new WarPlaneMovementStrategy();
		FiringStrategy firingStrategy = new WarPlaneFiringStrategy(new ProjectilesImplement()); 
		return new WarPlane(initialXPos, initialYPos, componentsFactory, assetLoader, movementStrategy, firingStrategy);
	}
	/**
     * Creates new Boss instance.
     *
     * @return new Boss instance.
     */
	@Override
	public Boss createBoss() {
		return new Boss(componentsFactory, assetLoader);
	}
}