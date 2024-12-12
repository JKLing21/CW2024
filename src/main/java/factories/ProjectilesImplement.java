package factories;

import com.example.demo.BossProjectile;
import com.example.demo.EnemyProjectile;
import com.example.demo.ImgAssetLoader;
import com.example.demo.UserProjectile;
import com.example.demo.WarPlaneProjectile;

import factories.interfaces.ComponentsFactory;
import factories.interfaces.ProjectilesFactory;

public class ProjectilesImplement implements ProjectilesFactory {
	
	private final ComponentsFactory componentsFactory;
	private final ImgAssetLoader assetLoader;
	
	public ProjectilesImplement() {
		this.componentsFactory = new ComponentsImplement();
		this.assetLoader = new ImgAssetLoader() {};
	}

	@Override
	public UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth) {
		return new UserProjectile(initialXPos, initialYPos, screenWidth, componentsFactory, assetLoader);
	}

	@Override
	public EnemyProjectile createEnemyProjectile(double initialX, double initialY) {
		return new EnemyProjectile(initialX, initialY, componentsFactory, assetLoader);
	}

	@Override
	public BossProjectile createBossProjectile(double initialYPos) {
		return new BossProjectile(initialYPos, componentsFactory, assetLoader);
	}
	
	@Override
    public WarPlaneProjectile createWarplaneProjectile(double initialXPos, double initialYPos) {
        return new WarPlaneProjectile(initialXPos, initialYPos, componentsFactory, assetLoader);
    }

}