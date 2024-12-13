package com.example.demo.Strategy;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Actors.Planes.FighterPlane;
import com.example.demo.Actors.Planes.WarPlane;
import com.example.demo.Factories.Interfaces.ProjectilesFactory;
/**
 * Implements the FiringStrategy interface 
 * which defines firing behavior for warplane.
 */
public class WarPlaneFiringStrategy implements FiringStrategy {

    private static final double WAR_PLANE_FIRE_RATE = 0.01;
    private final ProjectilesFactory projectileFactory;
    /**
     * Constructs WarPlaneFiringStrategy with ProjectilesFactory.
     *
     * @param projectileFactory: factory method used to create warplane projectiles when warplane fires.
     */
    public WarPlaneFiringStrategy(ProjectilesFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }
    /**
     * Attempts to fire projectile from FighterPlane if it's instance of WarPlane.
     * It checks if enemy should fire based on WAR_PLANE_FIRE_RATE.
     *
     * @param plane: warplane from which the projectile is to be fired.
     * @return ActiveActorDestructible which represents the fired projectile, or null if no projectile was fired.
     */
    @Override
    public ActiveActorDestructible fire(FighterPlane plane) {
        if (plane instanceof WarPlane) {
            if (Math.random() < WAR_PLANE_FIRE_RATE) {
                WarPlane warPlane = (WarPlane) plane;
                double projectileXPosition = warPlane.getProjectileXPosition(-76.0);
                double projectileYPosition = warPlane.getProjectileYPosition(22.0);
                return projectileFactory.createWarplaneProjectile(projectileXPosition, projectileYPosition);
            }
        }
        return null;
    }

}
