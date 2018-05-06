import res.ImageResource;
import res.SoundResource;

import javax.swing.*;
import java.awt.*;

public class JFrameTest extends JFrame {
	
	private static final long serialVersionUID = -711163588504124217L;
	private Image bgImage;
	private Image screenImage;
	private Graphics screenGraphics;
	
	public JFrameTest () {
		setTitle("JFrame Test");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		bgImage = ImageResource.DROP_IMAGE.getImageIcon().getImage();
		
		MusicPlayer musicPlayer = new MusicPlayer(SoundResource.THE_GHOST, true);
		musicPlayer.start();
	}
	
	public void paint (Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw (Graphics g) {
		g.drawImage(bgImage, (Main.SCREEN_WIDTH - Main.SCREEN_HEIGHT) / 2, 0,
				Main.SCREEN_HEIGHT, Main.SCREEN_HEIGHT, null);
		this.repaint();
	}
}