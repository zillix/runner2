package com.zillix.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Pool;
import com.zillix.game.Level;
import com.zillix.game.factories.RadialObjectControllerFactory;
import com.zillix.game.objects.IceBall;
import com.zillix.game.objects.Platform;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.RadialObject;
import com.zillix.game.objects.RadialObjectPoolManager;
import com.zillix.game.objects.RadialObjectSpawner;
import com.zillix.game.objects.collectables.IceBallCollectable;

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
				
				
	}

	public void update(float delta)
	{
		playerController.update(delta);
		level.getPlanet().update(delta);
		
		if (player.getOriginDistance() > level.getPlayerStats().furthestDistance)
		{
			double debt = player.getOriginDistance() - level.getPlayerStats().furthestDistance;
			platformSpawner.addDebt(debt);
			iceBallSpawner.addDebt(debt);
		}
		
		for (RadialObjectListController controller : radialObjectListControllers)
		{
			controller.update(delta);
		}
		
		level.getPlayerStats().update(delta);
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
