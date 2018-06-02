package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.AABBCollider;

import java.awt.*;

public class Bullet extends PlayerInteractive {
	private int speedX, speedY;
	
	public Bullet (int width, int height, Image img) {
		this.width = width;
		this.height = height;
		this.collider = new AABBCollider();
		this.image = img;
	}

	@Override
	public boolean interact (Player p) {
		p.addHp(-10);
		return true;
	}

	@Override
	public void active(){
		this.x += speedX;
		this.y += speedY;
	}
	
	public int getSpeedX() { return speedX; }
	public int getSpeedY() { return speedY; }
}
