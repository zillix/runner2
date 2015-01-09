package com.zillix.game.ui;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.controllers.HudController;
import com.zillix.game.interfaces.ICloseable;
import com.zillix.game.ui.UIPopup.VerticalAlignment;

public class UIPopup extends Group implements ICloseable {
	
	private static final String DEFAULT_MENU_BACKGROUND_PATH = "ui/boxes/box1/9patch/Box.pack";
	
	private Image background;
	protected HudController controller;
	protected VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;
	protected HorizontalAlignment horizontalAlignment = HorizontalAlignment.CENTER;
	
	public enum VerticalAlignment
	{
		NONE,
		TOP, 
		CENTER,
		BOTTOM
	}
	
	public enum HorizontalAlignment
	{
		NONE,
		LEFT, 
		CENTER,
		RIGHT
	}
	
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
		background.setWidth(width);
		background.setHeight(height);
		background.setX(-width / 2);
		background.setY(-height / 2);
		this.addActor(background);
		this.controller = controller;
		center();
	}
	
	public void close()
	{
		controller.close(this);
		onClose();
	}
	
	public void onClose()
	{}
	
	@Override
	public void act (float delta)
	{
		super.act(delta);
	}
	
	private void center()
	{
		switch (verticalAlignment)
		{
		case CENTER:
			setCenterPosition(getX(), Gdx.graphics.getHeight() / 2);
			break;
		case TOP:
			setCenterPosition(getX(), background.getHeight() / 2);
			break;
		case BOTTOM:
			setCenterPosition(getX(), Gdx.graphics.getHeight() - background.getHeight() / 2);
			break;
		default:
			break;
		}
		
		switch (horizontalAlignment)
		{
		case CENTER:
			setCenterPosition(Gdx.graphics.getWidth() / 2, getY());
			break;
		case LEFT:
			setCenterPosition(getWidth() / 2, getY());
			break;
		case RIGHT:
			setCenterPosition(Gdx.graphics.getWidth() / 2 - getWidth() / 2, getY());
			break;
		default:
			break;
		}
	}
		
	
}
