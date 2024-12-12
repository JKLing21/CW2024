package com.example.demo.Actors;

import factories.interfaces.ComponentsFactory;
import javafx.scene.Group;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;
	private boolean destroyedByUser;
	private boolean hasBeenCounted = false;

	public ActiveActorDestructible(int imageHeight, double initialXPos, double initialYPos,
			ComponentsFactory componentsFactory) {
		super(imageHeight, initialXPos, initialYPos, componentsFactory);
		isDestroyed = false;
		this.destroyedByUser = false;
	}

	@Override
	public abstract void updatePosition();

	public abstract void updateActor();

	@Override
	public abstract void takeDamage();

	@Override
	public void destroy() {
		setDestroyed(true);
	}

	public void destroy(Group root) {
		setDestroyed(true);
		root.getChildren().remove(this);
	}

	public void destroyByUser() {
		this.destroyedByUser = true;
		destroy();
	}

	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public boolean isDestroyedByUser() {
		return destroyedByUser;
	}

	public boolean hasBeenCounted() {
		return hasBeenCounted;
	}

	public void setHasBeenCounted(boolean hasBeenCounted) {
		this.hasBeenCounted = hasBeenCounted;
	}

} 