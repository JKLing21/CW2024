package com.example.demo.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * ImageProperties class is an utility class for applying properties to ImageView.
 * ImageProperties class provides methods to set various properties of ImageView,
 * such as position, size, and aspect ratio.
 */
public class ImageProperties {
	/**
     * Applies set of properties to ImageView.
     *
     * @param imageView: ImageView to which properties will be applied.
     * @param image: Image to set on ImageView.
     * @param xPosition: X position of ImageView.
     * @param yPosition: Y position of ImageView.
     * @param height: height of ImageView.
     * @param width: width of ImageView.
     * @param preserveRatio: the boolean indicating whether to preserve aspect ratio of image.
     */
	public static void applyProperties(ImageView imageView, Image image, Double xPosition, Double yPosition,
			Integer height, Double width, Boolean preserveRatio) {
		if (image != null) {
			setImage(imageView, image);
		}
		if (xPosition != null) {
			setLayoutX(imageView, xPosition);
		}
		if (yPosition != null) {
			setLayoutY(imageView, yPosition);
		}
		if (height != null) {
			setFitHeight(imageView, height);
		}
		if (width != null && width > 0) {
			setFitWidth(imageView, width);
		}
		if (preserveRatio != null) {
			setPreserveRatio(imageView, preserveRatio);
		}
	}
	/**
     * Sets image of ImageView.
     *
     * @param imageView: ImageView to which the image will be set.
     * @param image: Image to set on ImageView.
     */
	public static void setImage(ImageView imageView, Image image) {
		imageView.setImage(image);
	}
	/**
     * Sets X position of ImageView.
     *
     * @param imageView: ImageView to which X position will be set.
     * @param xPosition: X position to set.
     */
	public static void setLayoutX(ImageView imageView, Double xPosition) {
		imageView.setLayoutX(xPosition);
	}
	/**
     * Sets Y position of ImageView.
     *
     * @param imageView: ImageView to which Y position will be set.
     * @param xPosition: Y position to set.
     */
	public static void setLayoutY(ImageView imageView, Double yPosition) {
		imageView.setLayoutY(yPosition);
	}
	/**
     * Sets height of ImageView.
     *
     * @param imageView: ImageView to which height will be set.
     * @param height: height to set.
     */
	public static void setFitHeight(ImageView imageView, Integer height) {
		imageView.setFitHeight(height);
	}
	/**
     * Sets width of ImageView.
     *
     * @param imageView: ImageView to which width will be set.
     * @param width: width to set.
     */
	public static void setFitWidth(ImageView imageView, Double width) {
		imageView.setFitWidth(width);
	}
	/**
     * Sets whether aspect ratio of image should be preserved.
     *
     * @param imageView: ImageView to which preserve ratio setting will be applied.
     * @param preserveRatio: the boolean indicating whether to preserve aspect ratio.
     */
	public static void setPreserveRatio(ImageView imageView, Boolean preserveRatio) {
		imageView.setPreserveRatio(preserveRatio);
	}
}