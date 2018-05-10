package com.rshtiger.platformer;

import java.awt.*;

public class Entity {
    //(Top, Left) (Top, Right) (Bottom, Left) (Bottom, Right)
    private int Top, Bottom, Left, Right;


    public void setPosition(int p[]){
        Top = p[0];
        Bottom = p[1];
        Left = p[2];
        Right = p[3];
    }
    //not for diagonal
    public int isTouched(Player p){
        //Touched Top
        if(p.getPositionY() + p.getSqrSize() == Top && (p.getPositionX() + p.getSqrSize() > Left || p.getPositionX() < Right))
            return 1;
        //Touched Bottom
        if(p.getPositionY() == Bottom && (p.getPositionX() + p.getSqrSize() > Left || p.getPositionX() < Right))
            return 2;
        //Touched Left
        if(p.getPositionX() + p.getSqrSize()== Left&& (p.getPositionY() + p.getSqrSize() > Top || p.getPositionY() < Bottom ))
            return 3;
        //Touched Right
        if(p.getPositionX() <= Right && (p.getPositionY() + p.getSqrSize() > Top || p.getPositionY() < Bottom ))
            return 4;
        return 0;
    }
}
