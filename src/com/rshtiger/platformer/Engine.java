package com.rshtiger.platformer;

import com.dropfl.component.IDrawable;
import com.rshtiger.key.Key;
import com.rshtiger.key.KeyStatus;
import com.rshtiger.platformer.entity.Player;
import com.rshtiger.platformer.entity.PlayerInteractive;
import res.MapResource;

import java.awt.*;
import java.util.ArrayList;

public final class Engine extends Thread implements IDrawable {
	
	private double gravity = 1;
	private Player player;
	private Map map;
	private ArrayList<PlayerInteractive> entities;
	private double scale = 1;
	
	public Engine (MapResource mapResource) {
		map = mapResource.getMapData();
		entities = map.getBlocks();
		player = new Player();
	}
	
	public double getPlayerLeftX () {
		return player.getLeftX();
	}
	public double getPlayerRightX () {
		return player.getRightX();
	}
	public double getPlayerTopY () {
		return player.getTopY();
	}
	public double getPlayerBottomY () {
		return player.getBottomY();
	}
	
	public Engine () {
		this(MapResource.TestMap);
	}
	
	public void	tick () {
		
		if (KeyStatus.isKeyJustPressed(Key.SPACE)) {
		    if(!player.getJumped()){
		    	player.setJumped(true);
		    	player.setSpeedY(-Player.MAX_SPEED_Y);
				KeyStatus.setKeyProcessed(Key.SPACE);
            }
            // double jump?
        } else if (!KeyStatus.isKeyPressed(Key.SPACE) && player.getSpeedY() < 0)
        	player.setSpeedY(0);
        
        if(KeyStatus.isKeyPressed(Key.LEFT) ^ KeyStatus.isKeyPressed(Key.RIGHT))
        	player.setSpeedX(KeyStatus.isKeyPressed(Key.LEFT) ? -8 : 8);
        else
        	player.setSpeedX(0);

        if(KeyStatus.isKeyJustPressed(Key.S)){
			player.shieldOn();
			player.setShieldTime(30);
		}
		if (player.getShieldTime() > 0) {
			player.setShieldTime(player.getShieldTime() - 1);
		}
		else{
			player.shieldOff();
		}

		player.addX(scale * player.getSpeedX());
		player.addY(scale * player.getSpeedY());
		player.addSpeedY(scale * gravity);

		for (PlayerInteractive e : entities) {
			if (e.isCollided(player) && e.interact(player))
				entities.remove(e);
		}
	}
	
	public void	render (Graphics2D g) {
		for (PlayerInteractive entity : entities)
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
