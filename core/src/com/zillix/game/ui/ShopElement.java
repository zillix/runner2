package com.zillix.game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.zillix.game.assets.ZAssetManager;
import com.zillix.game.gamedata.StatDataElement;
import com.zillix.game.gamedata.StatData.GameStatEnum;
import com.zillix.util.UIUtil;

public class ShopElement extends Group {
	
	private StatDataElement statData;
	private Label nameLabel;
	private ImageButton button;
	private Label costLabel;
	private Label statLabel;
	
	private static final String PLUS_IMAGE_PATH = "ui/images/plus.png";
	
	
	public ShopElement(StatDataElement statData, int X, int Y, LabelStyle style, ZAssetManager assetManager, InputListener onButtonPressed)
	{
		this.statData = statData;
		
		setX(X);
		setY(Y);
		
		// TODO(alex)
		int level = 1;
		
		this.nameLabel = UIUtil.initLabel(statData.getName(), style, -150, 0);
		addActor(nameLabel);
		
		this.statLabel = UIUtil.initLabel(Float.toString(statData.getStatValueForLevel(level)), style, 0, 0);
		addActor(statLabel);
		
		button = new BasicImageButton(PLUS_IMAGE_PATH, 150, 0, assetManager);
		addActor(button);
		if (onButtonPressed != null)
		{
			button.addListener(onButtonPressed);
		}
		
		costLabel = UIUtil.initLabel(Integer.toString(statData.getUpgradeCost(level)), style, 200, 0);
		addActor(costLabel);
	}

}
