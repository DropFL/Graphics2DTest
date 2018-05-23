package com.dropfl;

import com.dropfl.activity.*;
import res.FontResource;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

public class GameFrame extends JFrame /*implements ScreenEffect*/{
	
	private static final long serialVersionUID = -711163588504124217L;
	
	private Activity activity;
	
	public GameFrame () {
		setTitle("JFrame Test");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		activity = new PlatformerActivity(this);
		setTitle(activity.getTitle());
		activity.start();
		
		setVisible(true);    // JFrame.paint is called after here.
		// so it must be called AFTER all members are initialized.
	}
	
	@Override
	public void paint (Graphics g) {
		VolatileImage img;
		do {
			img = activity.getScreen();
			g.drawImage(img, 0, 0, null);
		} while (img.contentsLost());
		this.repaint();
	}

//	public void changeActivity (activity related parameter(s)) {
//
//	}

//	public void applyEffect (ScreenEffect effect) {
//
//
//	}
}