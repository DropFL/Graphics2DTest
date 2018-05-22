package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.ScreenEffect;
import com.dropfl.music.MusicPlayer;

import java.awt.*;
import java.awt.image.VolatileImage;

public abstract class Activity {
	
	protected String title;
	protected MusicPlayer bgm;
	protected RenderingHints hints;
	protected VolatileImage image;
	protected Graphics2D graphics;
	protected GraphicsConfiguration config;
	
	protected Activity (Component c) {
		config = c.getGraphicsConfiguration();
		ScreenEffect.init(c);
	}
	
	protected void createImage () {
		image = config.createCompatibleVolatileImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	
	protected void updateImage () {
		if (image.validate(config) == VolatileImage.IMAGE_INCOMPATIBLE)
			createImage();
		graphics = image.createGraphics();
	}
	
	public abstract void start ();
	public abstract void close ();
	public abstract VolatileImage getScreen();
	
	public String getTitle () {
		return title;
	}
}
