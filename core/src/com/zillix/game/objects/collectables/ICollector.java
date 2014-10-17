package com.zillix.game.objects.collectables;


public interface ICollector {
	public void collect(Collectable collectable);
	
	public int getCollectableCount(Collectable.CollectableType type);
	
	public boolean isTouching(Collectable collectable);
}
