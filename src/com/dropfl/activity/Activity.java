package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.ScreenEffect;

import java.awt.*;
import java.awt.image.VolatileImage;

public abstract class Activity {
	
	protected static GraphicsConfiguration config;
	
	private boolean paused;
	
	protected String title;
	protected RenderingHints hints;
	protected VolatileImage image;
	protected Graphics2D graphics;
	
	protected final void createImage () {
		image = config.createCompatibleVolatileImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	protected final void updateImage () {
		if (image.validate(config) == VolatileImage.IMAGE_INCOMPATIBLE)
			createImage();
		graphics = image.createGraphics();
	}
	
	protected void onPause () {
		// do nothing.
	}
	protected void onResume () {
		// do nothing
	}
	protected boolean isPaused () {
		return paused;
	}
	
	public static void init (Component c) {
		config = c.getGraphicsConfiguration();
		ScreenEffect.init(c);
	}
	
	public abstract void start ();
	public abstract void close ();
	public abstract VolatileImage getScreen();
	
	public String getTitle () {
		return title;
	}
	
	public final void pause () {
		if(!paused) {
			paused = true;
			onPause();
		}
	}
	public final void resume () {
		if(paused) {
			paused = false;
			onResume();
		}
	}
	
	public static void syncState (Activity src, Activity dst) {
		dst.paused = src.paused;
	}
}
