package factories;

import com.example.demo.AudioManager;
import com.example.demo.Boss;
import com.example.demo.EnemyPlane;
import com.example.demo.ImgAssetLoader;
import com.example.demo.UserPlane;
import com.example.demo.WarPlane;
import com.example.demo.Strategy.EnemyFiringStrategy;
import com.example.demo.Strategy.EnemyMovementStrategy;
import com.example.demo.Strategy.WarPlaneFiringStrategy;
import com.example.demo.Strategy.WarPlaneMovementStrategy;
import com.example.demo.Strategy.FiringStrategy;
import com.example.demo.Strategy.MovementStrategy;

import factories.interfaces.ActorFactory;
import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;

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