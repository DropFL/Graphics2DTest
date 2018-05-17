package com.rshtiger.platformer.collision;

public class AABBCollider extends Collider {
	@Override
	public boolean isCollided (Shape s1, Shape s2) {
		if (s1.rotation != 0 || s2.rotation != 0)
			throw new IllegalArgumentException("AABB Collider cannot handle rotated shape.");
		
		double pivotLeft = (s1.x > s2.x) ? s1.x : s2.x,
				pivotRight = (s1.x + s1.width < s2.x + s2.width) ? (s1.x + s1.width) : (s2.x + s2.width),
				pivotTop = (s1.y > s2.y) ? s1.y : s2.y,
				pivotBottom = (s1.y + s1.height < s2.y + s2.height) ? (s1.y + s1.height) : (s2.y + s2.height);
		
		return pivotLeft < pivotRight && pivotTop < pivotBottom;
	}
}
