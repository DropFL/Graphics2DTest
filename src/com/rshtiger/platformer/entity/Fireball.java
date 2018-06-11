package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.SquareToCicleCollider;
import res.ImageResource;

import java.awt.*;

import static java.lang.Math.abs;

public class Fireball extends PlayerInteractive{
    private double speedX, speedY;
    private Player p;
    
    public Fireball (Player p) {
    	this.speedX = this.speedY = 0;
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
    public double getSpeedX() {
        return speedX;
    }
    public double getpeedY() { return speedY; }
}
