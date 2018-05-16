package com.rshtiger.platformer;

import com.dropfl.Main;
import com.dropfl.component.IDrawable;
import res.ImageResource;

import java.awt.*;

public final class Player implements IDrawable {
	
	private Image image;
	private boolean enabled;
	private Point position; // top-left vertex.
	private boolean jumped;
	private int speedX;
	private int speedY;
	private final int size;
	private int hp;
	public final static int MAX_SPEED_Y = 15;
	public final static int MAX_HP = 100;

	private Image ShieldImage;
	private int ShieldCount;
	private int ShieldTime;
	private boolean isShieldOn;

	public Player (int x, int y) {
		size = 32;
		image = ImageResource.UNIT_IMAGE.getImageIcon().getImage().getScaledInstance(size, size, Image.SCALE_FAST);
		enabled = true;
		position = new Point(x, y);
		hp = MAX_HP;

		isShieldOn = false;
		ShieldImage = ImageResource.Shield.getImageIcon().getImage().getScaledInstance((int)(size*1.6), (int)(1.6*size), Image.SCALE_FAST);
		ShieldCount = 3;
	}
	public Player () {
		this(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT / 2);
	}
	
	
	public int		getHp () {
		return hp;
	}
	public int		getSize () {
		return size;
	}
	public int		getLeftX () {
		return position.x;
	}
	public int		getTopY () {
		return position.y;
	}
	public int		getRightX () {
		return position.x + size;
	}
	public int		getBottomY () {
		return position.y + size;
	}
	public int		getSpeedX () {
		return speedX;
	}
	public int		getSpeedY () {
		return speedY;
	}
	public boolean	getJumped () {
		return jumped;
	}
	public boolean	isEnabled () {
		return enabled;
	}
	
	public void		setPositionX (int positionX) {
		position.x = positionX;
	}
	public void		setPositionY (int positionY) {
		position.y = positionY;
	}
	public void		setSpeedX (int speedX) {
		this.speedX = speedX;
	}
	public void		setSpeedY (int speedY) {
		if (speedY > MAX_SPEED_Y) this.speedY = MAX_SPEED_Y;
		else this.speedY = speedY;
	}
	public void		setJumped (boolean jumped){
		this.jumped = jumped;
	}
	
	public void		addHp (int deltaHp) {
		if(isShieldOn && deltaHp < 0){
			isShieldOn = false;
			return;
		}
		hp += deltaHp;
		if (hp < 0); // this.die();
		else if (hp > MAX_HP) hp = MAX_HP;
	}
 	public void		addSpeedX (int deltaSpeedX) {
		this.speedX += deltaSpeedX;
	}
	public void		addSpeedY (int deltaSpeedY) {
		speedY += deltaSpeedY;
		if (speedY > MAX_SPEED_Y) speedY = MAX_SPEED_Y;
	}
	public void		addPositionX (int deltaX) {
		position.x += deltaX;
	}
	public void		addPositionY (int deltaY) {
		position.y += deltaY;
	}

	public void ShieldOn(){
		if(isShieldOn == false && ShieldCount-- > 0) {
			isShieldOn = true;
		}
	}
	public void ShieldOff(){
		if(isShieldOn == false) return;
		isShieldOn = false;
	}
	public void SetShieldTime(int t){ ShieldTime = t; }
	public int GetShieldTime() { return ShieldTime; }
	public boolean chkShieldOn() { return isShieldOn; }
	@Override
	public void render (Graphics2D g) {
		g.drawImage(image, position.x, position.y, null);
		if(isShieldOn) g.drawImage(ShieldImage, position.x - 10, position.y - 10, null);
	}
}
