package com.zillix.game.pools;

import com.badlogic.gdx.utils.Pool;
import com.zillix.game.objects.RadialObject;


public class RadialObjectPool<T extends RadialObject> extends Pool<T> {
	Class<T> ClassType;
	
	public RadialObjectPool(Class<T> ClassType)
	{
		super();
		this.ClassType = ClassType;
	}
	
	
	@Override 
	protected T newObject() {
		 try {
			 T object = ClassType.newInstance();
			 object.setPool(this);
			 return object;
		 }
		 catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return null;
	}
	
	public boolean freeRadialObject(RadialObject object)
	{
		if (ClassType.isInstance(object))
		{
			free(ClassType.cast(object));
			
			return true;
		}
		
		return false;
	}
}
