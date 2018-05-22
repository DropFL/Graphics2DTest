package com.dropfl.effect;

import com.dropfl.Main;

import java.awt.*;
import java.awt.image.VolatileImage;

public abstract class ScreenEffect {
	protected static VolatileImage image;
	protected static Graphics2D graphics;
	private static GraphicsConfiguration config;
	
	public static void init (Component c) {
		config = c.getGraphicsConfiguration();
		createImage();
	}
	
	protected static void createImage () {
		image = config.createCompatibleVolatileImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	
	protected static void updateImage () {
		if (image.validate(config) == VolatileImage.IMAGE_INCOMPATIBLE)
			createImage();
		graphics = image.createGraphics();
	}
	
	public abstract void apply (VolatileImage image, RenderingHints hints);
}
