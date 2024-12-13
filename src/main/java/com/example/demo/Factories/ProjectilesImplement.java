package com.example.demo.Factories;

import com.example.demo.Actors.Projectiles.BossProjectile;
import com.example.demo.Actors.Projectiles.EnemyProjectile;
import com.example.demo.Actors.Projectiles.UserProjectile;
import com.example.demo.Actors.Projectiles.WarPlaneProjectile;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Factories.Interfaces.ProjectilesFactory;
/**
 * ProjectilesImplement class is responsible for creating various types of projectiles by implementing the ProjectilesFactory interface.
 */
public class ProjectilesImplement implements ProjectilesFactory {
	
	private final ComponentsFactory componentsFactory;
	private final ImgAssetLoader assetLoader;
	/**
	 * Constructs new ProjectilesImplement instance, initialising the ComponentsFactory and ImgAssetLoader.
	 */
	public ProjectilesImplement() {
		this.componentsFactory = new ComponentsImplement();
		this.assetLoader = new ImgAssetLoader() {};
	}
	/**
	 * Creates a new UserProjectile at the specified initial X and Y positions with the given screen width.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 * @param screenWidth The width of the screen.
	 * @return A new UserProjectile instance.
	 */
	@Override
	public UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth) {
		return new UserProjectile(initialXPos, initialYPos, screenWidth, componentsFactory, assetLoader);
	}
	/**
	 * Creates a new EnemyProjectile at the specified initial X and Y positions.
	 *
	 * @param initialX The initial X position of the projectile.
	 * @param initialY The initial Y position of the projectile.
	 * @return A new EnemyProjectile instance.
	 */
	@Override
	public EnemyProjectile createEnemyProjectile(double initialX, double initialY) {
		return new EnemyProjectile(initialX, initialY, componentsFactory, assetLoader);
	}
	/**
	 * Creates a new BossProjectile at the specified initial Y position.
	 *
	 * @param initialYPos The initial Y position of the projectile.
	 * @return A new BossProjectile instance.
	 */
	@Override
	public BossProjectile createBossProjectile(double initialYPos) {
		return new BossProjectile(initialYPos, componentsFactory, assetLoader);
	}
	/**
	 * Creates a new WarPlaneProjectile at the specified initial X and Y positions.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 * @return A new WarPlaneProjectile instance.
	 */
	@Override
    public WarPlaneProjectile createWarplaneProjectile(double initialXPos, double initialYPos) {
        return new WarPlaneProjectile(initialXPos, initialYPos, componentsFactory, assetLoader);
    }

}