package com.example.demo.Actors;

import com.example.demo.Factories.Interfaces.ComponentsFactory;

import javafx.scene.Group;
/**
 * ActiveActorDestructible class represents destructible active actor in game, which is entity that can be destroyed.
 * ActiveActorDestructible is an abstract class that extends ActiveActor and implements Destructible interface,
 * providing common functionality for destructible actors, such as destruction management and damage handling.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;
	private boolean destroyedByUser;
	private boolean hasBeenCounted = false;
	/**
	 * Constructs new ActiveActorDestructible instance.
	 * Initialises destructible actor with its initial position and components factory.
	 *
	 * @param imageHeight: height of destructible actor's image.
	 * @param initialXPos: initial X position of destructible actor.
	 * @param initialYPos: initial Y position of destructible actor.
	 * @param componentsFactory: ComponentsFactory used for components creation.
	 */
	public ActiveActorDestructible(int imageHeight, double initialXPos, double initialYPos,
			ComponentsFactory componentsFactory) {
		super(imageHeight, initialXPos, initialYPos, componentsFactory);
		isDestroyed = false;
		this.destroyedByUser = false;
	}
	/**
	 * Updates position of destructible actor.
	 * Method that must be implemented by subclasses to define specific movement behavior.
	 */
	@Override
	public abstract void updatePosition();
	/**
	 * Updates state of destructible actor.
	 * Method that must be implemented by subclasses to define specific update behavior.
	 */
	public abstract void updateActor();
	/**
	 * Handles destructible actor taking damage.
	 * Method that must be implemented by subclasses to define specific damage handling behavior.
	 */
	@Override
	public abstract void takeDamage();
	/**
	 * Destroys destructible actor by marking it as destroyed.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}
	/**
	 * Destroys destructible actor by marking it as destroyed and removing it from the scene.
	 *
	 * @param root: root group of scene.
	 */
	public void destroy(Group root) {
		setDestroyed(true);
		root.getChildren().remove(this);
	}
	/**
	 * Destroys destructible actor by marking it as destroyed by user.
	 */
	public void destroyByUser() {
		this.destroyedByUser = true;
		destroy();
	}
	/**
	 * Sets destroyed state of destructible actor.
	 *
	 * @param isDestroyed: new destroyed state.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	/**
	 * Checks if destructible actor is destroyed.
	 *
	 * @return True, if actor is destroyed, false otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	/**
	 * Checks if destructible actor was destroyed by user.
	 *
	 * @return True, if actor was destroyed by user, false otherwise.
	 */
	public boolean isDestroyedByUser() {
		return destroyedByUser;
	}
	/**
	 * Checks if destructible actor has been counted.
	 *
	 * @return True, if actor has been counted, false otherwise.
	 */
	public boolean hasBeenCounted() {
		return hasBeenCounted;
	}
	/**
	 * Sets whether destructible actor has been counted.
	 *
	 * @param hasBeenCounted: new counted state.
	 */
	public void setHasBeenCounted(boolean hasBeenCounted) {
		this.hasBeenCounted = hasBeenCounted;
	}

} 