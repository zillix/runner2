package com.zillix.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.zillix.game.objects.Planet;

public class LevelLoader {

	public static Level loadLevel(String levelName)
	{
		// For now, just create the level on demand.
		// Eventually we'll read it in from data
		
		Level level = null;
		Planet planet = null;
		if (levelName == "level1");
		{
			planet = new Planet();
			planet.setRadius(200);
			planet.setIncreaseRate(.2f);
			planet.setMinRadius(150);
			
			Sprite sprite = new Sprite(new Texture("planets/planetTransparent1.png"));
			sprite.setOrigin(planet.getRadius(), planet.getRadius());
			sprite.setSize(planet.getRadius() * 2, planet.getRadius() * 2);
			planet.setSprite(sprite);
			
			level = new Level(planet);
		}
		
		return level;
	}
}
