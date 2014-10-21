package com.zillix.game.renderers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.zillix.game.Level;
import com.zillix.game.objects.Platform;
import com.zillix.game.objects.RadialObject;
import com.zillix.game.objects.collectables.Collectable;
import com.zillix.game.objects.collectables.IceBallCollectable;

public class LevelRenderer implements IRenderer {

	protected Level level;
	public LevelRenderer(Level pLevel)
	{
		level = pLevel;
	}
	
	public void render(SpriteBatch batch, OrthographicCamera camera, float delta)
	{
		Vector3 startPosition = camera.position.cpy();
		camera.position.set(level.getPlayer().getAbsolutePosition(), 0);
		float cameraAngle = -getCameraCurrentXYAngle(camera) + 180;
		camera.rotate(cameraAngle - level.getPlayer().getOriginAngle() - 90);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			drawPlatforms(batch);
			drawGameObjects(batch);
			drawCollectables(batch);
			drawPlanet(batch);
			drawPlayer(batch);
		batch.end();
		
		camera.position.set(startPosition);
		camera.rotate(-getCameraCurrentXYAngle(camera));
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
	}
	
	private void drawPlanet(SpriteBatch batch)
	{
		level.getPlanet().draw(batch);
	}
	
	private void drawPlayer(SpriteBatch batch)
	{
		level.getPlayer().draw(batch);
	}
	
	private void drawPlatforms(SpriteBatch batch)
	{
		for (Platform p : level.getPlatforms())
		{
			p.draw(batch);
		}
	}
	
	private void drawGameObjects(SpriteBatch batch)
	{
		for (RadialObject p : level.getGameObjects())
		{
			p.draw(batch);
		}
	}
	
	private void drawCollectables(SpriteBatch batch)
	{
		for (Collectable p : level.getCollectables())
		{
			p.draw(batch);
		}
	}
	
	public float getCameraCurrentXYAngle(OrthographicCamera cam)
	{
	    return (float)Math.toDegrees(Math.atan2(cam.up.x, cam.up.y));
	}
	
	public void dispose()
	{}
}
