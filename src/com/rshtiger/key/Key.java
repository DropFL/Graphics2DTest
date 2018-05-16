package com.rshtiger.key;

import java.awt.event.KeyEvent;

public enum Key {
	LEFT, RIGHT, UP, DOWN, SPACE, S;
	
	public static Key getKey (int keyCode) {
		switch (keyCode) {
			case KeyEvent.VK_LEFT:
				return LEFT;
			case KeyEvent.VK_RIGHT:
				return RIGHT;
			case KeyEvent.VK_UP:
				return UP;
			case KeyEvent.VK_DOWN:
				return DOWN;
			case KeyEvent.VK_SPACE:
				return SPACE;
			case KeyEvent.VK_S:
				return S;
			default:
				return null;
		}
	}
}