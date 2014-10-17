package com.zillix.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zillix.game.Level;
import com.zillix.game.objects.Player;

public class HudRenderer implements IRenderer {
	private Level level;
	private BitmapFont font;
	private Player player;
	
	public HudRenderer(Level level)
	{
		this.level = level;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		player = level.getPlayer();
	}
	
	public void render(SpriteBatch batch, OrthographicCamera camera, float delta)
	{
		batch.begin();
			font.drawMultiLine(batch,  "Ice Balls: " + Integer.toString(player.getIceBallCount()), 10, Gdx.graphics.getHeight() - 10);
		batch.end();
	}
	
	public void dispose()
	{
		font.dispose();
	}
}
