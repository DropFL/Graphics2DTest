package com.rshtiger.platformer;

import com.rshtiger.platformer.collision.Collider;
import com.rshtiger.platformer.collision.Shape;

public abstract class PlayerInteractive extends Shape {
	protected Collider collider;
	
	public abstract boolean interact (Player player);
	
	public boolean isTouched (Player player) {
		return collider.isCollided(player, this);
	}
}
