package com.example.demo.Actors;
/**
 * Destructible interface represents destructible entity in the game.
 * Destructible interface defines contract for entities that can take damage and be destroyed.
 */
public interface Destructible {

	void takeDamage();

	void destroy();
	
}
