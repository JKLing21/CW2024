package com.example.demo;

public interface ProjectilesFactory {
	UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth);

	EnemyProjectile createEnemyProjectile(double initialX, double initialY);

	BossProjectile createBossProjectile(double initialY);
}
