package com.example.demo.Strategy;

import com.example.demo.ActiveActorDestructible;
import com.example.demo.FighterPlane;

/**
 * This interface defines the contract 
 * for different firing strategies in game.
 */
public interface FiringStrategy {
    ActiveActorDestructible fire(FighterPlane plane);
}


