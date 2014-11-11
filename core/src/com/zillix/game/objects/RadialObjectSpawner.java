package com.zillix.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Pool;

public class RadialObjectSpawner<T extends RadialObject> extends RadialObjectPool<T> {
	
	Class<T> ClassType;
	RadialOriginObject origin;
	
	RadialObject reference;
	
	double objectSpawnDebt = 0;
	double debtPerObject = 0;
	double minSpawnDistance = 0;
	double maxSpawnDistance = 0;
	
	public static final double DEFAULT_MIN_SPAWN_DISTANCE = 100;
	public static final double DEFAULT_MAX_SPAWN_DISTANCE = 200;
	public static final double DEFAULT_DEBT_PER_OBJECT = 20;
	
	ArrayList<? super T> outputList;
	
	public RadialObjectSpawner(Class<T> classType, ArrayList<? super T> outputList, RadialObject reference, RadialOriginObject origin, int initialQuantity)
	{
		this(classType, outputList, reference, origin, initialQuantity, DEFAULT_MIN_SPAWN_DISTANCE, DEFAULT_MAX_SPAWN_DISTANCE, DEFAULT_DEBT_PER_OBJECT);
	}
	
	public RadialObjectSpawner(Class<T> classType, ArrayList<? super T> outputList, RadialObject reference, RadialOriginObject origin, int initialQuantity, double minSpawnDistance, double maxSpawnDistance, double debtPerObject)
	{
		super(classType);
		this.ClassType = classType;
		this.origin = origin;
		this.outputList = outputList;
		this.reference = reference;
		this.debtPerObject = debtPerObject;
		this.minSpawnDistance = minSpawnDistance;
		this.maxSpawnDistance = maxSpawnDistance;
		spawnInitialMembers(initialQuantity);
	}
	
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
	
	private void spawnInitialMembers(int initialQuantity)
	{
		for (int i = 0; i < initialQuantity; i++)
		{
			T object = generateObject(minSpawnDistance 
					+ (maxSpawnDistance - minSpawnDistance) * Math.random()
					+ origin.radius);
			
			if (object != null)
			{
				outputList.add(object);
			}
		}
	}
	
	protected T generateObject(double originDistance)
	{
		T object = this.obtain();
		object.setup(origin);
		object.setOriginAngle((float)(Math.random() * 360));
		object.setOriginDistance((float)originDistance);
		return object;
	}
	
	public void addDebt(double debt)
	{
		objectSpawnDebt += debt / debtPerObject;
		
		while (objectSpawnDebt >= 1)
		{
			T object = generateObject(((float)(
						reference.getOriginDistance() 
						+ minSpawnDistance 
						+ (maxSpawnDistance - minSpawnDistance) * Math.random())));
			if (object != null)
			{
				fitObject(object);
				outputList.add(object);
			}
			
			
			objectSpawnDebt -= 1;
		}
	}
	
	protected void fitObject(T object)
	{
		// TODO make sure object doesn't overlap with other objects
	}
	
	public ArrayList<? super T> outputList()
	{
		return outputList;
	}
}
