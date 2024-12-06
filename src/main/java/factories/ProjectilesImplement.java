package factories;

import com.example.demo.BossProjectile;
import com.example.demo.EnemyProjectile;
import com.example.demo.ImgAssetLoader;
import com.example.demo.UserProjectile;

import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;

public class ProjectilesImplement implements ProjectilesFactory {

	@Override
	public UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth) {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {};
		return new UserProjectile(initialXPos, initialYPos, screenWidth, factory, assetLoader);
	}

	@Override
	public EnemyProjectile createEnemyProjectile(double initialX, double initialY) {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {};
		return new EnemyProjectile(initialX, initialY, factory, assetLoader);
	}

	@Override
	public BossProjectile createBossProjectile(double initialYPos) {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {};
		return new BossProjectile(initialYPos, factory, assetLoader);
	}

}