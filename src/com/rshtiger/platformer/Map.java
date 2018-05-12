package com.rshtiger.platformer;

import java.util.ArrayList;

public class Map {
    ArrayList<IPlayerInteractive> BlList= new ArrayList<IPlayerInteractive>();

    Map(){}

    public void AddBlock(Block newB) { BlList.add(newB); }
    public ArrayList<IPlayerInteractive> getBlocks() { return BlList; }
}
