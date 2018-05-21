package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.music.MusicPlayer;
import com.rshtiger.platformer.Engine;
import res.FontResource;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlatformerActivity extends Activity {
	
	private Image bgImage;
	private Engine engine;
	
	public PlatformerActivity () {
		title = "Platformer Activity";
		bgm = new MusicPlayer(SoundResource.THE_GHOST, true);
		bgImage = ImageResource.MAP_1.getImageIcon().getImage();
		engine = new Engine();
		hints = new RenderingHints(null);
		
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
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
	public BufferedImage getScreen () {
		
		BufferedImage image = new BufferedImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)image.getGraphics();
		g2d.setRenderingHints(hints);
		
		// Pre-renderer goes here
		g2d.drawImage(bgImage, 0, 0, null);
		
		// Engine renders here
		engine.render(g2d);
		
		// Post-renderer goes here
		// Font Test
		g2d.setColor(Color.WHITE);
		g2d.setFont(FontResource.PACIFITO.getFont(Font.PLAIN, 36));
		g2d.drawString("Test", 100, 100);
		
		g2d.dispose();
		
		return image;
	}
}
