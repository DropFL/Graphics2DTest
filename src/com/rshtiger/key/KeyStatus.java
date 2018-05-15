package com.rshtiger.key;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public final class KeyStatus {
	
	private static boolean isInitialized = false;
	private static HashMap<Key, Integer> status;
	
	private KeyStatus () {
		// none
	}
	
	public static void init (JFrame frame) {
		
		if (isInitialized) throw new IllegalStateException("KeyStatus already initialized");
		
		isInitialized = true;
		status = new HashMap<>();
		for (Key k : Key.values())
			status.put(k, 0);
		
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped (KeyEvent e) {
				//
			}
			
			@Override
			public void keyPressed (KeyEvent e) {
				Key k = Key.getKey(e.getKeyCode());
				
				if(k != null) status.put(k, 2);
			}
			
			@Override
			public void keyReleased (KeyEvent e) {
				Key k = Key.getKey(e.getKeyCode());
				
				if(k != null) status.put(k, 0);
			}
		});
	}
	
	public static boolean isKeyPressed (Key key) {
		return status.get(key) > 0;
	}
	public static boolean isKeyJustPressed (Key key) {
		return status.get(key) == 2;
	}
	
	public static void setKeyProcessed (Key key) {
		if(status.get(key) == 0)
			System.out.println("WTF? " + key + "is not even pressed yet...");
		else status.put(key, 1);
	}
}