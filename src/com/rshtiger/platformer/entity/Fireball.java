package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.SquareToCicleCollider;
import res.ImageResource;

import java.awt.*;

import static java.lang.Math.abs;

public class Fireball extends PlayerInteractive{
    private double speedX, speedY;

    public Fireball (int x, int y, int width, int height) {
        this.speedX = this.speedY = 0;
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.width = 100;
        this.height = 100;
        this.collider = new SquareToCicleCollider();
        this.image = ImageResource.FIREBALL.getImageIcon().getImage().getScaledInstance(100,100, Image.SCALE_FAST);
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
