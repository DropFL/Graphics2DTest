package com.dropfl.effect;

import com.dropfl.Main;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.VolatileImage;

public class ImageOverlayEffect extends ScreenEffect {
	
	private double x;
	private double y;
	private Image image;
	private double opacity;
	
	public ImageOverlayEffect (double x, double y, Image image, double opacity) {
		this.x = x;
		this.y = y;
		this.image = image;
		this.opacity = opacity;
	}
	
	public ImageOverlayEffect (double x, double y, Image image) {
		this(x, y, image, 1);
	}
	
	public double getX () {
		return x;
	}
	public double getY () {
		return y;
	}
	public Image getImage () {
		return image;
	}
	public double getOpacity () {
		return opacity;
	}
	
	public void setX (double x) {
		this.x = x;
	}
	public void setY (double y) {
		this.y = y;
	}
	public void setImage (Image image) {
		this.image = image;
	}
	public void setOpacity (double opacity) {
		this.opacity = opacity;
	}
	
	@Override
	public void apply (VolatileImage image) {
		
		if(opacity == 0) return;
		
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		if(opacity != 1)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)opacity));
		
		g.setRenderingHints(Main.getRenderingHint());
		g.drawImage(this.image, AffineTransform.getTranslateInstance(x, y), null);
		
		g.dispose();
	}
}
