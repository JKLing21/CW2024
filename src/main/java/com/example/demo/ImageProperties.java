package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageProperties {

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

    public static void setImage(ImageView imageView, Image image) {
        imageView.setImage(image);
    }

    public static void setLayoutX(ImageView imageView, Double xPosition) {
        imageView.setLayoutX(xPosition);
    }

    public static void setLayoutY(ImageView imageView, Double yPosition) {
        imageView.setLayoutY(yPosition);
    }

    public static void setFitHeight(ImageView imageView, Integer height) {
        imageView.setFitHeight(height);
    }
    
    public static void setFitWidth(ImageView imageView, Double width) {
        imageView.setFitWidth(width);
    }

    public static void setPreserveRatio(ImageView imageView, Boolean preserveRatio) {
        imageView.setPreserveRatio(preserveRatio);
    }
}
