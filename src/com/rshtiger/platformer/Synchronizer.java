package com.rshtiger.platformer;

import com.dropfl.effect.ScreenEffect;
import com.dropfl.music.MusicPlayer;

public class Synchronizer {
	
	private Engine engine;
	private MusicPlayer player;
	private int ticks;
	
	public Synchronizer (Engine engine, MusicPlayer player) {
		this.engine = engine;
		this.player = player;
		ticks = 0;
	}
	
	public void update () {
		while(ticks * 50 < player.getTime() * 3) {
			ticks ++;
			engine.tick();
		}
		
		ScreenEffect.setSeed(ticks);
	}
	
	public int getTicks () {
		return ticks;
	}
}
