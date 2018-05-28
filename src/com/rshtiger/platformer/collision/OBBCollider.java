package com.rshtiger.platformer.collision;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class OBBCollider extends Collider {
	private double savedAxis[][];
	private double savedExtends[];
	private double playerAxis[][] = {{1, 0}, {0, 1}};
	private double playerExtends[];
	double C[][];

	double AD[];      //Dot(A_i,D)
	double R0,R1,R;    //interval radii and distance between centers
	double R01;        //=R0+R1

	OBBCollider(Shape player, Shape me){
			savedAxis[0] = getAxis(me.getLeftX(), me.getBottomY(), me.getRightX(), me.getBottomY());
			savedAxis[1] = getAxis(me.getRightX(), me.getBottomY(), me.getRightX(), me.getTopY());
			savedExtends = getExtend(me.getRightX(), me.getTopY(), getCenterX(me) , getCenterY(me));
			playerExtends = getExtend(player.getRightX(), player.getLeftX(), getCenterX(player), getCenterY(player));
	}

	@Override
	public boolean isCollided (Shape player, Shape s2) {
		// Not implemented yet
		double D[] = {getCenterX(s2) - getCenterX(player), getCenterY(s2) - getCenterY(player)};
		C[0][0]= abs(dotProduct(playerAxis[0],savedAxis[0]));
		C[0][1]= abs(dotProduct(playerAxis[0],savedAxis[1]));
		AD[0] = abs(dotProduct(playerAxis[0], D));
		R1 = savedExtends[0]*C[0][0]+savedExtends[1]*C[0][1];
		R01 = playerExtends[0]+R1;
		if(AD[0] > R01) return false;

		C[1][0]= abs(dotProduct(playerAxis[1],savedAxis[0]));
		C[1][1]= abs(dotProduct(playerAxis[1],savedAxis[1]));
		AD[1] = abs(dotProduct(playerAxis[1], D));
		R1 = savedExtends[0]*C[1][0]+savedExtends[1]*C[1][1];
		R01 = playerExtends[1]+R1;
		if(AD[1] > R01) return false;

		R0 = playerExtends[0]*C[0][0]+savedExtends[1]*C[1][0];
		R01 = R0 + savedExtends[0];
		if(dotProduct(savedAxis[0], D) > R01) return false;

		R0 = playerExtends[0]*C[0][1]+savedExtends[1]*C[1][1];
		R01 = R0 + savedExtends[1];
		if(dotProduct(savedAxis[1], D) > R01) return false;

		return true;
	}

	double[] getAxis(double Ax, double Ay, double Bx, double By){
		double tmplength = sqrt((Ax - Bx) * (Ax - Bx) + (Ay - By) * (Ay - By));
		double rsltLngth[] = { abs(Ax - Bx) / tmplength, abs(Ay - By) / tmplength};
		return rsltLngth;
	}

	double dotProduct(double v0[], double v1[]){
		return v0[0]*v1[0] + v0[1]*v1[1];
	}

	double[] getExtend(double sx, double sy, double centerx, double centery){
		double rslt[] = new double[2];
		rslt[0] = sx - centerx;
		rslt[1] = sy - centery;
		return rslt;
	}

	double getCenterX(Shape s){ return (s.getLeftX() + s.getRightX()) / 2; }

	double getCenterY(Shape s){ return (s.getTopY() + s.getBottomY()) / 2; }
}
