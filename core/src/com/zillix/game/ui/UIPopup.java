package com.zillix.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.HudController;

public class UIPopup extends Group {
	
	private static final String DEFAULT_MENU_BACKGROUND_PATH = "ui/boxes/box1/9patch/Box.pack";
	
	private Image background;
	protected HudController controller;
	
	public UIPopup(int width, int height, BitmapFont font, ZAssetManager assetManager, HudController controller)
	{
		initialize(width, height, font, assetManager, controller, DEFAULT_MENU_BACKGROUND_PATH);
		
	}
	
	public UIPopup(int width, int height, BitmapFont font, ZAssetManager assetManager, HudController controller, String backgroundImage)
	{
		initialize(width, height, font, assetManager, controller, backgroundImage);
	}
	
	protected void initialize(int width, int height, BitmapFont font, ZAssetManager assetManager, HudController controller, String backgroundImage)
	{
		TextureAtlas atlas = assetManager.forceLoad(backgroundImage, TextureAtlas.class);
		NinePatch patch = atlas.createPatch("box1");
		background = new Image(patch);
		this.addActor(background);
		this.controller = controller;
	}
	
	protected void close()
	{
		controller.close(this);
	}
}
