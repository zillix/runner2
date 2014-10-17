package com.zillix.game.objects;


public class Planet extends RadialOriginObject {
	@Override
	public void rotate(float rotateAngle)
	{
		radius = Math.max(minRadius, radius + rotateAngle * increaseRate);
		sprite.setSize(radius * 2, radius * 2);
		sprite.setOrigin(radius, radius);
	}
}
