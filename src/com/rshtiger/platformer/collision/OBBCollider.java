package com.rshtiger.platformer.collision;

import com.rshtiger.platformer.entity.Player;

public class OBBCollider extends Collider {
	
	@Override
	public boolean isCollided (Shape player, Shape s2) {
		
		double rad = Math.toRadians(s2.getRotation());
		double sin = Math.sin(rad), cos =  Math.cos(rad);
		double w = s2.getRightX() - s2.getLeftX(), h = s2.getBottomY() - s2.getTopY();
		double[][] points = {
								{s2.getLeftX(), s2.getTopY()},
								{s2.getLeftX() + w * cos, s2.getTopY() + w * sin},
								{s2.getLeftX() - h * sin, s2.getTopY() + h * cos},
								{s2.getLeftX() + w * cos - h * sin, s2.getTopY() + w * sin + h * cos}
							};
		
		double min[] = points[0], max[] = points[0];
		
		for (double[] point : points) {
			if(min[0] > point[0]) min[0] = point[0];
			if(min[1] > point[1]) min[1] = point[1];
			if(max[0] < point[0]) max[0] = point[0];
			if(max[1] < point[1]) max[1] = point[1];
		}
		
		if ((player.getLeftX() > min[0] ? player.getLeftX() : min[0]) > (player.getRightX() > max[0] ? player.getRightX() : max[0]) ||
			(player.getTopY() > min[1] ? player.getTopY() : min[1]) > (player.getBottomY() > max[1] ? player.getBottomY() : max[1]))
			return false;
		
		sin = -sin;
		w = h = ((Player) player).getSize();
		
		points[0][0] = player.getLeftX();
		points[0][1] = player.getTopY();
		points[1][0] = player.getLeftX() + w * cos;
		points[1][1] = player.getTopY() + w * sin;
		points[2][0] = player.getLeftX() - h * sin;
		points[2][1] = player.getTopY() + h * cos;
		points[3][0] = player.getLeftX() + w * cos - h * sin;
		points[3][1] = player.getTopY() + w * sin + h * cos;
		
		min = max = points[0];
		
		for (double[] point : points) {
			if(min[0] > point[0]) min[0] = point[0];
			if(min[1] > point[1]) min[1] = point[1];
			if(max[0] < point[0]) max[0] = point[0];
			if(max[1] < point[1]) max[1] = point[1];
		}
		
		if ((s2.getLeftX() > min[0] ? s2.getLeftX() : min[0]) > (s2.getRightX() > max[0] ? s2.getRightX() : max[0]) ||
			(s2.getTopY() > min[1] ? s2.getTopY() : min[1]) > (s2.getBottomY() > max[1] ? s2.getBottomY() : max[1]))
			return false;
		
		return true;
	}
}
