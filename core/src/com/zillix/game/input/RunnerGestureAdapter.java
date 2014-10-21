package com.zillix.game.input;

import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.zillix.game.controllers.LevelController;


public class RunnerGestureAdapter extends GestureAdapter {
	protected LevelController controller;
	public RunnerGestureAdapter(LevelController controller)
	{
		this.controller = controller;
	}
}
