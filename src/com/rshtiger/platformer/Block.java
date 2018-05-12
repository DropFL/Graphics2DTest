package com.rshtiger.platformer;

public class Block implements IPlayerInteractive{
        int x, y, width, height;
        public Block(int x, int y, int width, int height){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

    @Override
    public void interact(Player p) {
        //Touched Top
        if(p.getBottomY() >= y)
            p.SetUnitJump(false);
        //Touched Bottom
        if(p.getTopY() <= y + height)
            p.setJmpTime(0);
        //Touched Left
        if(p.getRightX() >= x)
            p.movPositionX(-1*p.getSpeedX());
        //Touched Right
        if(p.getLeftX() <= x+width)
            p.movPositionX(p.getSpeedX());

    }

    @Override
    public boolean isTouched(Player p) {
            if(((p.getLeftX() > x && p.getLeftX() < x + width) || (p.getRightX() > x && p.getRightX() < x + width)) && (p.getTopY() > y && p.getBottomY() < y + width) || p.getBottomY() > y && p.getBottomY() < y + width)
                return true;
            return false;
    }
}
