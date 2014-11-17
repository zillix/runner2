package com.zillix.game.objects.collectables;

import com.zillix.game.objects.RadialOriginObject;

public class IceBallCollectable extends Collectable {

	private static final String ICEBALL_IMAGE_PATH = "objects/iceball.png";
	
	public IceBallCollectable(RadialOriginObject pOrigin)
	{
		super(pOrigin);
	}
	
	public IceBallCollectable() 
	{
		super();
	}
	
	@Override
	public void setup(RadialOriginObject pOrigin)
	{
		super.setup(pOrigin);
		type = CollectableType.ICEBALL;
	}
	
	@Override
	public String getImagePath()
	{
		return ICEBALL_IMAGE_PATH;
	}

}
