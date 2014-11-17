package com.zillix.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.PerformanceCounters;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.LevelController;
import com.zillix.game.input.ButtonRunnerGestureAdapter;
import com.zillix.game.input.ButtonRunnerGestureDetector;
import com.zillix.game.input.RunnerGestureAdapter;
import com.zillix.game.input.RunnerInput;
import com.zillix.game.renderers.HudRenderer;
import com.zillix.game.renderers.IRenderer;
import com.zillix.game.renderers.LevelRenderer;
import com.zillix.game.renderers.ScreenRenderer;

public class GameScreen implements Screen {

	private LevelController controller;
	private Level level;
	
	private RunnerInput input;
	private RunnerGestureAdapter runnerGesture;
	private GestureDetector gestureDetector;
	
	private ScreenRenderer renderer;
	
	private PerformanceCounters performance;
	
	private ZAssetManager assetManager;
	
	private boolean PROFILE = false;
	
	@Override
	public void show()
	{
		assetManager = new ZAssetManager();
		level = LevelLoader.loadLevel("level1");
		
		ArrayList<IRenderer> renderers = new ArrayList<IRenderer>();
		renderers.add(new LevelRenderer(assetManager, level));
		renderers.add(new HudRenderer(level));
		renderer = new ScreenRenderer(renderers);
		controller = new LevelController(level);
		
		runnerGesture = new ButtonRunnerGestureAdapter(controller);
		gestureDetector = new ButtonRunnerGestureDetector(controller, runnerGesture);
		
		input = new RunnerInput(controller, gestureDetector);
		Gdx.input.setInputProcessor(input);
		
		if (PROFILE)
		{
			performance = new PerformanceCounters();
			performance.add("clear");
			performance.add("render");
			performance.add("controller");
		}
		
	}
	
	@Override
	public void render (float delta) {
		assetManager.update();
		
		if (performance != null)
		{
			performance.tick();
		}
		
		if (performance != null)
		{
			performance.counters.get(0).start();
		}
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (performance != null)
		{
			performance.counters.get(0).stop();
		}
		
		if (performance != null)
		{
			performance.counters.get(2).start();
		}
		controller.update(delta);
		if (performance != null)
		{
			performance.counters.get(2).stop();
		}
		
		if (performance != null)
		{
			performance.counters.get(1).start();
		}
		renderer.render(delta);
		if (performance != null)
		{
			performance.counters.get(1).stop();
		}
		
		
		if (performance != null)
		{
			System.out.println("Performance: clear- " + performance.counters.get(0).time.latest + " render- " + performance.counters.get(2).time.latest + " controllers- " + performance.counters.get(1).time.latest);
		}
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
		renderer.dispose();
	}
	
}