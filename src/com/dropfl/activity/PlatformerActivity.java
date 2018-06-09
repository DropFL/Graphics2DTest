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
	private int beats;
	private TextOverlayEffect read;
	private TextOverlayEffect the;
	private TextOverlayEffect fucking;
	private TextOverlayEffect manual;
	private JitterEffect shake;
	private ScreenEffectIterator effects;
	private AdvancedMusicPlayer bgm;
	
	public PlatformerActivity () {
		
		title = "Platformer Activity";
		bgm = new AdvancedMusicPlayer(SoundResource.THE_GHOST);
		bgImage = ImageResource.MAP_1.getImageIcon().getImage();
		engine = new Engine();
		sync = new Synchronizer(engine, bgm);
		hints = new RenderingHints(null);
		prev = beats = 0;
		read = new TextOverlayEffect(404, 300, "",
										FontResource.BLACK_HAN_SANS.getFont(Font.PLAIN, 84), Color.BLACK);
		the = new TextOverlayEffect(687, 300, "",
										FontResource.BLACK_HAN_SANS.getFont(Font.PLAIN, 84), Color.BLACK);
		fucking = new TextOverlayEffect(368, 400, "",
										FontResource.BLACK_HAN_SANS.getFont(Font.PLAIN, 120), Color.RED);
		manual = new TextOverlayEffect(447, 480, "",
										FontResource.BLACK_HAN_SANS.getFont(Font.PLAIN, 84), Color.WHITE);
		shake = new JitterEffect(0, 0, Main.SCREEN_HEIGHT, JitterEffect.HORIZONTAL);
		effects = new ScreenEffectIterator(shake);
		
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	}

	@Override
	public void start () {
		bgm.play(()->requestActivityChange(MainActivity.class));
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
		engine.render(graphics);

		// Post-renderer goes here

		graphics.dispose();

		//Effect Test
		int time = bgm.getTime();
		int cur = time * 4 % 1875;
		if (cur < prev) {
			beats = (beats + 1) % 8;
			switch (beats) {
				case 0:
					read.setText("");
					the.setText("");
					fucking.setText("");
					manual.setText("");
					break;
				case 1:
					read.setText("READ");
					break;
				case 2:
					the.setText("THE");
					break;
				case 3:
					fucking.setText("FUCKING");
					break;
				case 4:
					manual.setText("MANUAL");
					break;
				case 5:
					fucking.setText("");
					manual.setText("");
					break;
				case 6:
					manual.setText("MANUAL");
					break;
				case 7:
					fucking.setText("FUCKING");
					break;
			}
			shake.setStrength(150);
		} else {
			double strength = shake.getStrength() / 1.5;
			shake.setStrength(strength);
		}
		
		prev = cur;
		ScreenEffect.setSeed(sync.getTicks());
		
		effects.apply(image, hints);
		
		return image;
	}
}
