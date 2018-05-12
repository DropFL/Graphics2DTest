package com.rshtiger.platformer;

import java.util.ArrayList;

public final class Engine {
	Player player;
	Map map;
	ArrayList<IPlayerInteractive> entities;

	// whole platformer engine process will be defined here, like:


	public void tick () {
		if (KeyStatus.isKeyUp()){
		    if(!player.isJumped()){
		    	player.SetUnitJump(true);
				player.setJmpTime(1);
            }
        }
        if(player.getJmpTime() > 0 && player.getJmpTime() < 6) {
			player.movPositionY(-1 *player.getSpeedY());
			player.setJmpTime(player.getJmpTime() + 1);
		}
		else if(player.getJmpTime() >= 6) {
			player.setJmpTime(0);
		}

		if (KeyStatus.isKeyDown()) player.setJmpTime(0);

		if (KeyStatus.isKeyLeft()) player.movPositionX(-1 * player.getSpeedX());
		if (KeyStatus.isKeyRight()) player.movPositionX(player.getSpeedX());

		if (player.isJumped() && player.getJmpTime() == 0) player.movPositionY(player.getSpeedY());
		//player.setEnabled(true);

		entities = map.getBlocks();

		for (IPlayerInteractive e : entities) {
			if (e.isTouched(player))
				e.interact(player);
			//else
				//player.movPositionY(player.getSpeedY() * 2);
		}
	}

}
