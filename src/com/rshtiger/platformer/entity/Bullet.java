package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.AABBCollider;
import com.rshtiger.platformer.collision.OBBCollider;
import res.ImageResource;

import java.awt.*;

public class Bullet extends PlayerInteractive {
	private double speedX, speedY;
	
	public Bullet (int x, int y, int width, int height) {
		this.speedX = this.speedY = 0;
		this.x = x;
		this.y = y;
		this.rotation = 0;
		this.width = width;
		this.height = height;
		this.collider = new OBBCollider();
		this.image = ImageResource.FIRE.getImageIcon().getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
	}

	@Override
	public boolean interact (Player p) {
		p.addHp(-10);
		return true;
	}

	@Override
	public void update(){
		this.x += speedX;
		this.y += speedY;
	}
	
	public double getSpeedX() { return speedX; }
	public double getSpeedY() { return speedY; }
}
