import music.MusicPlayer;
import res.ImageResource;
import res.SoundResource;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = -711163588504124217L;
	
	private Image bgImage;
	private Image screenImage;
	private Graphics screenGraphics;
	private int offset;
	private int deg;
	private MusicPlayer musicPlayer;
	Unit u = new Unit();

	// there will be "Activity", to modularize each screen.
	// private Activity activity;
	
	public GameFrame () {
		setTitle("JFrame Test");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		bgImage = ImageResource.WIN_IMAGE.getImageIcon().getImage();
		
		musicPlayer = new MusicPlayer(SoundResource.THE_GHOST, true);
		
		offset = deg = 0;
		musicPlayer.start();

		/*
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		activity = new IntroActivity();
		setTitle(activity.TITLE);
		 */
	}
	
	public void paint (Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null);
		g.drawImage(u.unit, u.x, u.y, null);
	}
	
	public void screenDraw (Graphics g) {
		if(musicPlayer == null) deg = 0;
		else deg = (musicPlayer.getTime() + offset) * 4 % 1875 * 360 / 1875;
		
		g.drawImage(bgImage, 0, 0,
				Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT, null);
		g.fillArc(Main.SCREEN_WIDTH / 2, Main.SCREEN_HEIGHT / 2, 100, 100, 0, deg);
		this.repaint();
		
		/*
		activity.render(g);
		this.repaint();
		 */
	}
}