package com.example.demo;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private int velocityMultiplier;
	private int numberOfKills;
	private boolean isFiring;
	private long lastShotTime;
	private static final long FIRE_INTERVAL = 300;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
		lastShotTime = System.currentTimeMillis();
	}
	
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}
	
	 @Override
	    public void updateActor() {
	        updatePosition();
	        if (isFiring && (System.currentTimeMillis() - lastShotTime >= FIRE_INTERVAL)) {
	            fireProjectile();
	            lastShotTime = System.currentTimeMillis();
	        }
	    }
	
	@Override
	public ActiveActorDestructible fireProjectile() {
	    double projectileX = getLayoutX() + getTranslateX() + PROJECTILE_X_POSITION;
	    double projectileY = getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	    return new UserProjectile(projectileX, projectileY);
	}

	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	public void moveUp() {
		velocityMultiplier = -1;
	}

	public void moveDown() {
		velocityMultiplier = 1;
	}

	public void stop() {
		velocityMultiplier = 0;
	}
	
	public void startFiring() {
	    isFiring = true;
	}

	public void stopFiring() {
	    isFiring = false;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}
	
	public int getVelocityMultiplier() {
	    return velocityMultiplier;
	}

}
