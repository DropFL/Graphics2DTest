package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.music.MusicPlayer;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class MainActivity extends Activity{
	
	private Image bgImage;
	private int deg;
	private float scale;
	private BufferedImage image;
	
	public MainActivity () {
		title = "Main Activity";
		bgm = new MusicPlayer(SoundResource.THE_FLOOR_IS_LAVA, true);
		image = new BufferedImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		bgImage = ImageResource.WP_720p.getImageIcon().getImage();
		scale = 1;
	}
	
	@Override
	public void render (Graphics2D g) {
		
		int time = bgm.getTime();
		int prevDeg = deg;
		
		deg = (time) * 4 % 1875 * 360 / 1875;
		if(deg < prevDeg) scale = 1.05f;
		
		Graphics2D g2d = image.createGraphics();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g2d.drawImage(bgImage, 0, 0, null);
		
		g2d.setStroke(new BasicStroke(4));
		g2d.drawOval(Main.SCREEN_WIDTH / 2 + 100, Main.SCREEN_HEIGHT / 2 - 50, 100, 100);
		g2d.drawOval(Main.SCREEN_WIDTH / 2 - 200, Main.SCREEN_HEIGHT / 2 - 50, 100, 100);
		
		g2d.setFont(new Font("Arial", Font.PLAIN, 24));
		g2d.drawString("Progress", Main.SCREEN_WIDTH / 2 - 200, Main.SCREEN_HEIGHT / 2 - 60);
		g2d.drawString("BPM", Main.SCREEN_WIDTH / 2 + 125, Main.SCREEN_HEIGHT / 2 - 60);
		
		g2d.setColor(new Color(255, 255, 255,128));
		g2d.fillArc(Main.SCREEN_WIDTH / 2 + 100, Main.SCREEN_HEIGHT / 2 - 50, 100, 100,
				90, deg);
		g2d.fillArc(Main.SCREEN_WIDTH / 2 - 200, Main.SCREEN_HEIGHT / 2 - 50, 100, 100,
				90, time * 360 / bgm.getLength());
		g2d.dispose();
		
		g.drawImage(image, (int) (Main.SCREEN_WIDTH * (1 - scale) / 2), (int) (Main.SCREEN_HEIGHT * (1 - scale) / 2),
				(int) (Main.SCREEN_WIDTH * scale), (int) (Main.SCREEN_HEIGHT * scale), null);
		
		scale = 1 + (scale - 1) / 1.1f;
	}
	
	@Override
	public void start () {
		bgm.start();
	}
	
	@Override
	public void close () {
		if(bgm.isAlive())
			bgm.finish();
	}
}
