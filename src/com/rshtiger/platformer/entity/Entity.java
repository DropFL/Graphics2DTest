package com.rshtiger.platformer.entity;

import com.dropfl.component.ImageComponent;
import com.rshtiger.platformer.collision.Shape;

public abstract class Entity extends ImageComponent implements Shape {
	
	protected double width;
	protected double height;
	
	@Override
	public double getLeftX () {
		return x;
	}
	
	@Override
	public double getRightX () {
		return x + width;
	}
	
	@Override
	public double getTopY () {
		return y;
	}
	
	@Override
	public double getBottomY () {
		return y + height;
	}
	
	@Override
	public double getRotation () {
		return rotation;
	}
}
