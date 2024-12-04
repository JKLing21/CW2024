package com.example.demo;

public interface ActorFactory {
	UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory);

	EnemyPlane createEnemyPlane(double ScreenWidth, double initialYPos);

	Boss createBoss();
}
