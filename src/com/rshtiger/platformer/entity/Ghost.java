package com.rshtiger.platformer.entity;

import com.rshtiger.platformer.collision.SquareToCircleCollider;
import res.ImageResource;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ghost extends PlayerInteractive{
    
    private double speedX, speedY;
    private Image wing;
    private Player p;
    
    public Ghost (int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collider = new SquareToCircleCollider();
        this.wing = ImageResource.GHOST_2.getImageIcon().getImage().getScaledInstance(3*(width - 5), 3*(height - 5),Image.SCALE_FAST);
        //this.image = ImageResource.GHOSTTMP.getImageIcon().getImage().getScaledInstance(width ,height,Image.SCALE_SMOOTH);
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
            t.translate(x - width , y - height);
            g.drawImage(wing, t, null);
    }

}
