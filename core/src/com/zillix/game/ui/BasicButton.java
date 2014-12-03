package com.zillix.game.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zillix.game.assets.ZAssetManager;

public class BasicButton extends TextButton {
	
	private static final String DEFAULT_BUTTON_ATLAS_PATH = "ui/buttons/button1/9patch/Buttons.pack";
	private static final String DEFAULT_UP_DRAWABLE = "button1up";
	private static final String DEFAULT_DOWN_DRAWABLE = "button1down";
	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_HEIGHT = 50;
	
	
	public BasicButton(String text, int X, int Y, BitmapFont font, ZAssetManager assetManager)
	{
		super(text, initializeButtonStyle(DEFAULT_BUTTON_ATLAS_PATH, DEFAULT_UP_DRAWABLE, DEFAULT_DOWN_DRAWABLE, font, assetManager));
		initialize(DEFAULT_WIDTH, DEFAULT_HEIGHT, X, Y);
	}
	
	public BasicButton(String text, int X, int Y, BitmapFont font, ZAssetManager assetManager, String buttonAtlasPath, String upDrawable, String downDrawable, int width, int height)
	{
		super(text, initializeButtonStyle(buttonAtlasPath, upDrawable, downDrawable, font, assetManager));
		initialize(width, height, X, Y);
	}
	
	protected void initialize(int width, int height, int x, int y)
	{
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
	}
	
	protected static TextButtonStyle initializeButtonStyle(String imagePath, String upDrawable, String downDrawable, BitmapFont font, ZAssetManager assetManager)
	{
		TextureAtlas buttonAtlas = assetManager.forceLoad(imagePath, TextureAtlas.class);
		
		Skin buttonSkin = new Skin();
		buttonSkin.addRegions(buttonAtlas);
		
		
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = font;
		buttonStyle.up = buttonSkin.getDrawable(upDrawable);
		buttonStyle.down = buttonSkin.getDrawable(downDrawable);
		
		
		buttonSkin.dispose();
		
		return buttonStyle;
	}
}
