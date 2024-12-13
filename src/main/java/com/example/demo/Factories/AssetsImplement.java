package com.example.demo.Factories;

import com.example.demo.Factories.Interfaces.AssetFactory;
import com.example.demo.Managers.ImageManager;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AssetsImplement implements AssetFactory {

    private final ImageManager imageManager;

    public AssetsImplement(ImageManager imageManager) {
        this.imageManager = imageManager;
    }
    
    @Override
    public ImageView createBackgroundImage(String imageName, double width, double height) {
        Image image = imageManager.getImage(imageName);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }
}