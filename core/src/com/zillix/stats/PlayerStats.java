package com.zillix.stats;

import com.zillix.game.objects.Player;

public class PlayerStats {
	
	private Player player;
	

	public double furthestDistance = 0;
	
	public PlayerStats(Player player)
	{
		this.player = player;
	}
	
	public void update(float delta)
	{
		if (player.getOriginDistance() > furthestDistance)
		{
			furthestDistance = player.getOriginDistance();
		}
	}
}
