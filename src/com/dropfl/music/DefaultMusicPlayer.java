package com.dropfl.music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import res.SoundResource;

public class DefaultMusicPlayer extends MusicPlayer {
	
	private Player player;
	
	public DefaultMusicPlayer (SoundResource resource, boolean isLoop) {
		super(resource, isLoop);
		
		this.player = null;
		this.onPlay = ()->{
			try {
				do {
					player = resource.getPlayer();
				} while (player.play(Integer.MAX_VALUE) && this.isLoop);
			} catch (JavaLayerException e) {
				// do nothing
			}
		};
	}
	
	public DefaultMusicPlayer (SoundResource resource) {
		this(resource, false);
	}
	
	@Override
	public int getTime () {
		if(player != null) return player.getPosition();
		else return 0;
	}
	
	@Override
	public void play () {
		if(thread != null && thread.isAlive())
			throw new IllegalStateException("player was playing, but tried to pause");
		
		thread = new Thread(onPlay);
		thread.start();
	}
	
	@Override
	public void stop () {
		if(thread == null || !thread.isAlive())
			throw new IllegalStateException("player was not playing, but tried to stop");
		
		player.close();
	}
}