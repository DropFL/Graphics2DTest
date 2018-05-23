package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.ScaleEffect;
import com.dropfl.music.MusicPlayer;
import res.FontResource;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.awt.image.VolatileImage;

public final class MainActivity extends Activity{
	
	private Image bgImage;
	private int deg;
	private float scale;
	
	public MainActivity (Component c) {
		super(c);
		
		title = "Main Activity";
		bgm = new MusicPlayer(SoundResource.THE_FLOOR_IS_LAVA, true);
		bgImage = ImageResource.MAP_1.getImageIcon().getImage();
		scale = 1;
		hints = new RenderingHints(null);
		
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	@Override
	public VolatileImage getScreen () {
		updateImage();
		graphics.setRenderingHints(hints);
		
		int time = bgm.getTime();
		int prevDeg = deg;
		
		deg = (time) * 4 % 1875 * 360 / 1875;
		if(deg < prevDeg) scale = 1.05f;
		
		graphics.drawImage(bgImage, 0, 0, null);
		
		graphics.setStroke(new BasicStroke(4));
		graphics.drawOval(Main.SCREEN_WIDTH / 2 + 100, Main.SCREEN_HEIGHT / 2 - 50, 100, 100);
		graphics.drawOval(Main.SCREEN_WIDTH / 2 - 200, Main.SCREEN_HEIGHT / 2 - 50, 100, 100);
		
		graphics.setFont(FontResource.PACIFITO.getFont(Font.PLAIN, 24));
		graphics.drawString("Progress", Main.SCREEN_WIDTH / 2 - 200, Main.SCREEN_HEIGHT / 2 - 60);
		graphics.drawString("BPM", Main.SCREEN_WIDTH / 2 + 125, Main.SCREEN_HEIGHT / 2 - 60);
		
		graphics.setColor(new Color(0, 0, 0,128));
		graphics.fillArc(Main.SCREEN_WIDTH / 2 + 100, Main.SCREEN_HEIGHT / 2 - 50, 100, 100,
				90, deg);
		graphics.fillArc(Main.SCREEN_WIDTH / 2 - 200, Main.SCREEN_HEIGHT / 2 - 50, 100, 100,
				90, time * 360 / bgm.getLength());
		graphics.dispose();

//		g.drawImage(image, (int) (Main.SCREEN_WIDTH * (1 - scale) / 2), (int) (Main.SCREEN_HEIGHT * (1 - scale) / 2),
//				(int) (Main.SCREEN_WIDTH * scale), (int) (Main.SCREEN_HEIGHT * scale), null);
		
		(new ScaleEffect(scale, scale)).apply(image, hints);
		scale = 1 + (scale - 1) / 1.1f;
		
		return image;
	}
	
	@Override
	public void start () {
		bgm.start();
		createImage();
	}
	
	@Override
	public void close () {
		if(bgm.isAlive())
			bgm.finish();
	}
}
