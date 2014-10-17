package com.zillix.game;

import java.util.ArrayList;

import com.zillix.game.objects.Planet;
import com.zillix.game.objects.Platform;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.collectables.IceBallCollectable;
import com.zillix.stats.PlayerStats;


public class Level {

	private Player player;
	private Planet planet;
	private ArrayList<Platform> platforms;
	private ArrayList<IceBallCollectable> iceBalls;
	private PlayerStats playerStats;
	
	public Level(Planet pPlanet)
	{
		planet = pPlanet;
		player = new Player(pPlanet, pPlanet.getRadius(), 0f);
		platforms = new ArrayList<Platform>();
		iceBalls = new ArrayList<IceBallCollectable>();
		playerStats = new PlayerStats(player);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}
	
	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}
	
	public ArrayList<IceBallCollectable> getIceBalls() {
		return iceBalls;
	}
	
	public PlayerStats getPlayerStats()
	{
		return playerStats;
	}
	
}
