package com.rshtiger.platformer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block implements IPlayerInteractive {
	
	private int x, y, width, height;
	private BufferedImage img;
	
	public Block (int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		g.setColor(new Color(92, 64, 51));
		g.fillRect(0, 0, width, height);
	}
	
	@Override
	public void interact (Player p) {
		int spX = p.getSpeedX(), spY = p.getSpeedY();
		
		// If the player is moved only vertically or horizontal area of this block covers the player's one,
		// it must be top-to-bottom collision.
		if ((x < p.getLeftX() && p.getRightX() < x + width) || spX == 0) {
			if (spY > 0) {
				p.setPositionY(y - p.getSize());
				p.setJumped(false);
			} else {
				p.setPositionY(y + height);
				p.setSpeedY(0);
			}
			
			System.out.println("X Range");
			return;
		}
		
		// If the player is moved only horizontally or vertical area of this block covers the player's one,
		// it must be top-to-bottom collision.
		if ((y < p.getTopY() && p.getBottomY() < y + height) || spY == 0) {
			if (spX > 0) p.setPositionX(x - p.getSize());
			else p.setPositionX(x + width);
			
			System.out.println("Y Range");
			return;
		}
		
		int pivotX = (spX > 0) ? (x - p.getRightX() + spX) : (x + width - p.getLeftX() + spX),
			pivotY = (spY > 0) ? (y - p.getBottomY() + spY) : (y + height - p.getTopY() + spY);
		
		if (pivotY * spX < spY * pivotX) {
			// left-to-right collision.
			if (spX > 0) p.setPositionX(x - p.getSize());
			else p.setPositionX(x + width);
		} else {
			// top-to-bottom collision.
			p.setSpeedY(0);
			
			if (spY > 0) {
				p.setPositionY(y - p.getSize());
				p.setJumped(false);
			}
			else p.setPositionY(y + height);
		}
		
	}
	
	@Override
	public boolean isTouched (Player p) {
		if ( ( (p.getLeftX() > x && p.getLeftX() < x + width) ||
			   (p.getRightX() > x && p.getRightX() < x + width) ) &&
		     ( (p.getTopY() > y && p.getTopY() < y + height) ||
			   (p.getBottomY() > y && p.getBottomY() < y + height) ) )
			return true;
		return false;
	}
	
	@Override
	public Image getImage () {
		return img;
	}
	
	@Override
	public int getPositionX () {
		return x;
	}
	
	@Override
	public int getPositionY () {
		return y;
	}
}
