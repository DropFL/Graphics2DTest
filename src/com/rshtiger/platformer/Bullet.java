package com.rshtiger.platformer;

import com.rshtiger.platformer.collision.AABBCollider;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends PlayerInteractive {
	private int speedX, speedY;
	private boolean enabled;
	
	Bullet (int width, int height, Image img) {
		this.width = width;
		this.height = height;
		this.collider = new AABBCollider();
		this.image = img;
	}
	
	@Override
	public boolean interact (Player p) {
		enabled = false;
		p.addHp(-10);
		
		return true;
	}
	
	@Override
	public void render (Graphics2D g) {
		AffineTransform t = new AffineTransform();
		
		t.translate(x, y);
		g.drawImage(image, t, null);
	}
	
}
