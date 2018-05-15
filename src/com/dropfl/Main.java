package com.dropfl;

import com.rshtiger.key.KeyStatus;

public class Main {
	
	// global constants goes here
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	
	public static void main (String[] args) {
		
		// Initialize KeyStatus
		KeyStatus.init();
		
		// Create a JFrame
		GameFrame frame = new GameFrame();
		
		// Link KeyStatus to the JFrame object
		KeyStatus.register(frame);
	}
}
