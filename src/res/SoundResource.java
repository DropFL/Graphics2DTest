package res;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public enum SoundResource {
	
	THE_GHOST("NIVIRO_The_Ghost.mp3"),
	LOOP_TEST("Loop_Test.mp3");
	
	private File file;
	
	SoundResource (String name) {
		file = new File(getClass().getResource("sounds/" + name).getPath());
	}
	
	public Player getPlayer () {
		try {
			return new Player(new BufferedInputStream(new FileInputStream(file)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
