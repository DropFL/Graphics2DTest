package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.SquareToCicleCollider;
import res.ImageResource;

import java.awt.*;

import static java.lang.Math.abs;

public class Fireball extends PlayerInteractive{
    private int speedX, speedY;
    Player p;
    public Fireball (Player p) {
        this.width = 50;
        this.height = 50;
        this.collider = new SquareToCicleCollider();
        this.image = ImageResource.FIREBALL.getImageIcon().getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        this.p = p;
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }

    @Override
    public void update() {
        //not implemented
    }
    public int getSpeedX() {
        return speedX;
    }
    public int getpeedY() { return speedY; }
}
