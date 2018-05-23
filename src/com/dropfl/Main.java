package com.dropfl;

import com.dropfl.effect.ScreenEffect;
import com.rshtiger.key.KeyStatus;
import res.FontResource;

public class Main {
	
	// global constants goes here
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	
	public static void main (String[] args) {
		
		// Hardware Acceleration
		System.setProperty("sun.java2d.opengl", "true");
		
		// Initialize
		FontResource.registerFonts();
		KeyStatus.init();
		
		// Create a JFrame
		GameFrame frame = new GameFrame();
		
		// Register JFrame object
		KeyStatus.register(frame);
	}
}
