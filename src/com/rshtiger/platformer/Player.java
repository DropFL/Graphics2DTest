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
	public int getPositionY () {
		return position.y;
	}
	public boolean isEnabled () {
		return enabled;
	}
	//	public Area getArea () { /* return proper area */ }

//	public void hit () { ...; }
//	etc.
}
