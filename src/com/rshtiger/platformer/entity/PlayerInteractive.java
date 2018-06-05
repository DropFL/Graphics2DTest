package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.Collider;

public abstract class PlayerInteractive extends Entity {
	protected Collider collider;
	
	public abstract boolean interact (Player player);
	public void update() {
		// null
	}
	public boolean isCollided (Player player) {
		return collider.isCollided(player, this);
	}
}
