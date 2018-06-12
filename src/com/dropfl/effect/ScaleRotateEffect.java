package com.dropfl.effect;

import com.dropfl.Main;

import java.awt.image.VolatileImage;

public class ScaleRotateEffect extends ScreenEffect{
	
	private ScaleEffect xScale, yScale;
	private RotateEffect rotate;
	
	public ScaleRotateEffect (double scaleX, double scaleY, double scalePivotX, double scalePivotY,
							  double rotation, double rotatePivotX, double rotatePivotY) {
		xScale = new ScaleEffect(scaleX, 1, scalePivotX, scalePivotY);
		yScale = new ScaleEffect(1, scaleY, scalePivotX, scalePivotY);
		rotate = new RotateEffect(rotation, rotatePivotX, rotatePivotY);
	}
	
	public ScaleRotateEffect (double scaleX, double scaleY, double rotation, double pivotX, double pivotY) {
		this(scaleX, scaleY, pivotX, pivotY, rotation, pivotX, pivotY);
	}
	
	public ScaleRotateEffect (double scaleX, double scaleY, double rotation) {
		this(scaleX, scaleY, rotation, Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT / 2);
	}
	
	public double getScaleX () {
		return xScale.getScaleX();
	}
	public double getScaleY () {
		return yScale.getScaleY();
	}
	public double getScalePivotX () {
		return xScale.getPivotX();
	}
	public double getScalePivotY () {
		return xScale.getPivotY();
	}
	public double getRotation () {
		return rotate.getRotation();
	}
	public double getRotatePivotX () {
		return rotate.getPivotX();
	}
	public double getRotatePivotY () {
		return rotate.getPivotY();
	}
	
	public void setScaleX (double scaleX) {
		xScale.setScaleX(scaleX);
	}
	public void setScaleY (double scaleY) {
		yScale.setScaleY(scaleY);
	}
	public void setScale (double scale) {
		xScale.setScaleX(scale);
		yScale.setScaleY(scale);
	}
	public void setScalePivotX (double pivotX) {
		xScale.setPivotX(pivotX);
		yScale.setPivotX(pivotX);
	}
	public void setScalePivotY (double pivotY) {
		xScale.setPivotY(pivotY);
		yScale.setPivotY(pivotY);
	}
	public void setRotation (double rotation) {
		rotate.setRotation(rotation);
	}
	public void setRotatePivotX (double pivotX) {
		rotate.setPivotX(pivotX);
	}
	public void setRotatePivotY (double pivotY) {
		rotate.setPivotY(pivotY);
	}
	public void setPivotX (double pivotX) {
		setScalePivotX(pivotX);
		setRotatePivotX(pivotX);
	}
	public void setPivotY (double pivotY) {
		setScalePivotY(pivotY);
		setRotatePivotY(pivotY);
	}
	
	@Override
	public void apply (VolatileImage image) {
		double factorX = xScale.getScaleX(), factorY = yScale.getScaleY();
		
		if(factorX < 1) xScale.apply(image);
		if(factorY < 1) yScale.apply(image);
		
		rotate.apply(image);
		
		if(factorX > 1) xScale.apply(image);
		if(factorY > 1) yScale.apply(image);
	}
}