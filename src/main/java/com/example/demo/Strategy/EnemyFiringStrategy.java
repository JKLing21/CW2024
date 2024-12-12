package com.example.demo.Strategy;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Actors.Planes.EnemyPlane;
import com.example.demo.Actors.Planes.FighterPlane;

import factories.interfaces.ProjectilesFactory;
/**
 * Implements the FiringStrategy interface 
 * which defines firing behavior for enemy plane.
 */
public class EnemyFiringStrategy implements FiringStrategy {

    private static final double ENEMY_FIRE_RATE = 0.01;
    private final ProjectilesFactory projectileFactory;
    /**
     * Constructs EnemyFiringStrategy with ProjectilesFactory.
     *
     * @param projectileFactory: factory method used to create enemy projectiles when enemy fires.
     */
    public EnemyFiringStrategy(ProjectilesFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
    /**
     * Attempts to fire projectile from FighterPlane if it's instance of EnemyPlane.
     * It checks if enemy should fire based on ENEMY_FIRE_RATE.
     *
     * @param plane: enemyplane from which the projectile is to be fired.
     * @return ActiveActorDestructible which represents the fired projectile, or null if no projectile was fired.
     */
    @Override
    public ActiveActorDestructible fire(FighterPlane plane) {
        if (plane instanceof EnemyPlane && Math.random() < ENEMY_FIRE_RATE) {
            EnemyPlane enemy = (EnemyPlane) plane;
            double projectileXPosition = enemy.getProjectileXPosition(-76.0);
            double projectileYPosition = enemy.getProjectileYPosition(22.0);
            return projectileFactory.createEnemyProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }
}
