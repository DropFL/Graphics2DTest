package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.music.MusicPlayer;
import com.rshtiger.platformer.Engine;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlatformerActivity extends Activity {
	
	private Image bgImage;
	private Engine engine;
	
	public PlatformerActivity () {
		title = "Platformer Activity";
		bgm = new MusicPlayer(SoundResource.THE_FLOOR_IS_LAVA, true);
		bgImage = ImageResource.WP_720p.getImageIcon().getImage();
		engine = new Engine();
	}
	
	@Override
	public void start () {
		engine.start();
		bgm.start();
	}
	
	@Override
	public void close () {
		engine.interrupt();
	}
	
	@Override
	public void render (Graphics2D g) {
		// Pre-renderer goes here
		g.drawImage(bgImage, 0, 0, null);
		
		// Engine renders here
		engine.render(g);
		
		// Post-renderer goes here
		// none..
	}
}
