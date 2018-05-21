package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.CropEffect;
import com.dropfl.music.MusicPlayer;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class MainActivity extends Activity{
	
	private Image bgImage;
	private int deg;
	private float scale;
	
	public MainActivity () {
		title = "Main Activity";
		bgm = new MusicPlayer(SoundResource.THE_FLOOR_IS_LAVA, true);
		bgImage = ImageResource.MAP_1.getImageIcon().getImage();
		scale = 1;
		hints = new RenderingHints(null);
		
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}
	
	@Override
	public BufferedImage getScreen () {
		
		BufferedImage image = new BufferedImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)image.getGraphics();
		
		g2d.setRenderingHints(hints);
		
		int time = bgm.getTime();
		int prevDeg = deg;
		
		deg = (time) * 4 % 1875 * 360 / 1875;
		if(deg < prevDeg) scale = 1.05f;
		
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

//		g.drawImage(image, (int) (Main.SCREEN_WIDTH * (1 - scale) / 2), (int) (Main.SCREEN_HEIGHT * (1 - scale) / 2),
//				(int) (Main.SCREEN_WIDTH * scale), (int) (Main.SCREEN_HEIGHT * scale), null);
		
		(new CropEffect(scale, scale)).apply(image);
		scale = 1 + (scale - 1) / 1.1f;
		
		return image;
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
