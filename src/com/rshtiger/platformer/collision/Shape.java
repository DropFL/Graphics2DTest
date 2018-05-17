package com.rshtiger.platformer.collision;

import com.dropfl.component.IDrawable;

import java.awt.*;

public abstract class Shape implements IDrawable {
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double rotation;
	protected Image image;
	
	double getX () {
		return x;
	}
	
	double getY () {
		return y;
	}
	
	double getWidth () {
		return width;
	}
	
	double getHeight () {
		return height;
	}
	
	double getRotation () {
		return rotation;
	}
}
