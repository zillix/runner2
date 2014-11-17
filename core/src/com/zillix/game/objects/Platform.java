package com.zillix.game.objects;

public class Platform extends RadialObject {

	private static final String PLATFORM_IMAGE_PATH = "platforms/platform2.png";
	
	public Platform(RadialOriginObject pOrigin)
	{
		super(pOrigin);
	}
	
	public Platform() {}
	
	@Override
	public String getImagePath()
	{
		return PLATFORM_IMAGE_PATH;
	}

}
