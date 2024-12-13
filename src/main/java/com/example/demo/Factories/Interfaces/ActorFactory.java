package com.example.demo.Factories.Interfaces;

import com.example.demo.Actors.Planes.Boss;
import com.example.demo.Actors.Planes.EnemyPlane;
import com.example.demo.Actors.Planes.UserPlane;
import com.example.demo.Actors.Planes.WarPlane;
import com.example.demo.Managers.AudioManager;
/**
 * ActorFactory interface defines methods for creating various game actors.
 */
public interface ActorFactory {
	/**
     * Creates new UserPlane with specified initial health, screen width, ProjectilesFactory, and AudioManager.
     *
     * @param initialHealth: initial health of user's plane.
     * @param screenWidth: width of the screen.
     * @param projectilesFactory: ProjectilesFactory used for projectiles creation.
     * @param audioManager: AudioManager used for playing audio.
     * @return new UserPlane instance.
     */
	UserPlane createUserPlane(int initialHealth, double screenWidth, ProjectilesFactory projectilesFactory, AudioManager audioManager);
	/**
     * Creates new EnemyPlane at specified screen width and initial Y position.
     *
     * @param screenWidth: width of screen.
     * @param initialYPos: initial Y position of the enemy plane.
     * @return new EnemyPlane instance.
     */
	EnemyPlane createEnemyPlane(double ScreenWidth, double initialYPos);
	/**
     * Creates new WarPlane at specified screen width and initial Y position.
     *
     * @param screenWidth: width of screen.
     * @param initialYPos: initial Y position of the warplane.
     * @return new WarPlane instance.
     */
	WarPlane createWarPlane(double ScreenWidth, double initialYPos);
	/**
     * Creates new Boss instance.
     *
     * @return new Boss instance.
     */
	Boss createBoss();
}
