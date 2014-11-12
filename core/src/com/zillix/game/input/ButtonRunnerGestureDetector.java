package com.zillix.game.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.zillix.game.controllers.LevelController;
import com.zillix.util.PolarUtil;

public class ButtonRunnerGestureDetector extends RunnerGestureDetector {
	enum Keys {
		JUMP, FIRE
	};
	
	private final int buttonRadiusSq = 400;
	private Map<Integer, Keys> pointers = new HashMap<Integer, Keys>();
	
	public ButtonRunnerGestureDetector(LevelController controller, GestureListener listener)
	{
		super(controller, 20, 0.4f, 1.1f, 0.15f, listener);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// Top left
		if (PolarUtil.squaredDistance(screenX, screenY, 0, 0) < buttonRadiusSq)
		{
			controller.jumpPressed(pointer);
			pointers.put(pointer, Keys.JUMP);
		}
		// Top right
		else if (PolarUtil.squaredDistance(screenX, screenY, Gdx.graphics.getWidth(), 0) < buttonRadiusSq)
		{
			controller.jumpPressed(pointer);
			pointers.put(pointer, Keys.JUMP);
		}
		// Bottom left
		else if (PolarUtil.squaredDistance(screenX, screenY, 0, Gdx.graphics.getHeight()) < buttonRadiusSq)
		{
			controller.firePressed(pointer);
			pointers.put(pointer, Keys.FIRE);
		}
		// Bottom right
		else if (PolarUtil.squaredDistance(screenX, screenY, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) < buttonRadiusSq)
		{
			controller.firePressed(pointer);
			pointers.put(pointer, Keys.FIRE);
		}
		else
		{
			if (screenX > Gdx.graphics.getWidth() / 2)
			{
				controller.rightPressed(pointer);
			}
			if (screenX < Gdx.graphics.getWidth() / 2)
			{
				controller.leftPressed(pointer);
			}
		}
		
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		
		if (pointers.containsKey(pointer))
		{
			switch (pointers.get(pointer))
			{
			case FIRE:
				controller.fireReleased(pointer);
				break;
				
			case JUMP:
				controller.jumpReleased(pointer);
				break;
				
			default:
				System.out.println("ButtonRunnerGestureDetector: Unsupported key " + pointers.get(pointer));
			}
			
			pointers.remove(pointer);
		}
		else
		{
			if (screenX > Gdx.graphics.getWidth() / 2)
			{
				controller.rightReleased(pointer);
			}
			if (screenX < Gdx.graphics.getWidth() / 2)
			{
				controller.leftReleased(pointer);
			}
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}
}
