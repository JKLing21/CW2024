package com.example.demo.Strategy;

import com.example.demo.Actors.Planes.FighterPlane;

/**
 * This interface defines the contract 
 * for different movement strategies in game.
 */
public interface MovementStrategy {
	void move(FighterPlane plane);
}
