package com.example.demo.Factories;

import com.example.demo.Factories.Interfaces.AssetFactory;
import com.example.demo.Managers.ImageManager;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * AssetsImplement class is responsible for creating and managing assets by implementing the AssetFactory interface.
 */
public class AssetsImplement implements AssetFactory {

    private final ImageManager imageManager;
    /**
     * Constructs new AssetsImplement instance, initialising image manager.
     *
     * @param imageManager: ImageManager responsible for loading and managing images.
     */
    public AssetsImplement(ImageManager imageManager) {
        this.imageManager = imageManager;
    }
    /**
     * Creates new ImageView for a background image with specified name, width and height.
     *
     * @param imageName: name of image to be loaded.
     * @param width: width to which the image should be scaled.
     * @param height: height to which the image should be scaled.
     * @return ImageView containing the background image.
     */
    @Override
    public ImageView createBackgroundImage(String imageName, double width, double height) {
        Image image = imageManager.getImage(imageName);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}