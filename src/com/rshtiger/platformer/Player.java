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
	private int SquareSize = 100;
	private boolean jumped;
	private int speed_x, save_speed_x;
	private int speed_y, save_speed_y;
	private int JmpTime = 0;
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
	public int getLeftX () {
		return position.x;
	}
	public int getTopY () { return position.y; }
	public int getRightX () {
		return position.x + SquareSize;
	}
	public int getBottomY () { return position.y + SquareSize; }
	public void movPositionX(int mx) { position.x += mx; }
	public void movPositionY(int my) { position.y += my; }
	public boolean isEnabled () { return enabled; }
	public void setSpeedX(int newsx) { speed_x = newsx; }
	public void setSpeedY(int newsy) { speed_y = newsy; }
	public int getSpeedX() { return speed_x; }
	public int getSpeedY() { return speed_y; }
	public int getSqrSize() { return SquareSize; }
	public void SetUnitJump(boolean TrueFalse){ jumped = TrueFalse; }
	public boolean isJumped() { return jumped; }
	public int getJmpTime() { return JmpTime; }
	public void setJmpTime(int newJmpT) { JmpTime = newJmpT; }
	//	public Area getArea () { /* return proper area */ }

//	public void hit () { ...; }
//	etc.
}
