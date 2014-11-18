package com.zillix.game.objects.collectables;

import com.zillix.game.objects.RadialOriginObject;

public class CoinCollectable extends Collectable {

	private static final String COIN_IMAGE_PATH = "objects/coin.png";
	
	public CoinCollectable(RadialOriginObject pOrigin)
	{
		super(pOrigin);
	}
	
	public CoinCollectable() 
	{
		super();
	}
	
	@Override
	public void setup(RadialOriginObject pOrigin)
	{
		super.setup(pOrigin);
		type = CollectableType.COIN;
	}
	
	@Override
	public String getImagePath()
	{
		return COIN_IMAGE_PATH;
	}

}
