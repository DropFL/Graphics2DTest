package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.music.MusicPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Activity {
	
	protected String title;
	protected BufferedImage image = new BufferedImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	protected MusicPlayer bgm;
	
	public abstract void render (Graphics2D g);
	public abstract void start ();
	public abstract void close ();
	
	public String getTitle () {
		return title;
	}
}
