package com.zillix.game.objects;

public class IceBall extends RadialObject {
	
	private static final float ICE_BALL_VELOCITY_Y = 10 * 30;
	private static final String ICEBALL_IMAGE_PATH = "objects/iceball.png";
	
	
	public IceBall(RadialOriginObject pOrigin)
	{
		super(pOrigin);
		velocity.y = ICE_BALL_VELOCITY_Y;
	}
	

	@Override
	protected String getImagePath()
	{
		return ICEBALL_IMAGE_PATH;
	}
	
	@Override
	public void debugUpdate(float delta)
	{
		super.debugUpdate(delta);
	}
}
