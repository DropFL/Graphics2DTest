package com.rshtiger.platformer;

import com.dropfl.component.IDrawable;

public interface IPlayerInteractive extends IDrawable {
	void interact (Player player);
	boolean isTouched(Player player);
	boolean isEnabled();
}
