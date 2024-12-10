package factories;

import com.example.demo.Boss;
import com.example.demo.FiringStrategy;
import com.example.demo.EnemyMovementStrategy;
import com.example.demo.EnemyFiringStrategy;
import com.example.demo.EnemyPlane;
import com.example.demo.ImgAssetLoader;
import com.example.demo.MovementStrategy;
import com.example.demo.UserPlane;

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
	public UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory) {
		return new UserPlane(initialHealth, screenWidth, projectilesFactory, componentsFactory, assetLoader);
	}

	@Override
	public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
		MovementStrategy movementStrategy = new EnemyMovementStrategy();
		FiringStrategy firingStrategy = new EnemyFiringStrategy(new ProjectilesImplement()); 
		return new EnemyPlane(initialXPos, initialYPos, componentsFactory, assetLoader, movementStrategy, firingStrategy);
	}

	@Override
	public Boss createBoss() {
		return new Boss(componentsFactory, assetLoader);
	}
}