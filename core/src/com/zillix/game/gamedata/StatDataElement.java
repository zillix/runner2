package com.zillix.game.gamedata;

import java.util.ArrayList;

public class StatDataElement {

	private float baseStatValue;
	private float increaseStatValue;
	private int diminishStatLevel;
	private float diminishStatValue;
	private float baseCostValue;
	private float increaseCostValue;
	private String name;
	
	public static final int DEFAULT_DIMINISH_STAT_LEVEL = 10;
	public static final float DEFAULT_DIMINISH_STAT_VALUE = .9f;
	
	
	public StatDataElement(String name,
			float baseStatValue,
			float increaseStatValue,
			float baseCostValue)
	{
		this(name, 
				baseStatValue, 
				increaseStatValue,
				baseCostValue,
				baseCostValue,
				DEFAULT_DIMINISH_STAT_LEVEL,
				DEFAULT_DIMINISH_STAT_VALUE);
	}
	
	public StatDataElement(String name,
			float baseStatValue,
			float increaseStatValue,
			float baseCostValue,
			float increaseCostValue)
	{
		this(name,
				baseStatValue, 
				increaseStatValue,
				baseCostValue,
				increaseCostValue,
				DEFAULT_DIMINISH_STAT_LEVEL,
				DEFAULT_DIMINISH_STAT_VALUE);
	}
	
	public StatDataElement(String name,
			float baseStatValue,
			float increaseStatValue,
			float baseCostValue,
			float increaseCostValue,
			int diminishStatLevel,
			float diminishStatValue)
	{
		this.name = name;
		this.baseStatValue = baseStatValue;
		this.increaseStatValue = increaseStatValue;
		this.baseCostValue = baseCostValue;
		this.increaseCostValue = increaseCostValue;
		this.diminishStatLevel = diminishStatLevel;
		this.diminishStatValue = diminishStatValue;
	}
	
	public float getStatValueForLevel(int level)
	{
		float value = baseStatValue + increaseStatValue * level;
		if (level < diminishStatLevel)
		{
			return value;
		}
	
		for (int i = diminishStatLevel; i <= level; i++)
		{
			value += Math.pow(diminishStatValue, level - diminishStatLevel) * increaseStatValue;
		}
		
		return value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getUpgradeCost(int level)
	{
		return (int)(baseCostValue + increaseCostValue * (level - 1));
	}
}
