package com.rshtiger.platformer;

import com.dropfl.component.IDrawable;
import com.rshtiger.key.Key;
import com.rshtiger.key.KeyStatus;
import com.rshtiger.platformer.entity.Block;
import com.rshtiger.platformer.entity.Bullet;
import com.rshtiger.platformer.entity.Fireball;
import com.rshtiger.platformer.entity.Player;
import com.rshtiger.platformer.entity.PlayerInteractive;
import res.MapResource;

import java.awt.*;
import java.util.ArrayList;

public final class Engine implements IDrawable {
	
	private double gravity = 1;
	private double speed = 1;
	private boolean inputAvailable = true;
	
	// Entities
	private Player player;
	private Map map;
	
	private ArrayList<Block> blocks;
	private ArrayList<PlayerInteractive> entities = new ArrayList<>();
	private ArrayList<PlayerInteractive> removeEntities = new ArrayList<>();
	
	public Engine (MapResource mapResource) {
		map = mapResource.getMapData();
		blocks = map.getBlocks();
		player = new Player();
		entities.add(new Fireball(200, 600, 100,100));
		entities.add(new Bullet(100, 600, 100,100));
	}
	
	public double getGravity () {
		return gravity;
	}
	public double getSpeed () {
		return speed;
	}
	
	public void setGravity (double gravity) {
		this.gravity = gravity;
	}
	public void setSpeed (double speed) {
		this.speed = speed;
	}
	public void setInputAvailable (boolean inputAvailable) {
		this.inputAvailable = inputAvailable;
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
	public boolean isInputAvailable () {
		return inputAvailable;
	}
	
	public ArrayList<Block> getBlocks () {
		return blocks;
	}
	public ArrayList<PlayerInteractive> getEntities () {
		return entities;
	}
	
	public Engine () {
		this(MapResource.TestMap);
	}
	
	public void	tick () {
		
		if(isInputAvailable())
			handleInput();
		
		if (player.getShieldTime() > 0) {
			player.setShieldTime(player.getShieldTime() - 1);
		}
		else{
			player.shieldOff();
		}

		player.addX(speed * player.getSpeedX());
		player.addY(speed * player.getSpeedY());
		player.addSpeedY(speed * gravity);

		for (PlayerInteractive e : blocks) {
			if (e.isCollided(player))
				e.interact(player);
		}
		for (PlayerInteractive e : entities) {
			if (e.isCollided(player) && e.interact(player)) removeEntities.add(e);
		}
		
		entities.removeAll(removeEntities);
		removeEntities.clear();
	}
	
	public void	render (Graphics2D g) {
		for (PlayerInteractive entity : blocks)
			entity.render(g);

		for (PlayerInteractive entity : entities)
			entity.render(g);

		player.render(g);
	}
	
	private void handleInput () {
		if (KeyStatus.isKeyJustPressed(Key.SPACE)) {
			if(player.getJumped() != 2){
				player.setJumped(player.getJumped() + 1);
				player.setSpeedY(-Player.MAX_SPEED_Y);
				KeyStatus.setKeyProcessed(Key.SPACE);
			}
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
	}
}
