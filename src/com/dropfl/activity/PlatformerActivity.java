package com.dropfl.activity;

import com.dropfl.effect.RotateEffect;
import com.dropfl.effect.ScaleEffect;
import com.dropfl.music.MusicPlayer;
import com.rshtiger.platformer.Engine;
import res.FontResource;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.awt.image.VolatileImage;

public class PlatformerActivity extends Activity {
	
	private Image bgImage;
	private Engine engine;
	private int prev;
	private ScaleEffect effect;
	private RotateEffect effect2;
	
	public PlatformerActivity (Component c) {
		super(c);
		
		title = "Platformer Activity";
		bgm = new MusicPlayer(SoundResource.THE_GHOST, true);
		bgImage = ImageResource.MAP_1.getImageIcon().getImage();
		engine = new Engine();
		hints = new RenderingHints(null);
		prev = 0;
		effect = new ScaleEffect(1, 1, 0, 0);
		effect2 = new RotateEffect(0);
		
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	}
	
	@Override
	public void start () {
		engine.start();
		bgm.start();
		createImage();
	}
	
	@Override
	public void close () {
		engine.interrupt();
	}
	
	@Override
	public VolatileImage getScreen () {
		updateImage();
		
		graphics.setRenderingHints(hints);
		
		// Pre-renderer goes here
		graphics.drawImage(bgImage, 0, 0, null);
		
		// Engine renders here
		engine.render(graphics);
		
		// Post-renderer goes here
		// Font Test
		graphics.setColor(Color.WHITE);
		graphics.setFont(FontResource.PACIFITO.getFont(Font.PLAIN, 36));
		graphics.drawString("Test", 100, 100);
		
		graphics.dispose();
		
		//Effect Test
		int cur = bgm.getTime() * 4 % 1875;
		if (cur < prev) {
			if(effect.getScaleX() > 1) {
				effect.setScaleX(0.95);
				effect.setScaleY(0.95);
				effect2.setRotation(-5);
			} else {
				effect.setScaleX(1.05);
				effect.setScaleY(1.05);
				effect2.setRotation(5);
			}
		} else {
			double scale = (effect.getScaleX() - 1) / 1.1 + 1;
			effect.setScaleX(scale);
			effect.setScaleY(scale);
			
			effect2.setRotation(effect2.getRotation() / 1.1);
		}
		prev = cur;
		
		effect.setPivotX((engine.getPlayerLeftX() + engine.getPlayerRightX()) / 2);
		effect.setPivotY((engine.getPlayerTopY() + engine.getPlayerBottomY()) / 2);
		effect2.setPivotX((engine.getPlayerLeftX() + engine.getPlayerRightX()) / 2);
		effect2.setPivotY((engine.getPlayerTopY() + engine.getPlayerBottomY()) / 2);
		
		effect2.apply(image, hints);
		effect.apply(image, hints);
		
		return image;
	}
}
