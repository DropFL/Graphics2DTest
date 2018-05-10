package com.rshtiger.platformer;

public final class Engine {
	Player player;
	// whole platformer engine process will be defined here, like:


	public void tick () {
		if (KeyStatus.isKeyUp()){
		    if(!player.isJumped()){

            }
        }
		//if (KeyStatus.isKeyDown()) player.y += 5;
		if (KeyStatus.isKeyLeft()) player.movPositionX(-1 * player.getSpeedX());
		if (KeyStatus.isKeyRight()) player.movPositionX(player.getSpeedX());
		
		player.setEnabled(true);
		
		for (Entity entity in list_of_entities) {
			if (entity.isTouched(player))
				platform.interact(player);
		}
	}


}
