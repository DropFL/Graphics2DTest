package res;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

public enum SoundResource implements IResource {
	
	THE_GHOST("NIVIRO_The_Ghost.mp3"),
	LOOP_TEST("Loop_Test.mp3");
	
	private String path;
	
	SoundResource (String name) {
		path = "src/res/sounds/" + name;
	}
	
	@Override
	public File getFile () {
		return new File(path);
	}
	
	@Override
	public URI getURI () {
		return getFile().toURI();
	}
	
	public Player getPlayer () {
		try {
			return new Player(new BufferedInputStream(new FileInputStream(getFile())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
