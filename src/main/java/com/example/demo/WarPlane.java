package com.example.demo;

import com.example.demo.Strategy.FiringStrategy;
import com.example.demo.Strategy.MovementStrategy;
import factories.interfaces.ComponentsFactory;
import javafx.scene.image.Image;

public class WarPlane extends FighterPlane {

    private static final int IMAGE_HEIGHT = 60;
    private static final int INITIAL_HEALTH = 1;
    private final Image enemyImage;

    private MovementStrategy movementStrategy;
    private final FiringStrategy firingStrategy;

    public WarPlane(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader,
                    MovementStrategy movementStrategy, FiringStrategy firingStrategy) {
        super(IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH, factory);
        this.enemyImage = assetLoader.loadAsset("warplane");
        this.movementStrategy = movementStrategy;
        this.firingStrategy = firingStrategy;
        ImageProperties.applyProperties(this, enemyImage, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
    }

    @Override
    public void updatePosition() {
        movementStrategy.move(this);
    }

    @Override
    public ActiveActorDestructible fireProjectile() {
        return firingStrategy.fire(this);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }
}