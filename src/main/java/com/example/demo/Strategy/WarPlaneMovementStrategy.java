package com.example.demo.Strategy;

import com.example.demo.FighterPlane;

public class WarPlaneMovementStrategy implements MovementStrategy {

    private static final double LEFT_BOUND = -80.0;
    private static final double RIGHT_BOUND = 1380.0;
    private static final double HORIZONTAL_VELOCITY = -9;
    private static final double VERTICAL_VELOCITY = 0;

    @Override
    public void move(FighterPlane plane) {
        plane.setTranslateX(plane.getTranslateX() + HORIZONTAL_VELOCITY);
        plane.setTranslateY(plane.getTranslateY() + VERTICAL_VELOCITY);

        double newPosition = plane.getLayoutX() + plane.getTranslateX();
        if (newPosition < LEFT_BOUND) {
            plane.setTranslateX(RIGHT_BOUND);
        }
    }
}
