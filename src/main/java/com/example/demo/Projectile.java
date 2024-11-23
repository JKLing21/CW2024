package com.example.demo;

public abstract class Projectile extends ActiveActorDestructible {
	
	private double screenWidth;

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	@Override
	public void takeDamage() {
		this.destroy();
	}

	@Override
	public void updatePosition() {	 
		 if (this.getX() < 0 || this.getX() > screenWidth) {
			    this.destroy();
			}
	}

}
