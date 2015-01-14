package com.zillix.game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.util.UIUtil;

public class ShopElement extends Group {
	
	private Label nameLabel;
	private ImageButton button;
	
	private static final String PLUS_IMAGE_PATH = "ui/images/plus.png";
	
	
	public ShopElement(String name, int X, int Y, LabelStyle style, ZAssetManager assetManager, InputListener onButtonPressed)
	{
		setX(X);
		setY(Y);
		
		this.nameLabel = UIUtil.initLabel(name, style, -150, 0);
		addActor(nameLabel);
		
		button = new BasicImageButton(PLUS_IMAGE_PATH, 150, 0, assetManager);
		addActor(button);
		if (onButtonPressed != null)
		{
			button.addListener(onButtonPressed);
		}
	}

}
