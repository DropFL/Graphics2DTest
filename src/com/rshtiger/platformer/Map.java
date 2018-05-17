package com.rshtiger.platformer;

import java.util.ArrayList;

public class Map {
	private ArrayList<PlayerInteractive> blocks;
	
	public Map () {
		blocks = new ArrayList<>();
	}
	
	public void addBlock (Block newBlock) {
		blocks.add(newBlock);
	}
	
	public ArrayList<PlayerInteractive> getBlocks () {
		return blocks;
	}
}
