package com.unseed.platformer;

import com.unseed.platformer.entity.Block;

import java.util.ArrayList;

public class Map {
	private ArrayList<Block> blocks;
	
	public Map () {
		blocks = new ArrayList<>();
	}
	
	public void addBlock (Block newBlock) {
		blocks.add(newBlock);
	}
	
	public ArrayList<Block> getBlocks () {
		return blocks;
	}
}
