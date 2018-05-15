package com.dropfl.activity;

import com.dropfl.component.IDrawable;
import com.dropfl.music.MusicPlayer;

public abstract class Activity implements IDrawable {
	
	protected String title;
	protected MusicPlayer bgm;
	
	public abstract void start ();
	public abstract void close ();
	
	public String getTitle () {
		return title;
	}
}
