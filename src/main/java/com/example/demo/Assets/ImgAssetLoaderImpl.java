package com.example.demo.Assets;

import javafx.scene.image.Image;
/**
 * ImgAssetLoaderImpl is a concrete implementation of ImgAssetLoader class.
 * ImgAssetLoaderImpl class provides the functionality to load image assets by delegating the loading process
 * to parent ImgAssetLoader class.
 */
public class ImgAssetLoaderImpl extends ImgAssetLoader {
	
    @Override
    public Image loadAsset(String name) {
    	
        return super.loadAsset(name);
    }
}