package com.example.demo;

public class ActorImplement implements ActorFactory {

	@Override
	public UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory) {
		ComponentsFactory factory = new ComponentsImplement();
		return new UserPlane(initialHealth, screenWidth, projectilesFactory, factory);
	}

	@Override
	public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
		ComponentsFactory factory = new ComponentsImplement();
		return new EnemyPlane(initialXPos, initialYPos, factory);
	}

	@Override
	public Boss createBoss() {
		ComponentsFactory factory = new ComponentsImplement();
		return new Boss(factory);
	}

}
