package com.zillix.game.controllers;

import java.util.ArrayList;

import com.zillix.game.objects.Planet;
import com.zillix.game.objects.RadialObject;
import com.zillix.game.objects.collectables.Collectable;
import com.zillix.game.objects.collectables.ICollector;

public class CollectableListController extends
		RadialObjectListController {
	
	private ICollector collector;
	
	public CollectableListController(ArrayList<? extends RadialObject> list, Planet planet, ICollector collector)
	{
		super(list, planet);
		this.collector = collector;
	}
	
	@Override
	public void processList(float deltaTime)
	{
		Collectable collectable;
		for (RadialObject object : list)
		{
			if (!(object instanceof Collectable))
			{
				continue;
			}
			
			collectable = (Collectable)object;
			
			if (collector.isTouching(collectable))
			{
				collector.collect(collectable);
				collectable.onCollected();
			}
		}
		
		super.processList(deltaTime);
	}

}
