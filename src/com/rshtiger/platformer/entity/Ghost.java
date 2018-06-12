package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.SquareToCicleCollider;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ghost extends PlayerInteractive{
	
    private Image wing;
    
    public Ghost (Player p) {
        this.width = 50;
        this.height = 50;
        this.collider = new SquareToCicleCollider();
        this.wing = ImageResource.GHOST_2.getImageIcon().getImage().getScaledInstance(100, 50,Image.SCALE_FAST);
        this.image = ImageResource.GHOSTTMP.getImageIcon().getImage().getScaledInstance(45,45,Image.SCALE_SMOOTH);
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }

    @Override
    public void render (Graphics2D g) {
        super.render(g);

            AffineTransform t = new AffineTransform();
            t.translate(x - 55, y - 10);
            g.drawImage(wing, t, null);
    }

}
