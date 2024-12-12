package com.example.demo.Managers;

import com.example.demo.Actors.ActiveActorDestructible;
import com.example.demo.Actors.Planes.UserPlane;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;

/**
 * Manages collisions between various actors. It handles collisions detection,
 * applying damage and managing destruction of actors.
 */
public class CollisionManager {

	private Group root;
	private double screenWidth;

	/**
	 * Constructs the CollisionManager for collision detection management.
	 * 
	 * @param root:        root Group which holds all game objects.
	 * @param screenWidth: width of screen for collision detection logic.
	 */
	public CollisionManager(Group root, double screenWidth) {
		this.root = root;
		this.screenWidth = screenWidth;
	}

	/**
	 * Handles collisions between two sets of actors If any two actors intersect,
	 * both of them take damage.
	 * 
	 * @param actors1: the first set of actors for collision check.
	 * @param actors2: the second set of actors for collision check.
	 */
	public void handlePlaneCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors1) {
			for (ActiveActorDestructible otherActor : actors2) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					handleDamage(actor, otherActor);
				}
			}
		}
	}

	/**
	 * Handles collisions between projectiles and targets When projectile hits a
	 * target, projectile and target take damage. Increments kill count and removes
	 * the target, If a target is destroyed.
	 * 
	 * @param projectiles: list of projectiles.
	 * @param targets:     list of target actors.
	 * @param user:        the player's plane.
	 */
	public void handleProjectileCollisions(List<ActiveActorDestructible> projectiles,
			List<ActiveActorDestructible> targets, UserPlane user) {
		List<ActiveActorDestructible> projectilesToRemove = new ArrayList<>();
		List<ActiveActorDestructible> targetsToRemove = new ArrayList<>();

		for (ActiveActorDestructible projectile : projectiles) {
			for (ActiveActorDestructible target : targets) {
				if (projectile.getBoundsInParent().intersects(target.getBoundsInParent())) {
					handleDamage(projectile, target);

					if (target.isDestroyed()) {
						handleTargetDestruction(target, user, targetsToRemove);
					}

					if (projectile.isDestroyed()) {
						projectilesToRemove.add(projectile);
					}
				}
			}
		}

		removeActors(projectilesToRemove, projectiles);
		removeActors(targetsToRemove, targets);
	}

	/**
	 * Handles situation where enemies plane penetrate the player's defense line. If
	 * an enemy plane passes the screen boundary, it will deal damage to player and
	 * be removed.
	 * 
	 * @param enemies: list of enemy actors for penetration check.
	 * @param user:    player's plane take damage if an enemy penetrates defense
	 *                 line.
	 */
	public void handleEnemyPenetration(List<ActiveActorDestructible> enemies, UserPlane user) {
		List<ActiveActorDestructible> enemiesToRemove = new ArrayList<>();

		for (ActiveActorDestructible enemy : enemies) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy(root);
				enemiesToRemove.add(enemy);
			}
		}

		removeActors(enemiesToRemove, enemies);
	}

	/**
	 * Applies damage to two actors if both collides.
	 * 
	 * @param actor1: first actor to take damage.
	 * @param actor2: second actor to take damage.
	 */
	private void handleDamage(ActiveActorDestructible actor1, ActiveActorDestructible actor2) {
		actor1.takeDamage();
		actor2.takeDamage();
	}

	/**
	 * Handles destruction of the target when it's hit by projectile. Player's kill
	 * count is incremented, then target is marked as counted to prevent multiple
	 * kills count.
	 * 
	 * @param target:          target actor that has been destroyed.
	 * @param user:            Player's plane to increment kill count.
	 * @param targetsToRemove: the list storing targets that need to be removed.
	 */
	private void handleTargetDestruction(ActiveActorDestructible target, UserPlane user,
			List<ActiveActorDestructible> targetsToRemove) {
		if (!target.hasBeenCounted()) {
			user.incrementKillCount();
			target.setHasBeenCounted(true);
		}
		targetsToRemove.add(target);
	}

	/**
	 * Removes actors from both root Group and actor list.
	 * 
	 * @param actorsToRemove: list of actors to be removed.
	 * @param actorList:      the actor list to remove from.
	 */
	private void removeActors(List<ActiveActorDestructible> actorsToRemove, List<ActiveActorDestructible> actorList) {
		for (ActiveActorDestructible actor : actorsToRemove) {
			if (actor != null && root.getChildren().contains(actor)) {
				root.getChildren().remove(actor);
			}
			actorList.remove(actor);
		}
	}

	/**
	 * Checks if enemy has penetrated the player's defense line.
	 * 
	 * @param enemy: enemy actor to check.
	 * @return true if enemy has penetrated the defense line, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}
}
