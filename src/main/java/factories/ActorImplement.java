package factories;

import com.example.demo.Boss;
import com.example.demo.EnemyMovementStrategy;
import com.example.demo.EnemyPlane;
import com.example.demo.ImgAssetLoader;
import com.example.demo.MovementStrategy;
import com.example.demo.UserPlane;

import factories.interfaces.ActorFactory;
import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;

public class ActorImplement implements ActorFactory {

	@Override
	public UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory) {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {
		};
		return new UserPlane(initialHealth, screenWidth, projectilesFactory, factory, assetLoader);
	}

	@Override
	public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {
		};
		MovementStrategy movementStrategy = new EnemyMovementStrategy();
		return new EnemyPlane(initialXPos, initialYPos, factory, assetLoader, movementStrategy);
	}

	@Override
	public Boss createBoss() {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {
		};
		return new Boss(factory, assetLoader);
	}

}