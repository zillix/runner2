package com.zillix.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.zillix.game.assets.ZAssetManager;

public class ShopButton extends BasicTextButton {
	public ShopButton(int X, int Y, BitmapFont font, ZAssetManager assetManager)
	{
		super("shop", X, Y, font, assetManager);
	}
}
