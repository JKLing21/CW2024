package com.example.demo;

import factories.interfaces.ProjectilesFactory;
/**
 * Implements the FiringStrategy interface 
 * which defines firing behavior for userplane.
 */
public class UserFiringStrategy implements FiringStrategy {

    private final ProjectilesFactory projectileFactory;
    private final double screenWidth;
	private static final int FIRE_RATE_DELAY = 200;
	private static final int RAPID_FIRE_DELAY = 150;
	private long lastFireTime = 0;
	private long lastPressTime = 0;
    /**
     * Constructs UserFiringStrategy with ProjectilesFactory and screen width.
     *
     * @param projectileFactory: factory method used to create user projectiles when user fires.
     * @param screenWidth: width of game screen for user projectile constraints.
     */
    public UserFiringStrategy(ProjectilesFactory projectileFactory, double screenWidth) {
        this.projectileFactory = projectileFactory;
        this.screenWidth = screenWidth;
    }
	/**
	 * Handles continuous firing logic for userplane.
	 *
	 * @param plane: userplane from which the projectile is to be fired.
	 * @return ActiveActorDestructible which represents the fired projectile, or null if no projectile was fired.
	 */
	public ActiveActorDestructible fireContinuous(UserPlane plane) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastFireTime >= FIRE_RATE_DELAY) {
			lastFireTime = currentTime;
			return fire(plane);
		}
		return null;
	}
	/**
	 * Handles rapid firing logic for userplane.
	 *
	 * @param plane: userplane from which the projectile is to be fired.
	 * @return ActiveActorDestructible which represents the fired projectile, or null if no projectile was fired.
	 */
	public ActiveActorDestructible fireRapid(UserPlane plane) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastPressTime >= RAPID_FIRE_DELAY) {
			lastPressTime = currentTime;
			return fire(plane);
		}
		return null;
	}
    /**
	 * Fires projectile from userplane. If player fires,
     * the projectile is created using the ProjectilesFactory at userplane's current position.
	 *
	 * @param plane: userplane from which the projectile is to be fired.
	 * @return ActiveActorDestructible which represents the fired projectile, or null if no projectile was fired.
	 */
    @Override
    public ActiveActorDestructible fire(FighterPlane plane) {
		if (plane instanceof UserPlane) {
            UserPlane userPlane = (UserPlane) plane;
			double projectileX = userPlane.getLayoutX() + userPlane.getTranslateX() + 150;
			double projectileY = userPlane.getLayoutY() + userPlane.getTranslateY() + 25;
            return projectileFactory.createUserProjectile(projectileX, projectileY, screenWidth);
        }
        return null;
    }
}
