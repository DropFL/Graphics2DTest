package com.rshtiger.platformer;

import com.rshtiger.key.Key;
import com.rshtiger.key.KeyStatus;
import res.MapResource;

import java.awt.*;
import java.util.ArrayList;

public final class Engine extends Thread{
	
	private int gravity = 1;
	private Player player;
	private Map map;
	private ArrayList<IPlayerInteractive> entities;
	
	public Engine (MapResource mapResource) {
		map = mapResource.getMapData();
		entities = map.getBlocks();
		player = new Player();
	}
	
	public Engine () {
		this(MapResource.TestMap);
	}
	
	public void	tick () {
		
		if (KeyStatus.isKeyJustPressed(Key.SPACE)) {
		    if(!player.getJumped()){
		    	player.setJumped(true);
		    	player.setSpeedY(-Player.MAX_SPEED_Y);
            }
            
            KeyStatus.setKeyProcessed(Key.SPACE);
        } else if (!KeyStatus.isKeyPressed(Key.SPACE) && player.getSpeedY() < 0)
        	player.setSpeedY(0);
        
        if(KeyStatus.isKeyPressed(Key.LEFT) ^ KeyStatus.isKeyPressed(Key.RIGHT))
        	player.setSpeedX(KeyStatus.isKeyPressed(Key.LEFT) ? -8 : 8);
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
	
	public void	render (Graphics2D g) {
		for (IPlayerInteractive entity : entities)
			entity.render(g);
		
		player.render(g);
	}
	
	@Override
	public void	run () {
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
