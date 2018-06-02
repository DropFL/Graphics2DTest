package com.dropfl.music;

import res.SoundResource;

public abstract class MusicPlayer {
	
	protected boolean isLoop;
	protected final SoundResource resource;
	protected Thread thread;
	protected Runnable onPlay;
	
	public MusicPlayer (SoundResource resource, boolean isLoop) {
		this.resource = resource;
		this.isLoop = isLoop;
	}
	
	public abstract void play();
	public abstract void stop();
	
	public abstract int getTime();
	
	public int getLength () {
		return resource.getLength();
	}
	public boolean getLoop () {
		return isLoop;
	}
	public SoundResource getResource () {
		return resource;
	}
	
	public void setLoop (boolean isLoop) {
		this.isLoop = isLoop;
	}
}
