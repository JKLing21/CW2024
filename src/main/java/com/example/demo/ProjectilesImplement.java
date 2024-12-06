package com.example.demo;

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