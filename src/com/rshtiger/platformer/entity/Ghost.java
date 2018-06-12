package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.SquareToCicleCollider;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ghost extends PlayerInteractive{
    
    private double speedX, speedY;
    private Image wing;
    private Player p;
    
    public Ghost (Player p) {
        this.width = 50;
        this.height = 50;
        this.collider = new SquareToCicleCollider();
        this.wing = ImageResource.GHOST_2.getImageIcon().getImage().getScaledInstance(100, 50,Image.SCALE_SMOOTH);
        this.image = ImageResource.GHOSTTMP.getImageIcon().getImage().getScaledInstance(45,45,Image.SCALE_SMOOTH);
        this.p = p;
    }

    @Override
    public boolean interact (Player p) {
        p.addHp(-10);
        return true;
    }

    @Override
    public void update(){
        int diff;
        diff = (int)p.getLeftX() - (int)getLeftX();
        speedX = 10. / ((diff*diff + 1) * (diff > 0 ? 1 : -1)) ;
        diff = (int)p.getTopY() - (int)getTopY();
        speedY = 10. / ((diff*diff + 1) * (diff > 0 ? 1 : -1)) ;
        this.x += speedX;
        this.y += speedY;

    }
    public double getSpeedX() {
        return speedX;
    }
    public double getpeedY() {
        return speedY;
    }

    @Override
    public void render (Graphics2D g) {
        super.render(g);

            AffineTransform t = new AffineTransform();
            t.translate(x - 55, y - 10);
            g.drawImage(wing, t, null);
    }

}
