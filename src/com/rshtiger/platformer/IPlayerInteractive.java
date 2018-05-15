package com.rshtiger.platformer;

public interface IPlayerInteractive extends IDrawable {
	void interact (Player player);
	boolean isTouched(Player player);
}
