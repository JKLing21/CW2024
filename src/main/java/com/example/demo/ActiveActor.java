package com.example.demo;

import java.util.Objects;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";
	private Rectangle hitbox;

	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos,
			ComponentsFactory componentsFactory) {
		Image image = new Image(
				Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm());
		this.setImage(image);
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);

		this.hitbox = componentsFactory.createHitbox(this.getBoundsInParent().getWidth(),
				this.getBoundsInParent().getHeight(), Color.TRANSPARENT, Color.TRANSPARENT);
		updateHitbox();
	}

	public abstract void updatePosition();

	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
		this.hitbox.setX(this.hitbox.getX() + horizontalMove);
	}

	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
		this.hitbox.setY(this.hitbox.getY() + verticalMove);
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void addHitboxToScene(Group root) {
		if (!root.getChildren().contains(hitbox)) {
			root.getChildren().add(hitbox);
		}
	}

	public void removeHitboxFromScene(Group root) {
		root.getChildren().remove(hitbox);
	}

	public void destroy(Group root) {
		removeHitboxFromScene(root);
	}

	private void updateHitbox() {
		double renderedWidth = this.getBoundsInParent().getWidth();
		double renderedHeight = this.getBoundsInParent().getHeight();

		hitbox.setWidth(renderedWidth);
		hitbox.setHeight(renderedHeight);
		hitbox.setX(this.getLayoutX());
		hitbox.setY(this.getLayoutY());
	}
}