package com.rshtiger.platformer;

import java.awt.*;

public class Entity {
    private int x, y, width, height;


    public void setPosition(int p[]){
        x = p[0];
        y= p[1];
        width = p[2];
        height = p[3];
    }
    //not for diagonal
    public int isTouched(Player p){
        //Touched Top
        if(p.getPositionY() + p.getSqrSize() == y && (p.getPositionX() + p.getSqrSize() > x || p.getPositionX() < x+width))
            return 1;
        //Touched Bottom
        if(p.getPositionY() == y + height && (p.getPositionX() + p.getSqrSize() > x || p.getPositionX() < x+width))
            return 2;
        //Touched Left
        if(p.getPositionX() + p.getSqrSize()== x && (p.getPositionY() + p.getSqrSize() > y || p.getPositionY() < y + height ))
            return 3;
        //Touched Right
        if(p.getPositionX() <= x+width && (p.getPositionY() + p.getSqrSize() > y || p.getPositionY() < y + height ))
            return 4;
        return 0;
    }
}
