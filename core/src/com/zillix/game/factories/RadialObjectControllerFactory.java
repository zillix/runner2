package com.zillix.game.factories;

import com.zillix.game.Level;
import com.zillix.game.controllers.RadialObjectController;
import com.zillix.game.objects.IceBall;
import com.zillix.game.objects.RadialObject;

public class RadialObjectControllerFactory {
	
	private Level level;
	
	public RadialObjectControllerFactory(Level level)
	{
		this.level = level;
	}
	
	public RadialObjectController createRadialObjectController(RadialObject object)
	{
		/*if (object.getClass() == IceBall.class)
		{
			
		}
		else*/
		{
			return new RadialObjectController(object, level);
		}
	}
}
