package com.zillix.game.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zillix.game.Level;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.HudController;
import com.zillix.game.objects.Player;
import com.zillix.game.objects.collectables.Collectable.CollectableType;
import com.zillix.game.ui.HudModel;
import com.zillix.game.ui.ShopButton;

public class HudRenderer implements IRenderer {
	private Level level;
	private BitmapFont font;
	private Player player;
	
	private Stage stage;
	private Table table;
	private ShapeRenderer shapeRenderer;
	
	private TextButtonStyle buttonStyle;
	
	private ZAssetManager assetManager;
	private HudController controller;
	
	private HudModel model;
	
	
	
	public HudRenderer(Level level, Viewport viewport, SpriteBatch batch, ZAssetManager assetManager, HudController controller, HudModel model)
	{
		this.level = level;
		this.assetManager = assetManager;
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		
		player = level.getPlayer();
		
		stage = new Stage(viewport, batch);
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		shapeRenderer = new ShapeRenderer();
		
		this.controller = controller;
		this.model = model;
		
		setup();
	}
	
	private void setup()
	{
		LabelStyle textStyle;
		
		textStyle = new LabelStyle();
		textStyle.font = font;
	}
	
	public void render(SpriteBatch batch, OrthographicCamera camera, float delta)
	{
		model.update(player);
		
		stage.draw();
		stage.act(delta);
	}
	
	public Stage getUIStage()
	{
		return stage;
	}
	
	public void dispose()
	{
		font.dispose();
	}
	
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height, true);
	}
		
	public BitmapFont getFont()
	{
		return font;
	}
}
