package com.example.demo;

public class ActorImplement implements ActorFactory {

	@Override
	public UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory) {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {};
		return new UserPlane(initialHealth, screenWidth, projectilesFactory, factory, assetLoader);
	}

	@Override
	public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {};
		return new EnemyPlane(initialXPos, initialYPos, factory, assetLoader);
	}

	@Override
	public Boss createBoss() {
		ComponentsFactory factory = new ComponentsImplement();
		ImgAssetLoader assetLoader = new ImgAssetLoader() {};
		return new Boss(factory, assetLoader);
	}

}