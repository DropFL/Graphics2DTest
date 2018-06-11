package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.*;
import com.dropfl.music.AdvancedMusicPlayer;
import com.rshtiger.key.Key;
import com.rshtiger.key.KeyStatus;
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
		effects = new ScreenEffectIterator(read, the, fucking, manual, shake);
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
		if(KeyStatus.isKeyJustPressed(Key.ESCAPE)) {
			KeyStatus.setKeyProcessed(Key.ESCAPE);
			requestActivityChange(MainActivity.class);
		}
		
		updateImage();
		
		graphics.setRenderingHints(Main.getRenderingHint());
		
		// Pre-renderer goes here
		graphics.drawImage(bgImage, 0, 0, null);
		
		// Engine renders here
		sync.update();
		engine.render(graphics);
		
		// Post-renderer goes here
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, Main.SCREEN_WIDTH * bgm.getTime() / bgm.getLength(), 3);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 3, Main.SCREEN_WIDTH * bgm.getTime() / bgm.getLength(), 1);
		
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
					fucking.setText("FUCKING");
					break;
				case 7:
					manual.setText("MANUAL");
					break;
			}
			shake.setStrength(10);
		} else if (!isPaused()) {
			double strength = shake.getStrength() / 1.1;
			shake.setStrength(strength);
		}
		
		prev = cur;
		ScreenEffect.setSeed(sync.getTicks());
		
		effects.apply(image);
		
		return image;
	}
}
