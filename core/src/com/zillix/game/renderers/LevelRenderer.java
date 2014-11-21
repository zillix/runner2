package com.zillix.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zillix.game.Level;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.objects.Planet;
import com.zillix.game.objects.Platform;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.RadialObject;
import com.zillix.game.objects.collectables.Collectable;
import com.zillix.util.PolarUtil;

public class LevelRenderer implements IRenderer {

	protected Level level;
	protected RadialObjectRenderer radialObjectRenderer;
	protected ZAssetManager assets;
	public LevelRenderer(ZAssetManager assets, Level pLevel)
	{
		level = pLevel;
		this.assets = assets;
		radialObjectRenderer = new RadialObjectRenderer(assets);
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
		Planet planet = level.getPlanet();
		Sprite sprite = planet.getSprite();
		sprite.setPosition(planet.getPosition().x - sprite.getWidth() / 2, planet.getPosition().y - sprite.getWidth() / 2);
		sprite.draw(batch);
	}
	
	private void drawPlayer(SpriteBatch batch)
	{
		radialObjectRenderer.draw(level.getPlayer(), batch);
	}
	
	private void drawPlatforms(SpriteBatch batch)
	{
		for (Platform p : level.getPlatforms())
		{
			if (isOnScreen(p))
			{
				radialObjectRenderer.draw(p, batch);
			}
		}
	}
	
	private void drawGameObjects(SpriteBatch batch)
	{
		for (RadialObject p : level.getGameObjects())
		{
			radialObjectRenderer.draw(p, batch);
		}
	}
	
	private void drawCollectables(SpriteBatch batch)
	{
		for (Collectable p : level.getCollectables())
		{
			radialObjectRenderer.draw(p, batch);
		}
	}
	
	public float getCameraCurrentXYAngle(OrthographicCamera cam)
	{
	    return (float)Math.toDegrees(Math.atan2(cam.up.x, cam.up.y));
	}
	
	private boolean isOnScreen(RadialObject object)
	{
		
		// TODO: Do this in a mathy way
		Player player = level.getPlayer();
		double angleDiff = PolarUtil.angleAbs(object.getOriginAngle(), player.getOriginAngle());
		float distDiff = Math.abs(object.getOriginDistance() - player.getOriginDistance());
		boolean value = angleDiff <= 120
				&& distDiff < Gdx.graphics.getWidth() / 2;
		if (!value)
		{
			int a = 0;
		}
	
		return value;
		/*
		Vector2 absPos = object.getAbsolutePosition();
		if (Player.class.isInstance(object))
		{
			System.out.println("PLAYER ISONSCREEN: " + absPos.x + " " + absPos.y + " " + object.getWidth() + " " + object.getHeight());
		}
		return object.getWidth() + absPos.x > -OFFSCREEN_RENDER
				&& object.getWidth() + absPos.x < Gdx.graphics.getWidth() + OFFSCREEN_RENDER
				&& object.getHeight() + absPos.y > -OFFSCREEN_RENDER
				&& object.getHeight() + absPos.y < Gdx.graphics.getHeight() + OFFSCREEN_RENDER;
				*/
	}
	
	public void dispose()
	{}
	
	public void resize(int width, int height) {}
}
