package com.example.demo;

public class ProjectilesImplement implements ProjectilesFactory {

	@Override
	public UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth) {
		ComponentsFactory factory = new ComponentsImplement();
		return new UserProjectile(initialXPos, initialYPos, screenWidth, factory);
	}

	@Override
	public EnemyProjectile createEnemyProjectile(double initialX, double initialY) {
		ComponentsFactory factory = new ComponentsImplement();
		return new EnemyProjectile(initialX, initialY, factory);
	}

	@Override
	public BossProjectile createBossProjectile(double initialYPos) {
		ComponentsFactory factory = new ComponentsImplement();
		return new BossProjectile(initialYPos, factory);
	}

}
