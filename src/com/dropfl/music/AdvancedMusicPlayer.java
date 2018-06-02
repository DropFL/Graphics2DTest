package com.dropfl.music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import res.SoundResource;

public class AdvancedMusicPlayer extends MusicPlayer{
	
	private int pausedFrame;
	private boolean isPaused;

	private AdvancedPlayer player;
	private AudioDevice device;
	private PlaybackListener listener;

	private Runnable onResume;
	
	public AdvancedMusicPlayer (SoundResource resource, boolean isLoop) {
		super(resource, isLoop);
		
		this.pausedFrame = 0;
		this.isPaused = false;
		this.device = null;
		this.player = null;
		
		this.listener = new PlaybackListener() {
			@Override
			public void playbackFinished (PlaybackEvent playbackEvent) {
				int offset = (int)(pausedFrame * resource.getMsPerFrame());
				pausedFrame = (int)((device.getPosition() + offset) / resource.getMsPerFrame());
				super.playbackFinished(playbackEvent);
			}
		};
		
		this.onPlay = ()->{
			try {
				do {
					player = getNewPlayer();
				} while (player.play(Integer.MAX_VALUE) && this.isLoop);
			} catch (JavaLayerException e) {
				// do nothing
			}
		};
		this.onResume = ()->{
			try {
				do {
					player = getNewPlayer();
				} while (player.play(pausedFrame, Integer.MAX_VALUE) && this.isLoop);
			} catch (JavaLayerException e) {
				// do nothing
			}
		};
	}
	
	public AdvancedMusicPlayer (SoundResource resource) {
		this(resource, false);
	}
	
	@Override
	public int getTime () {
		int offset = (int)(pausedFrame * resource.getMsPerFrame());
		if(device != null) return device.getPosition() + offset;
		else return offset;
	}
	
	@Override
	public void play () {
		if(!isPaused && thread != null && thread.isAlive())
			throw new IllegalStateException("player was playing, but tried to pause");
		
		isPaused = false;
		thread = new Thread(onPlay);
		
		thread.start();
	}
	
	@Override
	public void stop () {
		if(isPaused || thread == null || !thread.isAlive())
			throw new IllegalStateException("player was not playing, but tried to stop");
		
		player.stop();
		isPaused = false;
	}
	
	public void pause () {
		if(isPaused || thread == null || !thread.isAlive() || thread.isInterrupted())
			throw new IllegalStateException("player was not playing, but tried to pause");
		
		player.stop();
		isPaused = true;
	}
	
	public void resume () {
		if(!isPaused) throw new IllegalStateException("player was not paused, but tried to resume");
		
		isPaused = false;
		thread = new Thread(onResume);
		thread.start();
	}
	
	private AdvancedPlayer getNewPlayer () {
		try {
			this.device = FactoryRegistry.systemRegistry().createAudioDevice();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		AdvancedPlayer player = resource.getAdvancedPlayer(device);
		player.setPlayBackListener(listener);
		return player;
	}
}