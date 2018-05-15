package com.rshtiger.platformer;

import res.MapResource;

import java.awt.*;
import java.util.ArrayList;

public final class Engine extends Thread{
	
	private int gravity = 1;
	private Player player;
	private Map map;
	private ArrayList<IPlayerInteractive> entities;
	
	public Engine () {
		map = MapResource.TestMap.getMapData();
		entities = map.getBlocks();
		player = new Player();
	}
	
	public void tick () {
		if (KeyStatus.isKeySpace()) {
		    if(!player.getJumped()){
		    	player.setJumped(true);
		    	player.setSpeedY(-10);
            }
        } else if (player.getSpeedY() < 0)
        	player.setSpeedY(0);
        
        if(KeyStatus.isKeyLeft() ^ KeyStatus.isKeyRight())
        	player.setSpeedX(KeyStatus.isKeyLeft() ? -5 : 5);
        else
        	player.setSpeedX(0);
		
		player.addPositionX(player.getSpeedX());
		player.addPositionY(player.getSpeedY());
		
		player.addSpeedY(gravity);

		for (IPlayerInteractive e : entities) {
			if (e.isTouched(player))
				e.interact(player);
		}
	}
	
	public void render (Graphics2D g) {
		for (IPlayerInteractive entity : entities)
			entity.render(g);
		
		player.render(g);
	}
	
	@Override
	public void run () {
		try {
			while (true) {
				this.tick();
				Thread.sleep(16);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
