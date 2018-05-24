package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.*;
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
	private JitterEffect effect1;
	private PixelateEffect effect2;
	private ImageOverlayEffect effect1_5;
	private TextOverlayEffect effect3;
	private ScreenEffectIterator effects;
	
	public PlatformerActivity (Component c) {
		super(c);
		
		title = "Platformer Activity";
		bgm = new MusicPlayer(SoundResource.THE_GHOST, true);
		bgImage = ImageResource.MAP_1.getImageIcon().getImage();
		engine = new Engine();
		hints = new RenderingHints(null);
		prev = 0;
		effect1 = new JitterEffect(0, 0, Main.SCREEN_HEIGHT, JitterEffect.HORIZONTAL);
		effect2 = new PixelateEffect(1);
		effect1_5 = new ImageOverlayEffect(100, 200, ImageResource.GHOST_1.getImageIcon().getImage(), 0.5);
		effect3 = new TextOverlayEffect(100, 100, "Test",
										FontResource.PACIFITO.getFont(Font.PLAIN, 36), Color.WHITE);
		
		effects = new ScreenEffectIterator(effect1, effect1_5, effect2, effect3);
		
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
		
		graphics.dispose();
		
		//Effect Test
		int time = bgm.getTime();
		int cur = time * 4 % 1875;
		if (cur < prev) {
			effect1.setStrength(30);
			if(effect1.getDirection() == JitterEffect.VERTICAL) {
				effect1.setDirection(JitterEffect.HORIZONTAL);
				effect1.setLength(Main.SCREEN_HEIGHT);
				switch (effect2.getSize()) {
					case 1:
						effect2.setSize(2);
						break;
					case 2:
						effect2.setSize(3);
						break;
					case 3:
						effect2.setSize(4);
						break;
					case 4:
						effect2.setSize(5);
						break;
					case 5:
						effect2.setSize(6);
						break;
					case 6:
						effect2.setSize(7);
						break;
					case 7:
						effect2.setSize(8);
						break;
					case 8:
						effect2.setSize(1);
						break;
				}
			} else {
				effect1.setDirection(JitterEffect.VERTICAL);
				effect1.setLength(Main.SCREEN_WIDTH);
			}
		} else {
			double scale = effect1.getStrength() / 1.1;
			effect1.setStrength(scale);
		}
		effect1.setSeed(time);
		
		prev = cur;
		
		effects.apply(image, hints);
		
		return image;
	}
}
