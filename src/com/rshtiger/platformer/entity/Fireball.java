package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.SquareToCicleCollider;
import res.ImageResource;

import java.awt.*;

import static java.lang.Math.abs;

public class Fireball extends PlayerInteractive{

    public Fireball (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.rotation = 0;
        this.width = width;
        this.height = height;
        this.collider = new SquareToCicleCollider();
        this.image = ImageResource.FIREBALL.getImageIcon().getImage().getScaledInstance(width, height, Image.SCALE_FAST);
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }
}
