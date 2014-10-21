package com.zillix.game.controllers;

import com.zillix.game.Level;
import com.zillix.game.factories.RadialObjectControllerFactory;
import com.zillix.game.objects.Platform;
import com.zillix.game.objects.Player;
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
	RadialObjectListController platformController;
	RadialObjectListController collectableController;
	RadialObjectListController gameObjectController;
	RadialObjectSpawner<Platform> platformSpawner;
	RadialObjectSpawner<IceBallCollectable> iceBallSpawner;
	Player player;
	
	RadialObjectControllerFactory radialObjectControllerFactory;
	
	public LevelController(Level pLevel)
	{
		level = pLevel;
		player = pLevel.getPlayer();
		radialObjectControllerFactory = new RadialObjectControllerFactory(level);
		playerController = new PlayerController(level.getPlayer(), level);
		platformController = new RadialObjectListController(level.getPlatforms(), radialObjectControllerFactory, level.getPlanet());
		collectableController = new CollectableListController(level.getCollectables(), radialObjectControllerFactory, level.getPlanet(), player);
		gameObjectController = new RadialObjectListController(level.getGameObjects(), radialObjectControllerFactory, level.getPlanet());
		
		platformSpawner = new RadialObjectSpawner<Platform>(Platform.class, level.getPlatforms(), player, level.getPlanet(), INITIAL_PLATFORM_QUANTITY, PLATFORM_MIN_SPAWN_DISTANCE, PLATFORM_MAX_SPAWN_DISTANCE, DISTANCE_PER_PLATFORM);
		iceBallSpawner = new RadialObjectSpawner<IceBallCollectable>(IceBallCollectable.class, level.getCollectables(), player, level.getPlanet(), INITIAL_ICEBALL_QUANTITY, ICEBALL_MIN_SPAWN_DISTANCE, ICEBALL_MAX_SPAWN_DISTANCE, DISTANCE_PER_ICEBALL);
	}

	public void update(float delta)
	{
		playerController.update(delta);
		level.getPlanet().update(delta);
		platformController.update(delta);
		collectableController.update(delta);
		gameObjectController.update(delta);
		
		if (player.getOriginDistance() > level.getPlayerStats().furthestDistance)
		{
			double debt = player.getOriginDistance() - level.getPlayerStats().furthestDistance;
			platformSpawner.addDebt(debt);
			iceBallSpawner.addDebt(debt);
		}
		
		level.getPlayerStats().update(delta);
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
}
