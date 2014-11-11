package com.zillix.game.pools;

import java.util.HashMap;

import com.zillix.game.objects.RadialObject;

public class RadialObjectPoolManager {

	private HashMap<Class<? extends RadialObject>, RadialObjectPool<? extends RadialObject>> map = new HashMap<Class<? extends RadialObject>, RadialObjectPool<? extends RadialObject>>();
	
	
	public <T extends RadialObject> RadialObjectPool<? extends RadialObject> getPool(Class<T> classType)
	{
		if (map.containsKey(classType))
		{
			if (map.get(classType) == null)
			{
				System.out.println("ERROR: Null RadialObjectPool for classType " + classType);
			}
			return map.get(classType);
		}
		
		RadialObjectPool<? extends RadialObject> pool = new RadialObjectPool<T>(classType);
		addPool(pool, classType);
		return pool;
	}
	
	public void addPool(RadialObjectPool<? extends RadialObject> pool, Class<? extends RadialObject> classType)
	{
		map.put(classType, pool);
	}
	
	public boolean freeRadialObject(RadialObject  object, Class<? extends RadialObject> classType)
	{
		RadialObjectPool<? extends RadialObject> pool = getPool(classType);
		return pool.freeRadialObject(object);
	}
}
