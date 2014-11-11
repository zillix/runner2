package com.zillix.game.objects;

import java.util.HashMap;

public class RadialObjectPoolManager {

	private HashMap<Class<? extends RadialObject>, RadialObjectPool<? extends RadialObject>> map;
	
	
	public <T extends RadialObject> RadialObjectPool<? extends RadialObject> getPool(Class<T> classType)
	{
		if (map.containsKey(classType))
		{
			return map.get(classType);
		}
		
		RadialObjectPool<? extends RadialObject> pool = new RadialObjectPool<T>(classType);
		map.put(classType,  pool);
		return pool;
	}
	
	public boolean freeRadialObject(RadialObject  object, Class<? extends RadialObject> classType)
	{
		RadialObjectPool<? extends RadialObject> pool = getPool(classType);
		boolean result = pool.freeRadialObject(object);
		return result;
	}
}
