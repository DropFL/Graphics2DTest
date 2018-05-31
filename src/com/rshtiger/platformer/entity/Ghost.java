package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.AABBCollider;

import java.awt.*;

import static java.lang.Math.abs;

public class Ghost extends PlayerInteractive{
    private int speedX, speedY;
    Player p;
    public Ghost (int width, int height, Image img, Player p) {
        this.width = width;
        this.height = height;
        this.collider = new AABBCollider();
        this.image = img;
        this.p = p;
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }

    @Override
    public void active(){
        int tmpx;
        tmpx = (int)p.getLeftX() - (int)getLeftX();
        speedX = 10 / ((tmpx*tmpx + 1) * tmpx / abs(tmpx)) ;
        tmpx = (int)p.getTopY() - (int)getTopY();
        speedY = 10 / ((tmpx*tmpx + 1) * tmpx / abs(tmpx)) ;
        this.x += speedX;
        this.y += speedY;

    }
    public int getSpeedX() {
        return speedX;
    }
    public int getpeedY() {
        return speedY;
    }
}
