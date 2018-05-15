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

    Bullet(int type, int width, int height, float opacity){
        this.width = width;
        this.height = height;
        this.opacity = opacity;
        if(type == 1) {
            enabled = true;
            image = ImageResource.UNIT_IMAGE.getImageIcon().getImage().getScaledInstance(width, height, Image.SCALE_FAST);
        }
    }

    @Override
    public void interact(Player p) {
        enabled = false;
        p.addHp(-10);
    }

    @Override
    public void render(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.drawImage(image, x, y, null);
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
