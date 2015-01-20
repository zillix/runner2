package com.zillix.game.gamedata;

import java.util.HashMap;

public class StatData {
	public enum GameStatEnum
	{
		RUN_SPEED,
		JUMP_POWER,
		DOUBLE_JUMP_PERCENT,
		MAX_HP,
		HP_REGEN
	} 
	
	private HashMap<GameStatEnum, StatDataElement> data;
	
	public StatData()
	{
		data = new HashMap<StatData.GameStatEnum, StatDataElement>();
		data.put(GameStatEnum.RUN_SPEED, new StatDataElement("Run Speed",
				12,
				1.5f,
				80));
	
		data.put(GameStatEnum.JUMP_POWER, new StatDataElement("Jump Power",
				15,
				2,
				100,
				60));
		
		data.put(GameStatEnum.DOUBLE_JUMP_PERCENT, new StatDataElement("Double Jump Power",
				0,
				.25f,
				80,
				100,
				1,
				StatDataElement.DEFAULT_DIMINISH_STAT_VALUE));
		
		data.put(GameStatEnum.MAX_HP, new StatDataElement("Max Health",
				60,
				10,
				50,
				30,
				0,
				0));
	}
	
	public StatDataElement getStatDataElement(GameStatEnum statEnum)
	{
		return data.get(statEnum);
	}
}
