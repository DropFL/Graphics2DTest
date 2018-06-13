package com.unseed.platformer.entity;

import com.unseed.platformer.collision.Collider;

public abstract class PlayerInteractive extends Entity {
	protected Collider collider;
	
	public abstract boolean interact (Player player);
	
	public boolean isCollided (Player player) {
		return collider.isCollided(player, this);
	}
}
