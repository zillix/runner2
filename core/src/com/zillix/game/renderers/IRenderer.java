package com.zillix.game.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderer {
	public void render(SpriteBatch batch,  OrthographicCamera camera, float delta);
	public void resize(int width, int height);
	public void dispose();
}
