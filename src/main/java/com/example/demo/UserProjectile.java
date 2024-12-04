package com.example.demo;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 6;
	private static final int HORIZONTAL_VELOCITY = 15;

	private double screenWidth;

	public UserProjectile(double initialXPos, double initialYPos, double screenWidth, ComponentsFactory factory) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, factory);
		this.screenWidth = screenWidth;
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
		checkBounds();
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	private void checkBounds() {
		if (getX() > screenWidth) {
			destroy();
		}
	}

}
