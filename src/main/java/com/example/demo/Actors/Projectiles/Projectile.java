package com.example.demo.Actors.Projectiles;

import com.example.demo.Actors.ActiveActorDestructible;

import factories.interfaces.ComponentsFactory;

public abstract class Projectile extends ActiveActorDestructible {

	private double screenWidth;

	public Projectile(int imageHeight, double initialXPos, double initialYPos,
			ComponentsFactory componentsFactory) {
		super(imageHeight, initialXPos, initialYPos, componentsFactory);
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
