package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.music.MusicPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Activity {
	
	protected String title;
	protected MusicPlayer bgm;
	
	public abstract void render (Graphics2D g);		// How about returning Graphics2D or BufferedImage?
	public abstract void start ();
	public abstract void close ();
	
	public String getTitle () {
		return title;
	}
}
