package com.example.demo.Actors;

import com.example.demo.Factories.Interfaces.ComponentsFactory;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * ActiveActor class represents active actor in game, which is an entity that can move and interact with other entities.
 * ActiveActor is an abstract class that extends ImageView and provides common functionality for active actors,
 * such as hitbox management, movement and destruction.
 */
public abstract class ActiveActor extends ImageView {
	
	private Rectangle hitbox;
	/**
	 * Constructs new ActiveActor instance.
	 * Initialises active actor with its hitbox and initial position.
	 *
	 * @param imageHeight: height of active actor's image.
	 * @param initialXPos: initial X position of active actor.
	 * @param initialYPos: initial Y position of active actor.
	 * @param componentsFactory: ComponentsFactory used for components creation.
	 */
	public ActiveActor(int imageHeight, double initialXPos, double initialYPos,
			ComponentsFactory componentsFactory) {

		this.hitbox = componentsFactory.createHitbox(this.getBoundsInParent().getWidth(),
				this.getBoundsInParent().getHeight(), Color.TRANSPARENT, Color.TRANSPARENT);
		updateHitbox();
	}
	/**
	 * Updates position of active actor.
	 * Method that must be implemented by subclasses to define specific movement behavior.
	 */
	public abstract void updatePosition();
	/**
	 * Moves active actor horizontally by specified amount.
	 *
	 * @param horizontalMove: amount to move active actor horizontally.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
		this.hitbox.setX(this.hitbox.getX() + horizontalMove);
	}
	/**
	 * Moves active actor vertically by specified amount.
	 *
	 * @param verticalMove: amount to move active actor vertically.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
		this.hitbox.setY(this.hitbox.getY() + verticalMove);
	}
	/**
	 * Gets hitbox of active actor.
	 *
	 * @return hitbox rectangle.
	 */
	public Rectangle getHitbox() {
		return hitbox;
	}
	/**
	 * Adds hitbox to scene's root group.
	 *
	 * @param root: root group of scene.
	 */
	public void addHitboxToScene(Group root) {
		if (!root.getChildren().contains(hitbox)) {
			root.getChildren().add(hitbox);
		}
	}
	/**
	 * Removes hitbox from scene's root group.
	 *
	 * @param root: root group of scene.
	 */
	public void removeHitboxFromScene(Group root) {
		root.getChildren().remove(hitbox);
	}
	/**
	 * Destroys active actor by removing its hitbox from the scene.
	 *
	 * @param root: root group of scene.
	 */
	public void destroy(Group root) {
		removeHitboxFromScene(root);
	}
	/**
	 * Updates hitbox to match current position and size of active actor.
	 */
	private void updateHitbox() {
		double renderedWidth = this.getBoundsInParent().getWidth();
		double renderedHeight = this.getBoundsInParent().getHeight();

		hitbox.setWidth(renderedWidth);
		hitbox.setHeight(renderedHeight);
		hitbox.setX(this.getLayoutX());
		hitbox.setY(this.getLayoutY());
	}
}