package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.OBBCollider;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Laser extends PlayerInteractive{
    private boolean used = true;
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
        if(used) {
            System.out.println("Laser!!!!!!!");
            player.addHp(-10);
            used = false;
        }
        return false;
    }

    @Override
    public void render(Graphics2D g){
        AffineTransform t = new AffineTransform();
        t.translate(x - 2*width , y);
        t.rotate(Math.toRadians(rotation));
        g.drawImage(image, t, null);
    }
}
