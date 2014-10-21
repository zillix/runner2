package com.zillix.game.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.input.GestureDetector;
import com.zillix.game.controllers.LevelController;

public class RunnerInput extends InputAdapter {
	protected LevelController controller;
	
	protected GestureDetector gestureDetector;
	public RunnerInput(LevelController controller, GestureDetector gestureDetector)
	{
		this.controller = controller;
		this.gestureDetector = gestureDetector;
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
		return gestureDetector.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return gestureDetector.touchUp(screenX, screenY, pointer, button);
	} 
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return gestureDetector.touchDragged(screenX, screenY, pointer);
	} 
}
