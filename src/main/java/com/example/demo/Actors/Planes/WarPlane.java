package com.example.demo.Actors.Planes;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Assets.ImgAssetLoader;
import com.example.demo.Factories.Interfaces.ComponentsFactory;
import com.example.demo.Strategy.FiringStrategy;
import com.example.demo.Strategy.MovementStrategy;
import com.example.demo.utils.ImageProperties;

import javafx.scene.image.Image;
/**
 * Warplane class represents war plane in game.
 * Warplane class extends FighterPlane class and implements specific behaviors for warplanes,
 * such as strategies for movement and firing.
 */
public class WarPlane extends FighterPlane {

    private static final int IMAGE_HEIGHT = 60;
    private static final int INITIAL_HEALTH = 1;
    private final Image enemyImage;

    private MovementStrategy movementStrategy;
    private final FiringStrategy firingStrategy;
    /**
     * Constructs new WarPlane instance.
     * Initialises warplane with its image, movement and firing strategies.
     *
     * @param initialXPos: initial X position of the war plane.
     * @param initialYPos: initial Y position of the war plane.
     * @param factory: ComponentsFactory used for components creation.
     * @param assetLoader: ImgAssetLoader used for loading images.
     * @param movementStrategy: movement strategy for warplane.
     * @param firingStrategy: firing strategy for warplane.
     */
    public WarPlane(double initialXPos, double initialYPos, ComponentsFactory factory, ImgAssetLoader assetLoader,
                    MovementStrategy movementStrategy, FiringStrategy firingStrategy) {
        super(IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH, factory);
        this.enemyImage = assetLoader.loadAsset("warplane");
        this.movementStrategy = movementStrategy;
        this.firingStrategy = firingStrategy;
        ImageProperties.applyProperties(this, enemyImage, initialXPos, initialYPos, IMAGE_HEIGHT, null, true);
    }
    /**
     * Updates position of warplane based on warplane's movement strategy.
     */
    @Override
    public void updatePosition() {
        movementStrategy.move(this);
    }
    /**
     * Fires projectile based on warplane's firing strategy.
     *
     * @return fired projectile as ActiveActorDestructible.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return firingStrategy.fire(this);
    }
    /**
     * Updates warplane actor by updating its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
