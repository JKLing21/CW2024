package com.example.demo.Factories.Interfaces;

import com.example.demo.Actors.Projectiles.BossProjectile;
import com.example.demo.Actors.Projectiles.EnemyProjectile;
import com.example.demo.Actors.Projectiles.UserProjectile;
import com.example.demo.Actors.Projectiles.WarPlaneProjectile;
/**
 * The ProjectilesFactory interface defines methods for creating various types of projectiles.
 */
public interface ProjectilesFactory {
	/**
     * Creates a new UserProjectile at the specified initial X and Y positions with the given screen width.
     *
     * @param initialXPos The initial X position of the projectile.
     * @param initialYPos The initial Y position of the projectile.
     * @param screenWidth The width of the screen.
     * @return A new UserProjectile instance.
     */
	UserProjectile createUserProjectile(double initialXPos, double initialYPos, double screenWidth);
	/**
     * Creates a new EnemyProjectile at the specified initial X and Y positions.
     *
     * @param initialX The initial X position of the projectile.
     * @param initialY The initial Y position of the projectile.
     * @return A new EnemyProjectile instance.
     */
	EnemyProjectile createEnemyProjectile(double initialX, double initialY);
	/**
     * Creates a new BossProjectile at the specified initial Y position.
     *
     * @param initialY The initial Y position of the projectile.
     * @return A new BossProjectile instance.
     */
	BossProjectile createBossProjectile(double initialY);
	/**
     * Creates a new WarPlaneProjectile at the specified initial X and Y positions.
     *
     * @param initialXPos The initial X position of the projectile.
     * @param initialYPos The initial Y position of the projectile.
     * @return A new WarPlaneProjectile instance.
     */
	WarPlaneProjectile createWarplaneProjectile(double initialXPos, double initialYPos);
}
