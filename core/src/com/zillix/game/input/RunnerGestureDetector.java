package com.zillix.game.input;

import com.badlogic.gdx.input.GestureDetector;
import com.zillix.game.controllers.LevelController;

public class RunnerGestureDetector extends GestureDetector {
	protected LevelController controller;
	public RunnerGestureDetector(LevelController controller, float halfTapSquareSize, float tapCountInterval, float longPressDuration, float maxFlingDelay,
			GestureListener listener)
	{
		super(halfTapSquareSize, tapCountInterval, longPressDuration, maxFlingDelay, listener);
		this.controller = controller;
	}
}
