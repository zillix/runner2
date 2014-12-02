package com.zillix.game.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.zillix.game.assets.ZAssetManager;

public class ShopButton extends TextButton {
	
	private static final String BUTTON_ATLAS_PATH = "ui/buttons/button1/9patch/Buttons.pack";
	private static final int WIDTH = 100;
	private static final int HEIGHT = 50;
	public ShopButton(int X, int Y, BitmapFont font, ZAssetManager assetManager)
	{
		super("shop", initializeButtonStyle(font, assetManager));
		setWidth(WIDTH);
		setHeight(HEIGHT);
		setX(X - WIDTH / 2);
		setY(Y);
	}
	
	private static TextButtonStyle initializeButtonStyle(BitmapFont font, ZAssetManager assetManager)
	{
		assetManager.forceLoad(BUTTON_ATLAS_PATH, TextureAtlas.class);
		
		TextureAtlas buttonAtlas = assetManager.get(BUTTON_ATLAS_PATH, TextureAtlas.class);
		Skin buttonSkin = new Skin();
		buttonSkin.addRegions(buttonAtlas);
		
		
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = font;
		buttonStyle.up = buttonSkin.getDrawable("button1up");
		buttonStyle.down = buttonSkin.getDrawable("button1down");
		
		
		buttonSkin.dispose();
		
		return buttonStyle;
	}
}
