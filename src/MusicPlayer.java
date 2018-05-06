import javazoom.jl.player.Player;
import res.SoundResource;

public class MusicPlayer extends Thread {

	private Player player;
	private boolean isLoop;
	private final SoundResource resource;
	
	public MusicPlayer (SoundResource resource, boolean isLoop) {
		this.resource = resource;
		this.isLoop = isLoop;
		this.player = null;
	}
	
	public MusicPlayer (SoundResource resource) {
		this(resource, false);
	}
	
	public int getTime () {
		if(player == null) return 0;
		else return player.getPosition();
	}
	
	public void finish () {
		if(isAlive()) {
			isLoop = false;
			player.close();
			this.interrupt();
		}
	}
	
	public void run () {
		try {
			do {
				player = resource.getPlayer();
				player.play();
			} while (isLoop);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
