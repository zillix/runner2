package com.zillix.game.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.zillix.game.factories.RadialObjectControllerFactory;
import com.zillix.game.objects.Planet;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.RadialObject;

public class RadialObjectListController {
	
	private static final int MIN_SUBMERGE_DIST = 20;

	protected ArrayList<? extends RadialObject> list;
	protected Planet planet;
	
	protected RadialObjectControllerFactory factory;
	
	protected HashMap<RadialObject, RadialObjectController> controllers;
	
	public RadialObjectListController(ArrayList<? extends RadialObject> list, RadialObjectControllerFactory factory, Planet planet)
	{
		this.list = list;
		this.planet = planet;
		this.factory = factory;
		controllers= new HashMap<RadialObject, RadialObjectController>();
	}
	
	public void update(float deltaTime)
	{
		processList(deltaTime);
		cleanList();
	}
	
	protected void processList(float deltaTime)
	{
		for (int i = list.size() - 1; i >= 0; i--)
		{
			RadialObject object = list.get(i);
			if (!controllers.containsKey(object))
			{
				controllers.put(object, factory.createRadialObjectController(object));
			}
			controllers.get(object).update(deltaTime);
			object.debugUpdate(deltaTime);
			if (object.getOriginDistance() < planet.getRadius() - MIN_SUBMERGE_DIST)
			{
				object.setIsDead(true);
			}
		}
	}
	
	protected void cleanList()
	{
		for (int i = list.size() - 1; i >= 0; i--)
		{
			RadialObject object = list.get(i);
			if (object.getIsDead())
			{
				list.remove(i);
				controllers.remove(object);
			}
		}
	}
	
}
