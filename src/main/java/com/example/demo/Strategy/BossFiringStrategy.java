package com.example.demo.Strategy;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Actors.Planes.Boss;
import com.example.demo.Actors.Planes.FighterPlane;
import com.example.demo.Factories.Interfaces.ProjectilesFactory;
/**
 * Implements the FiringStrategy interface 
 * which defines firing behavior for boss plane.
 */
public class BossFiringStrategy implements FiringStrategy {

    private static final double BOSS_FIRE_RATE = 0.04;
    private ProjectilesFactory projectileFactory;
    /**
     * Constructs BossFiringStrategy with ProjectilesFactory
     * 
     * @param projectileFactory: factory method used to create boss projectiles when boss fires.
     */
    public BossFiringStrategy(ProjectilesFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
    /**
     * Attempts to fire projectile from FighterPlane if it's instance of Boss
     * It checks if the boss should fire projectile based on BOSS_FIRE_RATE. If boss fires,
     * the projectile is created using the ProjectilesFactory at bossplane's current position.
     * 
     * @param plane: bossplane from which the projectile is to be fired.
     * @return ActiveActorDestructible which represents the fired projectile, or null if no projectile was fired.
     */
    @Override
    public ActiveActorDestructible fire(FighterPlane plane) {
        if (plane instanceof Boss) {
            Boss boss = (Boss) plane;
            if (Math.random() < BOSS_FIRE_RATE) {
                return projectileFactory.createBossProjectile(boss.getProjectileInitialPosition());
            }
        }
        return null;
    }
}

