package com.dropfl.activity;

import com.dropfl.Main;
import com.dropfl.effect.ScaleEffect;
import com.dropfl.effect.TextOverlayEffect;
import com.dropfl.music.DefaultMusicPlayer;
import res.FontResource;
import res.ImageResource;
import res.SoundResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.VolatileImage;

public final class MainActivity extends Activity{
	
	private Image bgImage;
	private int deg;
	private float scale;
	private boolean isFullScreen;
	private DefaultMusicPlayer bgm;
	private final JButton start_btn;
	private final JButton option_btn;
	private final JButton exit_btn;
	private final JButton maxframe_btn;
	private final MouseAdapter start_adapter;
	private final MouseAdapter maximize_adapter;
	public MainActivity () {
		title = "Main Activity";
		bgm = new DefaultMusicPlayer(SoundResource.THE_FLOOR_IS_LAVA, true);
		bgImage = ImageResource.START_BACKGROUND.getImageIcon().getImage();
		scale = 1;
		start_btn = new JButton(ImageResource.BLANK_BUTTON.getImageIcon(226, 68));
		option_btn = new JButton(ImageResource.OPTION_BUTTON.getImageIcon(226, 68));
		exit_btn = new JButton(ImageResource.EXIT_BUTTON.getImageIcon(226, 68));
		maxframe_btn = new JButton(ImageResource.MAXIMIZE_BUTTON.getImageIcon(30, 30));
		start_adapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				requestActivityChange(PlatformerActivity.class);
			}
		};
		maximize_adapter = new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {

			}
		};
		initUI();
		
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
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
		
		graphics.setColor(new Color(0, 0, 0,128));
		graphics.fillArc(Main.SCREEN_WIDTH / 2 + 100, Main.SCREEN_HEIGHT / 2 - 50, 100, 100,
				90, deg);
		graphics.fillArc(Main.SCREEN_WIDTH / 2 - 200, Main.SCREEN_HEIGHT / 2 - 50, 100, 100,
				90, time * 360 / bgm.getLength());
		graphics.dispose();
		
		TextOverlayEffect txt = new TextOverlayEffect(
				Main.SCREEN_WIDTH / 2 - 200,
				Main.SCREEN_HEIGHT / 2 - 60,
				"Progress",
				FontResource.PACIFITO.getFont(Font.PLAIN, 24),
				Color.BLACK
		);
		
		txt.apply(image, hints);
		txt.setText("BPM");
		txt.setX(Main.SCREEN_WIDTH / 2 + 115);
		txt.apply(image, hints);
		
		new ScaleEffect(scale, scale).apply(image, hints);

		scale = 1 + (scale - 1) / 1.1f;
		
		return image;
	}
	
	@Override
	public void start () {
		bgm.play();
		createImage();
	}
	
	@Override
	public void close () {
		start_btn.removeMouseListener(start_adapter);
		
		try {
			bgm.stop();
		} catch (IllegalStateException e) {
			// do nothing
		}
	}

	private void initBtn(JButton btn, int x, int y, int width, int height, MouseAdapter adptr){
		btn.setBounds(x, y, width, height);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.addMouseListener(adptr);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		components.add(btn);
	}

	private void initUI () {
		initBtn(start_btn, Main.SCREEN_WIDTH / 2 + 275, Main.SCREEN_HEIGHT / 2 - 65, 226, 68, start_adapter);

		initBtn(maxframe_btn, Main.SCREEN_WIDTH  - 40, Main.SCREEN_HEIGHT - 40, 30, 30, maximize_adapter);
	}
}
