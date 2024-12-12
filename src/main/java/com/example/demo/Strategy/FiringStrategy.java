package com.example.demo.Strategy;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Actors.Planes.FighterPlane;

/**
 * This interface defines the contract 
 * for different firing strategies in game.
 */
public interface FiringStrategy {
    ActiveActorDestructible fire(FighterPlane plane);
}


