package com.rshtiger.platformer;

import com.dropfl.Main;
import com.dropfl.component.IDrawable;
import com.rshtiger.platformer.collision.Shape;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

public final class Player extends Shape {
	
	private Image image;
	private boolean enabled;
	private Point.Double position; // top-left vertex.
	private boolean jumped;
	private int speedX;
	private int speedY;
	private final int size;
	private int hp;
	private double x;
	private double y;
	
	private Image shieldImage;
	private int shieldCount;
	private int shieldTime;
	private boolean isShieldOn;
	
	public final static int MAX_SPEED_Y = 15;
	public final static int MAX_HP = 100;
	
	
	public Player (int x, int y) {
		size = 32;
		image = ImageResource.UNIT_IMAGE.getImageIcon().getImage().getScaledInstance(size, size, Image.SCALE_FAST);
		enabled = true;
		position = new Point.Double(x, y);
		hp = MAX_HP;
		
		shieldImage = ImageResource.SHIELD.getImageIcon().getImage().getScaledInstance((int)(size * 1.6), (int)(1.6 * size), Image.SCALE_FAST);
		isShieldOn = false;
		shieldCount = 3;
	}
	
	public Player () {
		this(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT / 2);
	}
	
	// Getters
	public int getHp () {
		return hp;
	}
	
	public int getSize () {
		return size;
	}
	
	public int getSpeedX () {
		return speedX;
	}
	
	public int getSpeedY () {
		return speedY;
	}
	
	public int getShieldTime () {
		return shieldTime;
	}
	
	public double getLeftX () {
		return position.x;
	}
	
	public double getTopY () {
		return position.y;
	}
	
	public double getRightX () {
		return position.x + size;
	}
	
	public double getBottomY () {
		return position.y + size;
	}
	
	public boolean getJumped () {
		return jumped;
	}
	
	public boolean isEnabled () {
		return enabled;
	}
	
	public boolean isShieldOn () {
		return isShieldOn;
	}
	
	// Setters
	public void setPositionX (double positionX) {
		position.x = positionX;
	}
	
	public void setPositionY (double positionY) {
		position.y = positionY;
	}
	
	public void setSpeedX (int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY (int speedY) {
		if (speedY > MAX_SPEED_Y) this.speedY = MAX_SPEED_Y;
		else this.speedY = speedY;
	}
	
	public void setJumped (boolean jumped) {
		this.jumped = jumped;
	}
	
	public void setShieldTime (int t) {
		shieldTime = t;
	}
	
	// Adders
	public void addHp (int deltaHp) {
		if (isShieldOn && deltaHp < 0) {
			isShieldOn = false;
			return;
		}
		hp += deltaHp;
		if (hp < 0) ; // this.die();
		else if (hp > MAX_HP) hp = MAX_HP;
	}
	
	public void addSpeedX (int deltaSpeedX) {
		this.speedX += deltaSpeedX;
	}
	
	public void addSpeedY (int deltaSpeedY) {
		speedY += deltaSpeedY;
		if (speedY > MAX_SPEED_Y) speedY = MAX_SPEED_Y;
	}
	
	public void addPositionX (int deltaX) {
		position.x += deltaX;
	}
	
	public void addPositionY (int deltaY) {
		position.y += deltaY;
	}
	
	// Others
	public void shieldOn () {
		if (isShieldOn == false && shieldCount-- > 0) {
			isShieldOn = true;
		}
	}
	
	public void shieldOff () {
		isShieldOn = false;
	}
	
	
	@Override
	public void render (Graphics2D g) {
		AffineTransform t = new AffineTransform();
		
		t.translate(position.getX(), position.getY());
		g.drawImage(image, t, null);
		
		if (isShieldOn) {
			t.translate(-10, -10);
			g.drawImage(shieldImage, t, null);
		}
	}
}
