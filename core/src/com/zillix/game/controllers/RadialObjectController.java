package com.zillix.game.controllers;

import com.zillix.game.Level;
import com.zillix.game.objects.Platform;
import com.zillix.game.objects.RadialObject;
import com.zillix.util.PolarUtil;


public class RadialObjectController 
{
	RadialObject object;
	Level level;
	
	double closeDistance = 0;
	
	private static final int DEFAULT_CLOSE_DISTANCE = 200;
	
	boolean usesSurfaceCollision = false;
	boolean usesPlatformCollision = false;
	boolean allowMovement = true;
	
	public RadialObjectController(RadialObject pObject, Level pLevel)
	{
		object = pObject;
		
		level = pLevel;
		closeDistance = DEFAULT_CLOSE_DISTANCE;
	}
	
	public void update(float deltaTime)
	{
		if (allowMovement)
		{
			stepAcceleration(deltaTime);
			
			capVelocity();
			
			if (usesSurfaceCollision)
			{
				collideSurface(deltaTime);
			}
			
			if (usesPlatformCollision)
			{
				collidePlatforms(deltaTime);
			}
			
			stepMovement(deltaTime);	
		}	
	}
	
	protected void capVelocity()
	{
		if (object.getVelocity().y > object.getMaxVelocity().y)
		{
			object.getVelocity().y = object.getMaxVelocity().y;
		}
		
		if (object.getVelocity().y < -object.getMaxVelocity().y)
		{
			object.getVelocity().y = -object.getMaxVelocity().y;
		}
		
		if (object.getVelocity().x > object.getMaxVelocity().x)
		{
			object.getVelocity().x = object.getMaxVelocity().x;
		}
		
		if (object.getVelocity().x < -object.getMaxVelocity().x)
		{
			object.getVelocity().x = -object.getMaxVelocity().x;
		}
	}
	
	protected void stepAcceleration(float deltaTime)
	{
		object.getVelocity().x += object.getAcceleration().x * deltaTime;
		
		double maxXVelocity = object.getDefaultVelocity().x;
		if (object.getAcceleration().x == 0)
		{
			maxXVelocity = 0;
		}
		
		if (object.getVelocity().x < -maxXVelocity)
		{
			object.getVelocity().x = Math.min(0, object.getVelocity().x + object.getDeceleration().x * deltaTime);
		}
		
		if (object.getVelocity().x > maxXVelocity)
		{
			object.getVelocity().x = Math.max(0, object.getVelocity().x - object.getDeceleration().x * deltaTime);
		}
		
		object.getVelocity().y += object.getAcceleration().y * deltaTime;
	}
	
	protected void stepMovement(float deltaTime)
	{
		double rotateAngle = -360 * object.getVelocity().x * deltaTime / (object.getOriginDistance() * 2 * Math.PI);
		object.setOriginDistance(object.getOriginDistance()
				- (object.getVelocity().y * deltaTime));
		
		object.setOriginAngle((float)PolarUtil.standardizeAngle(object.getOriginAngle() + rotateAngle));
		level.getPlanet().rotate((float)-rotateAngle);
	}
	
	protected void collideSurface(float deltaTime)
	{
		if (object.getVelocity().y > 0
				&& object.getBottomDistance() < level.getPlanet().getRadius())
		{
			object.setBottomDistance(level.getPlanet().getRadius());
			onObjectLanded();
		}
	}
	
	protected void collidePlatforms(float deltaTime)
	{
		if (object.getVelocity().y >= 0)
		{
			for (Platform platform : level.getPlatforms())
			{
				if (isClose(platform))
				{
					if (object.getBottomDistance() >= platform.getTopDistance())
					{
						float arcLength = platform.getWidth();
						double angleSpan = 360 * (arcLength / (2 * Math.PI * platform.getTopDistance()));
						
						
						// Estimate the position at the next frame
						RadialObject next = object.getObjectAtNextFrame(deltaTime);
						
						
						if (PolarUtil.angleAbs(object.getOriginAngle(), platform.getOriginAngle()) <= arcLength / 2
							&& PolarUtil.angleAbs((double)next.getOriginAngle(), platform.getOriginAngle()) < angleSpan / 2
							&& object.getBottomDistance() >= platform.getTopDistance()
							&& next.getBottomDistance() <= platform.getTopDistance())
						{
							object.setBottomDistance(platform.getTopDistance());
							onObjectLanded();
						}
						
					}
				}
			}
		}
	}
	
	protected boolean isClose(RadialObject obj)
	{
		double distance = PolarUtil.squaredDistancePolar(object.getOriginDistance(),
				PolarUtil.toRadians(object.getOriginAngle()),
				obj.getOriginDistance(),
				PolarUtil.toRadians(obj.getOriginAngle()));
		return distance <= Math.pow(closeDistance, 2);
	}
	
	public void onObjectLanded() 
	{
		object.getVelocity().y = 0;
	}

}
