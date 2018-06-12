package com.rshtiger.platformer.entity;

import com.dropfl.Main;
import com.rshtiger.platformer.collision.Shape;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

public final class Player extends Entity {
	
	private boolean enabled;
	private int jumped;
	private double speedX;
	private double speedY;
	private int hp;
	private int size;
	
	private Image shieldImage;
	private Image hpBar;
	private Image hpb;
	private Image shieldIco;
	private int shieldCount;
	private int shieldTime;
	private boolean isShieldOn;
	
	public final static double MAX_SPEED_Y = 15;
	public final static int MAX_HP = 100;
	
	public Player (int x, int y) {
		width = height = size = 80;
		image = ImageResource.UNIT_IMAGE
					.getImageIcon()
					.getImage()
					.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		enabled = true;
		hp = MAX_HP;
		
		this.rotation = 0;
		this.x = x;
		this.y = y;
		
		shieldImage = ImageResource.SHIELD
						.getImageIcon()
						.getImage()
						.getScaledInstance((int)(size * 1.6), (int)(size * 1.6), Image.SCALE_FAST);
		isShieldOn = false;
		shieldCount = 3;

		hpBar= ImageResource.HPBAR
				.getImageIcon()
				.getImage()
				.getScaledInstance(30, 404, Image.SCALE_FAST);


		hpb = ImageResource.HP
				.getImageIcon()
				.getImage()
				.getScaledInstance(26, 4 * hp, Image.SCALE_FAST);
		shieldIco = ImageResource.SHIELDICO
				.getImageIcon()
				.getImage()
				.getScaledInstance(30, 30 , Image.SCALE_FAST);
	}
	
	public Player () {
		this(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT / 2);
	}
	
	// Getters
	public int		getHp () {
		return hp;
	}
	public int		getSize () {
		return size;
	}
	public int		getShieldTime () {
		return shieldTime;
	}
	public int		getJumped () {
		return jumped;
	}
	public double	getSpeedX () {
		return speedX;
	}
	public double	getSpeedY () {
		return speedY;
	}
	public boolean	isEnabled () {
		return enabled;
	}
	public boolean	isShieldOn () {
		return isShieldOn;
	}
	
	// Setters
	public void		setSpeedX (double speedX) {
		this.speedX = speedX;
	}
	public void		setSpeedY (double speedY) {
		if (speedY > MAX_SPEED_Y) this.speedY = MAX_SPEED_Y;
		else this.speedY = speedY;
	}
	public void		setJumped (int jumped) {
		this.jumped = jumped;
	}
	public void		setShieldTime (int t) {
		shieldTime = t;
	}
	
	// Adders
	public void		addX (double deltaX) {
		x += deltaX;
	}
	public void		addY (double deltaY) {
		y += deltaY;
	}
	public void		addHp (int deltaHp) {
		if (isShieldOn && deltaHp < 0) {
			isShieldOn = false;
			return;
		}
		hp += deltaHp;
		hpb = ImageResource.HP
				.getImageIcon()
				.getImage()
				.getScaledInstance(26, 4 * hp, Image.SCALE_FAST);
		System.out.println(hp);
		if (hp < 0) ; // this.die();
		else if (hp > MAX_HP) hp = MAX_HP;
	}
	public void		addSpeedX (double deltaSpeedX) {
		this.speedX += deltaSpeedX;
	}
	public void		addSpeedY (double deltaSpeedY) {
		speedY += deltaSpeedY;
		if (speedY > MAX_SPEED_Y) speedY = MAX_SPEED_Y;
	}
	
	// Others
	public void		shieldOn () {
		if (!isShieldOn && shieldCount > 0) {
			shieldCount--;
			isShieldOn = true;
		}
	}
	public void		shieldOff () {
		isShieldOn = false;
	}
	
	
	@Override
	public void render (Graphics2D g) {
		super.render(g);
		AffineTransform hb = new AffineTransform();
		hb.translate(1235, 200);
		g.drawImage(hpBar, hb,null);

		AffineTransform h = new AffineTransform();
		h.translate(1237, 202 + 4 * (100 - hp));
		g.drawImage(hpb, h,null);

		for(int i = 0; i < shieldCount; i++){
			AffineTransform sd = new AffineTransform();
			sd.translate(10 + 30 * i, 10);
			g.drawImage(shieldIco, sd,null);
		}
		if (isShieldOn) {
			AffineTransform t = new AffineTransform();
			t.translate(x - width / 4, y - height / 4);
			g.drawImage(shieldImage, t, null);
		}
	}

}
