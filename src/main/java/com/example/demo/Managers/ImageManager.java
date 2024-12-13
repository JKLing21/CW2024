package com.example.demo.Managers;

import com.example.demo.Assets.ImgAssetLoader;

import javafx.scene.image.Image;
/**
 * ImageManager class manages loading and retrieval of image assets in game.
 * ImageManager class is a singleton, ensuring that only one instance of ImageManager exists.
 */
public class ImageManager extends ImgAssetLoader {
    private static ImageManager instance;
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ImageManager() {}
    /**
     * Gets singleton instance of ImageManager.
     *
     * @return singleton instance of ImageManager.
     */
    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }
    /**
     * Loads and retrieves image asset by the name.
     *
     * @param assetName: name of image asset to load.
     * @return loaded Image object, or null if asset could not be loaded.
     */
    public Image getImage(String assetName) {
        return loadAsset(assetName);
    }
}