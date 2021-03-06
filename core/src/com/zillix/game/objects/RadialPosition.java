package com.zillix.game.objects;

import com.badlogic.gdx.math.Vector2;

public class RadialPosition {
	protected float originDistance = 0;
	protected float originAngle = 0;
	protected RadialOriginObject origin;
	
	private Vector2 coords;

	public RadialPosition(RadialOriginObject pOrigin, float originDistance, float originAngle)
	{
		this.originDistance = originDistance;
		this.originAngle = originAngle;
		coords = new Vector2();
	}
	
	public Vector2 getRelativeCoordinates()
	{
		coords.set((float)(Math.cos(Math.toRadians(originAngle)) * originDistance), (float)(Math.sin(Math.toRadians(originAngle)) * originDistance));
		return coords;
	}
	
	public Vector2 getAbsoluteCoordinates()
	{
		if (origin != null)
		{
			return getRelativeCoordinates().cpy().add(origin.getPosition());
		}
		return getRelativeCoordinates().cpy();
}

	public float getOriginAngle() {
		return originAngle;
	}

	public void setOriginAngle(float originAngle) {
		this.originAngle = originAngle;
	}

	public float getOriginDistance() {
		return originDistance;
	}

	public void setOriginDistance(float originDistance) {
		this.originDistance = originDistance;
	}
	
	// TODO: remove this allocation
	public RadialPosition copy()
	{
		return new RadialPosition(origin, originDistance, originAngle);
	}
	
	public void reset()
	{
		originDistance = 0;
		originAngle = 0;
		coords.set(0, 0);
	}

	public RadialOriginObject getOrigin() {
		return origin;
	}

	public void setOrigin(RadialOriginObject origin) {
		this.origin = origin;
	}
}
