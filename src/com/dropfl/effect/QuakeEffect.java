package com.dropfl.effect;

import com.dropfl.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;
import java.util.Random;

public class QuakeEffect extends ScreenEffect {
	
	private double strengthX;
	private double strengthY;
	private int seed;
	
	public QuakeEffect (double strengthX, double strengthY) {
		this.strengthX = strengthX;
		this.strengthY = strengthY;
		this.seed = 0;
	}
	
	public QuakeEffect (double strength) {
		this(strength, strength);
	}
	
	public double getStrengthX () {
		return strengthX;
	}
	public double getStrengthY () {
		return strengthY;
	}
	public int getSeed () {
		return seed;
	}
	
	public void setStrengthX (double strengthX) {
		this.strengthX = strengthX;
	}
	public void setStrengthY (double strengthY) {
		this.strengthY = strengthY;
	}
	public void setSeed (int seed) {
		this.seed = seed;
	}
	
	@Override
	public void apply (VolatileImage image, RenderingHints hints) {
		
		if(strengthX < 1 && strengthY < 1) return;
		
		updateImage();
		graphics.drawImage(image, 0, 0, null);
		
		Random random = new Random(seed);
		
		AffineTransform transform = new AffineTransform();
		transform.translate((1 - random.nextDouble() * 2) * strengthX, (1 - random.nextDouble() * 2) * strengthY);
		
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		g.setRenderingHints(hints);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		g.drawImage(ScreenEffect.image, transform, null);
		
		g.dispose();
	}
}
