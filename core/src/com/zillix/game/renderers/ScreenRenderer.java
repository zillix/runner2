package com.zillix.game.renderers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScreenRenderer {
	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;
	
	protected ArrayList<IRenderer> renderers;
	
	public ScreenRenderer(ArrayList<IRenderer> renderers)
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		camera.update();
		viewport = new FitViewport(960, 540, camera);
		
		this.renderers = renderers;

	}
	
	public void render(float delta)
	{
		for (IRenderer renderer : renderers)
		{
			renderer.render(batch,  camera, delta);
		}
	}
	
	public void dispose()
	{
		batch.dispose();
		for (IRenderer renderer : renderers)
		{
			renderer.dispose();
		}
	}
	
	public void resize(int width, int height)
	{
		viewport.update(width, height);
		for (IRenderer renderer : renderers)
		{
			renderer.resize(width,  height);
		}
	}
}
