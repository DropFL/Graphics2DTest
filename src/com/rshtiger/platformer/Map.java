package com.rshtiger.platformer;

import java.util.ArrayList;

public class Map {
    ArrayList<Block> BlList= new ArrayList<Block>();

    Map(){}

    public void AddBlock(Block newB) {
        BlList.add(newB);
    }
}
