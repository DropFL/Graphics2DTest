package com.dropfl.effect;

import com.dropfl.Main;
import com.jhlabs.image.CropFilter;

import java.awt.image.BufferedImage;

public class CropEffect implements IScreenEffect {
	
	private double scaleX;
	private double scaleY;
	private double pivotX;
	private double pivotY;
	
	public CropEffect (double scaleX, double scaleY, double pivotX, double pivotY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.pivotX = pivotX;
		this.pivotY = pivotY;
	}
	
	public CropEffect (double scaleX, double scaleY) {
		this(scaleX, scaleY, Main.SCREEN_WIDTH / 2., Main.SCREEN_HEIGHT / 2.);
	}
	
	public double getScaleX () {
		return scaleX;
	}
	public double getScaleY () {
		return scaleY;
	}
	public double getPivotX () {
		return pivotX;
	}
	public double getPivotY () {
		return pivotY;
	}
	
	public void setScaleX (double scaleX) {
		this.scaleX = scaleX;
	}
	public void setScaleY (double scaleY) {
		this.scaleY = scaleY;
	}
	public void setPivotX (double pivotX) {
		this.pivotX = pivotX;
	}
	public void setPivotY (double pivotY) {
		this.pivotY = pivotY;
	}
	
	@Override
	public void apply (BufferedImage image) {
		CropFilter filter = new CropFilter( (int)((1 - scaleX) * pivotX),
											(int)((1 - scaleY) * pivotY),
											(int)(Main.SCREEN_WIDTH  / scaleX),
											(int)(Main.SCREEN_HEIGHT / scaleY) );
		
		filter.filter(image, image);
	}
}
