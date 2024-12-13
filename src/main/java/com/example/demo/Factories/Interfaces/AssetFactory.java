package com.example.demo.Factories.Interfaces;

import javafx.scene.image.ImageView;
/**
 * AssetFactory interface defines methods for creating various assets, such as background images.
 */
public interface AssetFactory {
	/**
     * Creates a new ImageView for a background image with the specified name, width, and height.
     *
     * @param imageName The name of the image to be loaded.
     * @param width The width to which the image should be scaled.
     * @param height The height to which the image should be scaled.
     * @return An ImageView containing the background image.
     */
    ImageView createBackgroundImage(String imageName, double width, double height);
}