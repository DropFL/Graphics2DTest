package com.rshtiger.platformer;

import com.dropfl.component.IDrawable;
import com.rshtiger.key.Key;
import com.rshtiger.key.KeyStatus;
import com.rshtiger.platformer.entity.*;
import res.MapResource;

import java.awt.*;
import java.util.ArrayList;

public final class Engine implements IDrawable {
	
	private double gravity = 1;
	private Player player;
	private Map map;
	private ArrayList<PlayerInteractive> entities;
	private ArrayList<PlayerInteractive> bullets = new ArrayList<PlayerInteractive>();
	private ArrayList<PlayerInteractive> upbullets = new ArrayList<PlayerInteractive>();;
	private double scale = 1;
	private Fireball tmpb;
	private int tmpd = 1;
	public Engine (MapResource mapResource) {
		map = mapResource.getMapData();
		entities = map.getBlocks();
		player = new Player();
		bullets.add(new Fireball(200, 600, 100,100));
		bullets.add(new Ghost(100, 600, 50,50));
	}
	
	public double getGravity () {
		return gravity;
	}
	public double getScale () {
		return scale;
	}
	
	public void setGravity (double gravity) {
		this.gravity = gravity;
	}
	public void setScale (double scale) {
		this.scale = scale;
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
		    if(player.getJumped() != 2){
		    	player.setJumped(player.getJumped() + 1);
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
		for (PlayerInteractive e : bullets) {
			if (!(e.isCollided(player) && e.interact(player))) upbullets.add(e);

		}
		bullets.clear();
		for (PlayerInteractive e : upbullets) {
			bullets.add(e);
		}
		upbullets.clear();
	}
	
	public void	render (Graphics2D g) {
		for (PlayerInteractive entity : entities)
			entity.render(g);

		for (PlayerInteractive entity : bullets)
			entity.render(g);

		player.render(g);
	}
}
