package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.AABBCollider;
import com.rshtiger.platformer.collision.SquareToCicleCollider;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.lang.Math.abs;

public class Ghost extends PlayerInteractive{
    private int speedX, speedY;
    private Image wing;
    Player p;
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

    @Override
    public void render (Graphics2D g) {
        super.render(g);

            AffineTransform t = new AffineTransform();
            t.translate(x - 55, y - 10);
            g.drawImage(wing, t, null);
    }
}
