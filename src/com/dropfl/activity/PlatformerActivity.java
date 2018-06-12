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

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	private ImageOverlayEffect pauseEffect;
	
	private JButton resume;
	private JButton restart;
	private JButton stop;
	private MouseAdapter resumeAdapter;
	private MouseAdapter restartAdapter;
	private MouseAdapter stopAdapter;
	
	private boolean paused;
	
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
		
		pauseEffect = new ImageOverlayEffect(0, 0, ImageResource.PAUSE_OVERLAY.getImageIcon().getImage(), 0);
		
		resume = new JButton(ImageResource.RESUME_BUTTON.getImageIcon(160, 160));
		restart = new JButton(ImageResource.RESTART_BUTTON.getImageIcon(160, 160));
		stop = new JButton(ImageResource.STOP_BUTTON.getImageIcon(160, 160));
		
		resumeAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				resumeGame();
			}
		};
		restartAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				requestActivityChange(PlatformerActivity.class);
			}
		};
		stopAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				requestActivityChange(MainActivity.class);
			}
		};
		
		paused = false;
	}
	
	@Override
	public void start () {
		bgm.play(()->requestActivityChange(MainActivity.class));
		createImage();
		
		resume.setVisible(false);
		restart.setVisible(false);
		stop.setVisible(false);
		
		addButton(resume, Main.SCREEN_WIDTH / 2 - 80,Main.SCREEN_HEIGHT / 2 - 80, 160, 160, resumeAdapter);
		addButton(restart, Main.SCREEN_WIDTH / 2 - 320,Main.SCREEN_HEIGHT / 2 - 80, 160, 160, restartAdapter);
		addButton(stop, Main.SCREEN_WIDTH / 2 + 160,Main.SCREEN_HEIGHT / 2 - 80, 160, 160, stopAdapter);
	}
	
	@Override
	public void close () {
		bgm.stop();
	}
	
	@Override
	protected void onPause () {
		if (!paused) pauseGame();
	}
	
	@Override
	protected void onResume () {
	}
	
	@Override
	public VolatileImage getScreen () {
		
		if(KeyStatus.isKeyJustPressed(Key.ESCAPE)) {
			KeyStatus.setKeyProcessed(Key.ESCAPE);
			if(paused) resumeGame();
			else pauseGame();
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
		
		pauseEffect.apply(image);
		
		return image;
	}
	
	private void pauseGame () {
		bgm.pause();
		paused = true;
		
		resume.setVisible(true);
		restart.setVisible(true);
		stop.setVisible(true);
		
		pauseEffect.setOpacity(1);
	}
	
	private void resumeGame () {
		bgm.resume();
		paused = false;
		
		resume.setVisible(false);
		restart.setVisible(false);
		stop.setVisible(false);
		
		pauseEffect.setOpacity(0);
	}
}
