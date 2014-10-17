package com.zillix.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.zillix.game.controllers.LevelController;
import com.zillix.game.renderers.HudRenderer;
import com.zillix.game.renderers.IRenderer;
import com.zillix.game.renderers.LevelRenderer;
import com.zillix.game.renderers.ScreenRenderer;

public class GameScreen extends InputAdapter implements Screen {

	private LevelController controller;
	private Level level;
	
	private ScreenRenderer renderer;
	
	@Override
	public void show()
	{
		level = LevelLoader.loadLevel("level1");
		
		ArrayList<IRenderer> renderers = new ArrayList<IRenderer>();
		renderers.add(new LevelRenderer(level));
		renderers.add(new HudRenderer(level));
		renderer = new ScreenRenderer(renderers);
		controller = new LevelController(level);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		controller.update(delta);
		
		renderer.render(delta);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftPressed(0);
		if (keycode == Keys.RIGHT)
			controller.rightPressed(0);
		if (keycode == Keys.SPACE)
			controller.jumpPressed(0);
		if (keycode == Keys.X)
			controller.firePressed(0);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftReleased(0);
		if (keycode == Keys.RIGHT)
			controller.rightReleased(0);
		if (keycode == Keys.SPACE)
			controller.jumpReleased(0);
		if (keycode == Keys.X)
			controller.fireReleased(0);
		return true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if (screenX > Gdx.graphics.getWidth() / 2)
		{
			controller.rightPressed(pointer);
		}
		if (screenX < Gdx.graphics.getWidth() / 2)
		{
			controller.leftPressed(pointer);
		}
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (screenX > Gdx.graphics.getWidth() / 2)
		{
			controller.rightReleased(pointer);
		}
		if (screenX < Gdx.graphics.getWidth() / 2)
		{
			controller.leftReleased(pointer);
		}
		return true;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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