package com.zillix.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.utils.PerformanceCounters;
import com.badlogic.gdx.utils.Pool;
import com.zillix.game.Level;
import com.zillix.game.factories.RadialObjectControllerFactory;
import com.zillix.game.objects.IceBall;
import com.zillix.game.objects.Platform;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.RadialObject;
import com.zillix.game.objects.RadialObjectSpawner;
import com.zillix.game.objects.collectables.IceBallCollectable;
import com.zillix.game.pools.RadialObjectPoolManager;

public class LevelController {
	
	// These should be imported through data
	private static final int INITIAL_PLATFORM_QUANTITY = 0;
	private static final int PLATFORM_MIN_SPAWN_DISTANCE = 100;
	private static final int PLATFORM_MAX_SPAWN_DISTANCE = 200;
	private static final int DISTANCE_PER_PLATFORM = 15;
	
	private static final int INITIAL_ICEBALL_QUANTITY = 0;
	private static final int ICEBALL_MIN_SPAWN_DISTANCE = 100;
	private static final int ICEBALL_MAX_SPAWN_DISTANCE = 200;
	private static final int DISTANCE_PER_ICEBALL = 30;
	
	Level level;
	PlayerController playerController;
	ArrayList<RadialObjectListController> radialObjectListControllers;
	RadialObjectPoolManager poolManager;
	
	RadialObjectSpawner<Platform> platformSpawner;
	RadialObjectSpawner<IceBallCollectable> iceBallSpawner;
	Player player;
	
	private PerformanceCounters performance;
	
	private boolean PROFILE = false;
	
	
	RadialObjectControllerFactory radialObjectControllerFactory;
	
	public LevelController(Level pLevel)
	{
		level = pLevel;
		player = pLevel.getPlayer();
		poolManager = new RadialObjectPoolManager();
		
		radialObjectControllerFactory = new RadialObjectControllerFactory(level);
		radialObjectListControllers = new ArrayList<RadialObjectListController>();
		
		playerController = new PlayerController(level.getPlayer(), level);
		radialObjectListControllers.add(new RadialObjectListController(level.getPlatforms(), radialObjectControllerFactory, poolManager, level.getPlanet()));
		radialObjectListControllers.add(new CollectableListController(level.getCollectables(), radialObjectControllerFactory, poolManager, level.getPlanet(), player));
		radialObjectListControllers.add(new RadialObjectListController(level.getGameObjects(), radialObjectControllerFactory, poolManager, level.getPlanet()));
		
		platformSpawner = new RadialObjectSpawner<Platform>(Platform.class, level.getPlatforms(), player, level.getPlanet(), INITIAL_PLATFORM_QUANTITY, PLATFORM_MIN_SPAWN_DISTANCE, PLATFORM_MAX_SPAWN_DISTANCE, DISTANCE_PER_PLATFORM);
		iceBallSpawner = new RadialObjectSpawner<IceBallCollectable>(IceBallCollectable.class, level.getCollectables(), player, level.getPlanet(), INITIAL_ICEBALL_QUANTITY, ICEBALL_MIN_SPAWN_DISTANCE, ICEBALL_MAX_SPAWN_DISTANCE, DISTANCE_PER_ICEBALL);
	
		// TODO: these may not need to exist as properties
		poolManager.addPool(platformSpawner, Platform.class);
		poolManager.addPool(iceBallSpawner, IceBall.class);
		
		if (PROFILE)
		{
			performance = new PerformanceCounters();
			performance.add("player");
			performance.add("planet");
			performance.add("objects");
			performance.add("stats");
		}
				
				
	}

	public void update(float delta)
	{
		// Player controller
		if (PROFILE) performance.counters.get(0).start();
		playerController.update(delta);
		if (PROFILE) performance.counters.get(0).stop();
		
		if (PROFILE) performance.counters.get(1).start();
		level.getPlanet().update(delta);
		if (PROFILE) performance.counters.get(1).stop();
		
		if (player.getOriginDistance() > level.getPlayerStats().furthestDistance)
		{
			double debt = player.getOriginDistance() - level.getPlayerStats().furthestDistance;
			platformSpawner.addDebt(debt);
			iceBallSpawner.addDebt(debt);
		}
		
		if (PROFILE) performance.counters.get(2).start();
		for (RadialObjectListController controller : radialObjectListControllers)
		{
			controller.update(delta);
		}
		if (PROFILE) performance.counters.get(2).stop();
		
		if (PROFILE) performance.counters.get(3).start();
		level.getPlayerStats().update(delta);
		if (PROFILE) performance.counters.get(3).stop();
		
		if (PROFILE)
		{
			String perf = "Controller performance: ";
			perf += " PLAYER: " + performance.counters.get(0).time.total;
			perf += " PLANET: " + performance.counters.get(1).time.total;
			perf += " OBJECTS: " + performance.counters.get(2).time.total;
			perf += " STATS: " + performance.counters.get(3).time.total;
			
			System.out.println(perf);
		}
		
		if (PROFILE) performance.tick(delta);
	}
	
	public <T> Pool<? extends RadialObject> getRadialObjectPool(Class<? extends RadialObject> classType)
	{
		return poolManager.getPool(classType);
	}
	
	public void leftPressed(int pointer) {
		playerController.leftPressed(pointer);
	}

	public void rightPressed(int pointer) {
		playerController.rightPressed(pointer);
	}

	public void jumpPressed(int pointer) {
		playerController.jumpPressed(pointer);
	}

	public void firePressed(int pointer) {
		playerController.firePressed(pointer);
	}

	public void leftReleased(int pointer) {
		playerController.leftReleased(pointer);
	}

	public void rightReleased(int pointer) {
		playerController.rightReleased(pointer);
	}

	public void jumpReleased(int pointer) {
		playerController.jumpReleased(pointer);
	}

	public void fireReleased(int pointer) {
		playerController.fireReleased(pointer);
	}
	
	public void swipeUp(float velocity) {
		playerController.swipeUp(velocity);
	}
	
	public void swipeDown(float velocity) {
		playerController.swipeDown(velocity);
	}
}
