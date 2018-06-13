package com.unseed.platformer;

import com.dropfl.effect.ScreenEffect;
import com.dropfl.effect.ScreenEffectIterator;
import com.dropfl.music.MusicPlayer;
import com.unseed.key.Key;
import com.unseed.key.KeyStatus;
import com.unseed.platformer.event.EventManager;

public class Synchronizer {
	
	private Engine engine;
	private MusicPlayer player;
	private ScreenEffectIterator effects;
	private EventManager eventManager;
	private int ticks;
	
	public Synchronizer (Engine engine, MusicPlayer player, ScreenEffectIterator effects) {
		this.engine = engine;
		this.player = player;
		this.effects = effects;
		this.eventManager = new EventManager(engine, effects);
		ticks = 0;
	}
	
	public void update () {
		while(ticks * 50 < player.getTime() * 3) {
			ticks ++;
			
			eventManager.update(ticks);
			engine.tick();
			ScreenEffect.setSeed(ticks);
		}
		
		
		if(KeyStatus.isKeyJustPressed(Key.DOWN)) {
			KeyStatus.setKeyProcessed(Key.DOWN);
			System.out.println(ticks);
		}
	}
	
	public int getTicks () {
		return ticks;
	}
	public Engine getEngine () {
		return engine;
	}
	public ScreenEffectIterator getEffects () {
		return effects;
	}
	
}

