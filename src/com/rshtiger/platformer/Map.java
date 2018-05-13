package com.rshtiger.platformer;

import java.util.ArrayList;

public class Map {
	private ArrayList<IPlayerInteractive> BlList;
	
	public Map () {
		BlList = new ArrayList<IPlayerInteractive>();
	}
	
	public void AddBlock (Block newB) {
		BlList.add(newB);
	}
	
	public ArrayList<IPlayerInteractive> getBlocks () {
		return BlList;
	}
}
