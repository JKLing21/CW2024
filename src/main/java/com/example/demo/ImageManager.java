package com.example.demo;

import javafx.scene.image.Image;

public class ImageManager extends ImgAssetLoader {
    private static ImageManager instance;

    private ImageManager() {}

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    public Image getImage(String assetName) {
        return loadAsset(assetName);
    }
}