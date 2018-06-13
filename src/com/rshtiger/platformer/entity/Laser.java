package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.OBBCollider;
import res.ImageResource;

import java.awt.*;

public class Laser extends PlayerInteractive{
    private int damage = 10;
    public Laser(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collider = new OBBCollider();
        this.image = ImageResource.LASER_R.getImageIcon().getImage().getScaledInstance(width * 5, height, Image.SCALE_FAST);
    }

    @Override
    public boolean interact(Player player) {

        player.addHp(-1 * damage);
        damage = 0;
        return false;
    }
}
