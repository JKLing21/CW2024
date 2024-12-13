package com.example.demo.Strategy;

import com.example.demo.Actors.Planes.Boss;

/**
 * This interface defines the contract 
 * for managing and applying shielding effects for bossplane.
 */
public interface BossShielding {
	
	void updateShield(Boss boss);
	
    void applyShieldEffect(Boss boss);
    
    boolean isShielded();
}
