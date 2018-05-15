package com.rshtiger.platformer;

import res.ImageResource;

import java.awt.*;

public final class Player {
	
	private Image image;
	private boolean enabled;
	private Point position; // top-left vertex.
	private boolean jumped;
	private int speedX;
	private int speedY;
	private final int size;
	
	private static int MAX_SPEED_Y = 20;
	
	public Player () {
		size = 50;
		image = ImageResource.UNIT_IMAGE.getImageIcon().getImage().getScaledInstance(size, size, Image.SCALE_FAST);
		enabled = true;
		//size = image.getWidth(null);
		position = new Point(100, 100);
	}
	
	public Image getImage () {
		return image;
	}
	public int getSize () {
		return size;
	}
	public int getLeftX () {
		return position.x;
	}
	public int getTopY () {
		return position.y;
	}
	public int getRightX () {
		return position.x + size;
	}
	public int getBottomY () {
		return position.y + size;
	}
	public int getSpeedX () {
		return speedX;
	}
	public int getSpeedY () {
		return speedY;
	}
	public boolean getJumped () {
		return jumped;
	}
	public boolean isEnabled () {
		return enabled;
	}
	
	public void addPositionX (int deltaX) {
		position.x += deltaX;
	}
	public void addPositionY (int deltaY) {
		position.y += deltaY;
	}
	public void setPositionX (int positionX) {
		position.x = positionX;
	}
	public void setPositionY (int positionY) {
		position.y = positionY;
	}
	public void addSpeedX (int deltaSpeedX) {
		this.speedX += deltaSpeedX;
	}
	public void addSpeedY (int deltaSpeedY) {
		speedY += deltaSpeedY;
		if (speedY > MAX_SPEED_Y) speedY = MAX_SPEED_Y;
	}
	public void setSpeedX (int speedX) {
		this.speedX = speedX;
	}
	public void setSpeedY (int speedY) {
		if (speedY > MAX_SPEED_Y) this.speedY = MAX_SPEED_Y;
		else this.speedY = speedY;
	}
	public void setJumped (boolean jumped){
		this.jumped = jumped;
	}

//	public void hit () { ...; }
//	etc.
}
