package com.dropfl;

import com.rshtiger.platformer.KeyStatus;

public class Main {
	
	// global constants goes here
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	
	
	public static void main (String[] args) {
		
		// Create a JFrame
		GameFrame frame = new GameFrame();
		
		// Link KeyListener to the JFrame object
		KeyStatus.init(frame);
	}
}
