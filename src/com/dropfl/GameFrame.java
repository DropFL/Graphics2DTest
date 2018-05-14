package com.dropfl;

import com.dropfl.activity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = -711163588504124217L;
	
	private Activity activity;
	
	public GameFrame () {
		setTitle("JFrame Test");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		activity = new PlatformerActivity();
		setTitle(activity.getTitle());
		activity.start();
		
		setVisible(true);	// JFrame.paint is called after here.
							// so it must be called AFTER all members are initialized.
	}
	
	public void paint (Graphics g) {
		BufferedImage screenImage = (BufferedImage) createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		Graphics2D screenGraphics = (Graphics2D) screenImage.getGraphics();
		
		activity.render(screenGraphics);
		
		g.drawImage(screenImage, 0, 0, null);
		this.repaint();
	}
	
//	public void changeActivity (activity related parameter(s)) {
//
//	}
}