package com.dropfl.activity;

import com.dropfl.GameFrame;
import com.dropfl.Main;
import com.dropfl.effect.ScreenEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public abstract class Activity {
	
	private static GameFrame frame;
	
	private boolean paused = false;
	
	protected static GraphicsConfiguration config;
	
	protected String title;
	protected VolatileImage image;
	protected Graphics2D graphics;
	protected final ArrayList<JComponent> components = new ArrayList<>();
	
	public static void init (GameFrame f) {
		frame = f;
		config = frame.getGraphicsConfiguration();
		ScreenEffect.init(config);
	}
	public void syncState (Activity target) {
		// null
	}
	
	public String getTitle () {
		return title;
	}
	public ArrayList<JComponent> getComponents () {
		return components;
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
	
	public abstract void start ();
	public abstract void close ();
	public abstract VolatileImage getScreen();
	
	protected void onPause () {
		// do nothing.
	}
	protected void onResume () {
		// do nothing
	}
	protected boolean isPaused () {
		return paused;
	}
	
	protected final void createImage () {
		image = config.createCompatibleVolatileImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	protected final void updateImage () {
		if (image.validate(config) == VolatileImage.IMAGE_INCOMPATIBLE)
			createImage();
		graphics = image.createGraphics();
	}
	protected final void requestActivityChange (Class<? extends Activity> target) {
		Activity newActivity = null;
		
		try {
			newActivity = target.getConstructor().newInstance();
			syncState(newActivity);
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println("Invalid Request : " + e.getMessage());
		}
		
		frame.changeActivity(newActivity);
	}
}
