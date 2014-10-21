package com.zillix.game.input;

import com.badlogic.gdx.Gdx;
import com.zillix.game.controllers.LevelController;

public class SimpleRunnerGestureDetector extends RunnerGestureDetector {
	public SimpleRunnerGestureDetector(LevelController controller, GestureListener listener)
	{
		super(controller, 20, 0.4f, 1.1f, 0.15f, listener);
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
		return super.touchDown(screenX, screenY, pointer, button);
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
		return super.touchUp(screenX, screenY, pointer, button);
	}
}
