package com.example.demo.Factories.Interfaces;

import javafx.scene.image.ImageView;

public interface AssetFactory {
    ImageView createBackgroundImage(String imageName, double width, double height);
}