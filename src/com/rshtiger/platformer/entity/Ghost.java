package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.AABBCollider;

import java.awt.*;

public class Ghost extends PlayerInteractive{
    private int speedX, speedY;

    public Ghost (int width, int height, Image img) {
        this.width = width;
        this.height = height;
        this.collider = new AABBCollider();
        this.image = img;
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }


    public int makeSpeedX(Player p) {
        speedX = 10 / ((int)p.getLeftX() - (int)getLeftX());
        return speedX;
    }
    public int makepeedY(Player p) {
        speedY = 10 / ((int)p.getTopY() - (int)getTopY());
        return speedY;
    }
}
