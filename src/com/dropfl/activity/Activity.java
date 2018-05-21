package com.dropfl.activity;

import com.dropfl.component.IDrawable;
import com.dropfl.music.MusicPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Activity {
	
	protected String title;
	protected MusicPlayer bgm;
	protected RenderingHints hints;
	
	public abstract void start ();
	public abstract void close ();
	public abstract BufferedImage getScreen();
	
	public String getTitle () {
		return title;
	}
}
