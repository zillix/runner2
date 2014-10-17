package com.zillix.game.objects.collectables;

import java.util.HashMap;
import java.util.Map;



public class Collection {
	


	private Map<Collectable.CollectableType, Integer> _collection;
	
	public Collection()
	{
		_collection = new HashMap<Collectable.CollectableType, Integer>();
	}
	
	public void addCollectable(Collectable collectable)
	{
		addCollectable(collectable.getType(), 1);
	}
	
	public void addCollectable(Collectable.CollectableType type, int count)
	{
		if (_collection.get(type) == null)
		{
			_collection.put(type,  0);
		}
		
		_collection.put(type, _collection.get(type) + count);
	}
	
	public void decrementCollectable(Collectable.CollectableType type, int count)
	{
		if (_collection.get(type) != null)
		{
			_collection.put(type, Math.max(0, _collection.get(type) - count));
		}
	}
	
	public int getCount(Collectable.CollectableType type)
	{
		if (_collection.get(type) == null)
		{
			return 0;
		}
		
		return _collection.get(type);
	}
}
