package com.example.demo.Strategy;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.FighterPlane;
import com.example.demo.WarPlane;
import factories.interfaces.ProjectilesFactory;

public class WarPlaneFiringStrategy implements FiringStrategy {

    private static final double WAR_PLANE_FIRE_RATE = 0.01;
    private final ProjectilesFactory projectileFactory;

    public WarPlaneFiringStrategy(ProjectilesFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }

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
