package com.dropfl.component;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ImageComponent implements IDrawable {
	
	protected double x;
	protected double y;
	protected double rotation;
	protected Image image;
	
	@Override
	public void render (Graphics2D g) {
		AffineTransform t = new AffineTransform();
		
		t.rotate(rotation * 180 / Math.PI);
		t.translate(x, y);
		
		g.drawImage(image, t, null);
	}
}
