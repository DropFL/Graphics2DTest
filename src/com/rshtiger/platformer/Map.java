package com.rshtiger.platformer;

import java.util.ArrayList;

public class Map {
	private ArrayList<IPlayerInteractive> blocks;
	
	public Map () {
		blocks = new ArrayList<>();
	}
	
	public void AddBlock (Block newBlock) {
		blocks.add(newBlock);
	}
	
	public ArrayList<IPlayerInteractive> getBlocks () {
		return blocks;
	}
}
