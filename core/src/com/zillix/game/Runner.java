package com.zillix.game;

import com.badlogic.gdx.Game;

public class Runner extends Game {
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}
}
