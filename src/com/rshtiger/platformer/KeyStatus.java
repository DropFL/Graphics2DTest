package com.rshtiger.platformer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyStatus {
	
	private static boolean isInitialized = false;
	
	private static boolean keyUp = false;
	private static boolean keyDown = false;
	private static boolean keyLeft = false;
	private static boolean keyRight = false;
	private static boolean keySpace = false;
	
	// can be simplified with HashMap?
	
	public static void init (JFrame frame) {
		
		if (isInitialized) throw new IllegalStateException("KeyStatus already initialized");
		
		isInitialized = true;
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped (KeyEvent e) {
				//
			}
			
			@Override
			public void keyPressed (KeyEvent e) {
				updateStatus(e.getKeyCode(), true);
			}
			
			@Override
			public void keyReleased (KeyEvent e) {
				updateStatus(e.getKeyCode(), false);
			}
		});
	}
	
	private KeyStatus () {
	
	}
	
	public static boolean isKeyUp () {
		return keyUp;
	}
	public static boolean isKeyDown () {
		return keyDown;
	}
	public static boolean isKeyLeft () {
		return keyLeft;
	}
	public static boolean isKeyRight () {
		return keyRight;
	}
	public static boolean isKeySpace () {
		return keySpace;
	}
	
	private static void updateStatus (int keyCode, boolean isPressed) {
		
		switch (keyCode) {
			case KeyEvent.VK_UP:
				keyUp = isPressed;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = isPressed;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = isPressed;
				break;
			case KeyEvent.VK_RIGHT:
				keyRight = isPressed;
				break;
			case KeyEvent.VK_SPACE:
				keySpace = isPressed;
				break;
		}
	}
}
