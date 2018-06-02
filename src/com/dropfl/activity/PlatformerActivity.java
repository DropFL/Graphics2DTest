package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.*;
import com.dropfl.music.AdvancedMusicPlayer;
import com.rshtiger.platformer.Engine;
import com.rshtiger.platformer.Synchronizer;
import res.FontResource;
import res.ImageResource;
import res.SoundResource;

import java.awt.*;
import java.awt.image.VolatileImage;

public class PlatformerActivity extends Activity {
	
	private Image bgImage;
	private Engine engine;
	private Synchronizer sync;
	private int prev;
	private JitterEffect jitter;
	private PixelateEffect pixelate;
	private ImageOverlayEffect imgOverlay;
	private TextOverlayEffect txtOverlay;
	private ScreenEffectIterator effects;
	private AdvancedMusicPlayer bgm;
	
	public PlatformerActivity () {
		
		title = "Platformer Activity";
		bgm = new AdvancedMusicPlayer(SoundResource.THE_FLOOR_IS_LAVA, true);
		bgImage = ImageResource.MAP_1.getImageIcon().getImage();
		engine = new Engine();
		sync = new Synchronizer(engine, bgm);
		hints = new RenderingHints(null);
		prev = 0;
		jitter = new JitterEffect(0, 0, Main.SCREEN_HEIGHT, JitterEffect.HORIZONTAL);
		pixelate = new PixelateEffect(1);
		imgOverlay = new ImageOverlayEffect(100, 200, ImageResource.GHOST_1.getImageIcon().getImage(), 0.5);
		txtOverlay = new TextOverlayEffect(100, 100, "Test",
										FontResource.PACIFITO.getFont(Font.PLAIN, 36), Color.WHITE);
		
		effects = new ScreenEffectIterator(imgOverlay, txtOverlay);
		
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	}
	
	@Override
	public void start () {
		bgm.play();
		if(isPaused()) bgm.pause();
		createImage();
	}
	
	@Override
	public void close () {
		bgm.stop();
	}
	
	@Override
	protected void onPause () {
		bgm.pause();
	}
	
	@Override
	protected void onResume () {
		bgm.resume();
	}
	
	@Override
	public VolatileImage getScreen () {
		updateImage();
		
		graphics.setRenderingHints(hints);
		
		// Pre-renderer goes here
		graphics.drawImage(bgImage, 0, 0, null);
		
		// Engine renders here
		
		sync.update();
//		engine.setScale(Math.sin(sync.getTicks() * Math.PI / 60) * 0.4 + 0.6);
		engine.render(graphics);
		
		// Post-renderer goes here
		
		graphics.dispose();
		
		//Effect Test
		int time = bgm.getTime();
		int cur = time * 4 % 1875;
		if (cur < prev) {
			jitter.setStrength(30);
			if(jitter.getDirection() == JitterEffect.VERTICAL) {
				jitter.setDirection(JitterEffect.HORIZONTAL);
				jitter.setLength(Main.SCREEN_HEIGHT);
				switch (pixelate.getSize()) {
					case 1:
						pixelate.setSize(2);
						break;
					case 2:
						pixelate.setSize(3);
						break;
					case 3:
						pixelate.setSize(4);
						break;
					case 4:
						pixelate.setSize(5);
						break;
					case 5:
						pixelate.setSize(6);
						break;
					case 6:
						pixelate.setSize(7);
						break;
					case 7:
						pixelate.setSize(8);
						break;
					case 8:
						pixelate.setSize(1);
						break;
				}
			} else {
				jitter.setDirection(JitterEffect.VERTICAL);
				jitter.setLength(Main.SCREEN_WIDTH);
			}
		} else {
			double scale = jitter.getStrength() / 1.1;
			jitter.setStrength(scale);
		}
		
		prev = cur;
		
		effects.apply(image, hints);
		
		return image;
	}
}
