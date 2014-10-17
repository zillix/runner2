package com.zillix.util;

public class PolarUtil {
	public static double toDegrees(double radians)
	{
		return radians * 180 / Math.PI;
	}
	
	public static double toRadians(double degrees)
	{
		return degrees * Math.PI / 180;
	}
	
	public static double angleAbs(double ang1, double ang2)
	{
		ang1 = standardizeAngle(ang1);
		ang2 = standardizeAngle(ang2);
		double diff = Math.abs(ang1 - ang2);
		
		if (diff>180)
		diff=180 - (diff - 180);
		
		return Math.abs(diff);
	}
	
	public static double standardizeAngle(double ang) 
	{
		return ang % 360;
	}
	
	public static double squaredDistancePolar(double distance1, double radians1, double distance2, double radians2)
	{
		return Math.pow(distance1,  2) + Math.pow(distance2, 2) - 2 * distance1 * distance2 * Math.cos(radians2 - radians1);
	}
	
	public static double squaredDistancePointLineSegment(double a, double b, double x1, double y1, double x2, double y2)
	{
		double A= a-x1;
		double B=b-y1;
		double C=x2-x1;
		double D=y2-y1;
		double dot = A*C+B*D;
		double len_sq = C*C+D*D;
		double param=dot/len_sq;
		
		double xx,yy;
		if (param < 0)
		{
			xx=x1;
			yy=y1;
		}
		else if (param > 1)
		{
			xx=x2;
			yy=y2;
		}
		else
		{
			xx=x1+ param*C;
			yy=y1+param*D;
		}
		return squaredDistance(a,b,xx,yy);
	}
	
	public static double squaredDistancePointLineSegmentPolar(double pr, double pa, double r1, double a1, double r2, double a2)
	{
		// NOTE this likely doesn't work...
		double squaredSegDist = squaredDistancePolar(r1, a1, r2, a2);
		if (squaredSegDist == 0)
		{
			return squaredDistancePolar(pa, pr, r1, a2);
		}
		
		double t = polarDot(pr - r1, pa - a1, r2 - r1, a2 - a1) / squaredSegDist;
		if ( t < 0) return squaredDistancePolar(pr, pa, r1, a1);
		else if (t > 1) return squaredDistancePolar(pr, pa, r2, a2);
		
		return squaredDistancePolar(pr, pa, r1 + t * (r2 - r1), a1 + t * (a1 - a1));
	}
	
	public static double polarDot(double r1, double a1, double r2, double a2)
	{
		return  r1 * r2 * (Math.cos(a1) * Math.cos(r2) + Math.sin(a1) * Math.sin(a2));
	}
	
	public static double squaredDistance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
	}
}
