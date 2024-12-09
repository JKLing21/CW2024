package com.example.demo;
/**
 * This interface defines the contract 
 * for different firing strategies in game.
 */
public interface FiringStrategy {
    ActiveActorDestructible fire(FighterPlane plane);
}


