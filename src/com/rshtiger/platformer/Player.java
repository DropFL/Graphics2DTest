package com.rshtiger.platformer;

import res.ImageResource;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public final class Player {
	
	private Image image;
	private boolean enabled;
	private Point position; // top-left vertex.
	private int SquareSize;
	private boolean jumped;
	private int speed_x;
	private int speed_y;

	public Player () {
		image = ImageResource.UNIT_IMAGE.getImageIcon().getImage();
		enabled = true;
		position = new Point(100, 100);
	}
	
	public Image getImage () {
		return image;
	}
	public Point getPosition () {
		return (Point) position.clone();
	}
	public int getPositionX () {
		return position.x;
	}
	public int getPositionY () { return position.y; }
	public void movPositionX(int mx) { position.x += mx; }
	public void movPositionY(int my) { position.y += my; }
	public boolean isEnabled () { return enabled; }
	public void setSpeedX(int newsx) { speed_x = newsx; }
	public void setSpeedY(int newsy) { speed_y = newsy; }
	public int getSpeedX() { return speed_x; }
	public int getSpeedY() { return speed_y; }
	public int getSqrSize() { return SquareSize; }
	public void SetUnitJump(boolean tf){ jumped = tf; }
	public boolean isJumped() { return jumped; }
	//	public Area getArea () { /* return proper area */ }

//	public void hit () { ...; }
//	etc.
}
