package com.rshtiger.platformer;

import java.awt.*;

public interface IPlayerInteractive {
	Image getImage ();
	int getPositionX ();
	int getPositionY ();
	// How about IDrawable?
	
	void interact (Player player);
	boolean isTouched(Player player);
}
