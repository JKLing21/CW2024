package com.example.demo.Strategy;

import com.example.demo.Actors.Planes.FighterPlane;

/**
 * Implements the MovementStrategy interface 
 * which defines movement behavior for enemy plane.
 */
public class EnemyMovementStrategy implements MovementStrategy {

	private static final double LEFT_BOUND = -80.0;
	private static final double RIGHT_BOUND = 1380.0;
	private static final double HORIZONTAL_VELOCITY = -6;
	/**
     * Moves enemy plane according to strategy defined.
     * enemy plane moves horizontally by the specified velocity. 
     * If the plane reaches the left boundary, it is wrapped around to the right boundary.
     *
     * @param plane: FighterPlane object which represents enemy plane to be moved.
     */
	@Override
	public void move(FighterPlane plane) {

		plane.setTranslateX(plane.getTranslateX() + HORIZONTAL_VELOCITY);

		double newPosition = plane.getLayoutX() + plane.getTranslateX();
		if (newPosition < LEFT_BOUND) {
			plane.setTranslateX(RIGHT_BOUND);
		}
	}
}
