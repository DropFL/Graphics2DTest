package com.rshtiger.platformer;

import res.ImageResource;

import java.awt.*;

public class Bullet implements IPlayerInteractive {
    private int width, height;
    private int x, y;
    private int speedX, speedY;
    private Image image;
    private boolean enabled;
    float opacity;

    Bullet(int width, int height, float opacity, Image img){
        this.width = width;
        this.height = height;
        this.opacity = opacity;
        image = img;
    }

    @Override
    public void interact(Player p) {
        enabled = false;
        p.addHp(-10);
    }

    @Override
    public void render(Graphics2D g) {
        if(isEnabled()) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g.drawImage(image, x, y, null);
        }
    }

    @Override
    public boolean isTouched(Player p) {
        int pivotLeft = (p.getLeftX() > x) ? p.getLeftX() : x,
                pivotRight = (p.getRightX() < x + width) ? p.getRightX() : (x + width),
                pivotTop = (p.getTopY() > y) ? p.getTopY() : y,
                pivotBottom = (p.getBottomY() < y + height) ? p.getBottomY() : (y + height);


        return pivotLeft < pivotRight && pivotTop < pivotBottom;
    }

    public boolean isEnabled() { return enabled; }
}
