package com.example.demo;
/**
 * Implements the MovementStrategy interface 
 * which defines movement behavior for user plane.
 */
public class UserMovementStrategy implements MovementStrategy {

    private static final double Y_UPPER_BOUND = 0;
    private static final double Y_LOWER_BOUND = 680.0;
    private static final int VERTICAL_VELOCITY = 8;

    private final UserPlane userPlane;
    private int velocityMultiplier;
    /**
     * Constructs UserMovementStrategy object, initialises UserPlane and velocity multiplier.
     * 
     * @param userPlane: UserPlane object that is control by strategy
     */
    public UserMovementStrategy(UserPlane userPlane) {
        this.userPlane = userPlane;
        this.velocityMultiplier = 0;
    }
    /**
     * Gets current velocity multiplier which controls plane direction and movement speed.
     * 
     * @return current velocity multiplier
     */
    public int getVelocityMultiplier() {
        return velocityMultiplier;
    }

    /**
     * Sets plane movement direction to upwards
     * by setting the velocity multiplier to -1
     */
    public void moveUp() {
        velocityMultiplier = -1;
    }

    /**
     * Sets plane movement direction to downwards
     * by setting the velocity multiplier to 1
     */
    public void moveDown() {
        velocityMultiplier = 1;
    }

    /**
     * Stops plane movement
     * by setting the velocity multiplier to 0
     */
    public void stop() {
        velocityMultiplier = 0;
    }

    /**
     * Updates plane's position based on the current movement direction and speed.
     * If plane exceeds the upper or lower bounds, the position is reset.
     */
    public void updatePosition() {
        if (velocityMultiplier != 0) {
            double initialTranslateY = userPlane.getTranslateY();
            userPlane.setTranslateY(userPlane.getTranslateY() + VERTICAL_VELOCITY * velocityMultiplier);

            double newPosition = userPlane.getLayoutY() + userPlane.getTranslateY();
            if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
                userPlane.setTranslateY(initialTranslateY); // Reset if out of bounds
            }
        }
    }

    /**
     * Moves FighterPlane by updating its position using movement strategy.
     * 
     * @param plane: FighterPlane to be moved
     */
    @Override
    public void move(FighterPlane plane) {
    	updatePosition(); 
    }
}
