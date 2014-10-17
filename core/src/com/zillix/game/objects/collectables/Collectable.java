package com.zillix.game.objects.collectables;

import com.zillix.game.objects.RadialObject;
import com.zillix.game.objects.RadialOriginObject;
import com.zillix.util.PolarUtil;


public class Collectable extends RadialObject {
	
	public enum CollectableType
	{
		NONE,
		COIN,
		ICEBALL
	}
	
	private static final double DEFAULT_COLLECT_DISTANCE = 30;
	
	protected CollectableType type = CollectableType.NONE;
	
	protected double collectDistance = DEFAULT_COLLECT_DISTANCE;
	
	public Collectable(RadialOriginObject pOrigin)
	{
		this();
		setup(pOrigin);
	}
	
	public Collectable()
	{
		super();
	}
	
	protected double getCollectDistance()
	{
		return collectDistance;
	}
	
	public boolean isTouching(RadialObject object)
	{
		return PolarUtil.squaredDistancePolar(object.getOriginDistance(),
				object.getOriginAngleRadians(),
				getOriginDistance(),
				getOriginAngleRadians()) < Math.pow(collectDistance, 2);
	}
	
	public void onCollected()
	{
		isDead = true;
	}

	public CollectableType getType() {
		return type;
	}

	public void setType(CollectableType type) {
		this.type = type;
	}
}
